package com.example.jenkin.fileupload;

import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <pre>
 *     author : jenkin
 *     e-mail : jekin-du@foxmail.com
 *     time   : 2019/01/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class FTPUtil {


    /**
     * 单个文件上传
     *
     * @param url      网络路径
     * @param params   请求参数
     * @param filePath 上传的文件路径
     * @param listener 上传的响应监听器
     */
    public static void uploadFile(String url, HashMap<String, String> params, String filePath, final ResponseListener listener) {

        OkHttpClient client = new OkHttpClient();
        // 上传文件使用MultipartBody.Builder
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        //添加参数
        if (params != null) {
            //遍历参数，加到请求中
            for (HashMap.Entry<String, String> param : params.entrySet()) {
                builder.addFormDataPart(param.getKey(), param.getValue());
            }
        }

        //添加文件
        if (filePath != null && !filePath.equals("")) {

            try {
                String[] spilt = filePath.split("/");
                String fileName = spilt[spilt.length - 1];

                File file = new File(filePath);
                // 第一个参数是键，第二个参数是文件名，第三个是一个RequestBody
                builder.addFormDataPart("file", fileName, RequestBody.create(null, file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        RequestBody requestBody = builder.build();
        // POST请求
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onError(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                int code = 400;
                String msg = Constant.Msg.FAILURE;

                try {
                    String jsonString = response.body().string();

                    Log.d("TAG", "onResponse: " + jsonString);
                    JSONObject jsonObject = JSONObject.parseObject(jsonString);

                    JSONObject data = null;
                    if (jsonObject != null) {
                        code = jsonObject.getInteger("code");
                        msg = jsonObject.getString("msg");
                        data = jsonObject.getJSONObject("data");
                    }

                    Looper.prepare();
                    if (data != null) {
                        listener.onResponse(code, msg, data.toString());
                    } else {
                        listener.onResponse(code, msg, null);
                    }
                    Looper.loop();
                } catch (Exception e) {

                    Looper.prepare();
                    listener.onError("解析数据出现错误");
                    Looper.loop();
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 下载文件
     *
     * @param remoteUrl 文件的服务器url
     * @param localPath 本地文件路径 包括文件夹和文件名 如/storage/0/MyScreen/folder/file.txt
     */
    public static void downloadFile(String remoteUrl, final String localPath, final DownloadListener listener) {

        //构建请求
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(remoteUrl).build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Looper.prepare();
                listener.onFailure(e.toString());
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i("Code", "onResponse: code:"+response.code());

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;

                //储存下载文件的目录
                String dir = localPath.substring(0, localPath.lastIndexOf('/'));
                File folder = new File(dir);
                if (!folder.exists()) {
                    folder.mkdir();
                }

                File file = new File(localPath);
                if (!file.exists()) {
                    file.createNewFile();
                }

                try {
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);

                    long total = response.body().contentLength();
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        float progress = sum * 1.0f / total;
                        //显示下载进度
                        Looper.prepare();
                        listener.onProgress(progress);
                        Looper.loop();
                    }
                    fos.flush();
                    //下载完成
                    Looper.prepare();
                    listener.onSuccess();
                    Looper.loop();
                } catch (Exception e) {
                    Looper.prepare();
                    listener.onFailure(e.toString());
                    Looper.loop();
                } finally {

                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    public interface DownloadListener {

        /**
         * 下载成功
         */
        void onSuccess();

        /**
         * 下载进度
         *
         * @param percentage 下载百分比
         */
        void onProgress(float percentage);

        /**
         * 下载失败
         *
         * @param error 出错原因
         */
        void onFailure(String error);
    }

    //响应监听器
    public interface ResponseListener {
        //响应
        void onResponse(int code, String msg, String jsonData);

        //网络出错
        void onError(String error);
    }
}
