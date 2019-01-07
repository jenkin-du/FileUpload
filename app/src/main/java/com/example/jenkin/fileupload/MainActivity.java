package com.example.jenkin.fileupload;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText editText;
    private Context mContext;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        // 申请并获得权限
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        // 获取控件
        Button btUpload = (Button) findViewById(R.id.bt_upload);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMax(100);


        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String url = "http://192.168.0.101:8080/MyScreen/FileController/receiveFile";
//
//                HashMap<String, String> params = new HashMap<>();
//                params.put("mid", "2019010615559873");
//
//                String filePath = Environment.getExternalStorageDirectory().getPath() + "/file.txt";
//
//                FTPUtil.uploadFile(url, params, filePath, new FTPUtil.ResponseListener() {
//                    @Override
//                    public void onResponse(int code, String msg, String jsonData) {
//
//                        Log.i("TAG", "onResponse: " + code + ": " + msg + ": " + jsonData);
//                        Toast.makeText(MainActivity.this,"response",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(String error) {
//
//                        Log.i("TAG", "onError: "+error);
//                    }
//                });

                dialog.show();
                String remoteUrl = "http://192.168.0.101:8080/file/log.txt";
                String localPath = Environment.getExternalStorageDirectory().getPath() + "/log.txt";

                FTPUtil.downloadFile(remoteUrl, localPath, new FTPUtil.DownloadListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }

                    @Override
                    public void onProgress(float percentage) {

                        dialog.setProgress((int) (100 * percentage));

                        Log.i("percentage", "onProgress: "+percentage);
                    }

                    @Override
                    public void onFailure(String error) {
                        dialog.cancel();

                        Log.d("error", "onFailure: error:" + error);
                        Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
