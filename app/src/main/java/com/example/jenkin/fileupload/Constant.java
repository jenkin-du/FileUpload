package com.example.jenkin.fileupload;

import android.os.Environment;

/**
 * <pre>
 *     author : jenkin
 *     e-mail : jekin-du@foxmail.com
 *     time   : 2018/07/31
 *     desc   : 各种常量
 *     version: 1.0
 * </pre>
 */
public class Constant {

    /**
     * url
     */
    public static class Url {
        //基本url
        public static String BASE = "http://192.168.0.101:8080/MyScreen";

        //本地图片存储url
        public static String PHOTO = Environment.getExternalStorageDirectory().getPath() + "/myscreen/Image/";
    }


    /**
     * 响应消息
     */
    public static class Msg {

        //common
        public static final String SUCCESS = "success";// 成功
        public static final String FAILURE = "failure";//失败

        //注册
        public static final String PHONE_EXISTED = "phone existed";// 电话已注册

        //登陆
        public static final String PHONE_OR_PASSWORD_FAILED = "phone or password failed";// 电话或密码错误

        //获取
        public static final String GET_PLAN_FAILED = "get plan failed";//获取平板守护设置失败
    }

    public static class ResponseCode {

        private static final int SUCCESS = 200;
        private static final int FAILURE = 400;
    }


    /**
     * 日期常量
     */
    public static class Date {

        public static final String MONDAY = "MONDAY";
        public static final String TUESDAY = "TUESDAY";
        public static final String WEDNESDAY = "WEDNESDAY";
        public static final String THURSDAY = "THURSDAY";
        public static final String WEEKDAY = "WEEKDAY";
        public static final String FRIDAY = "FRIDAY";
        public static final String SATURDAY = "SATURDAY";
        public static final String SUNDAY = "SUNDAY";
        public static final String HOLIDAY = "HOLIDAY";
        public static final String EVERYDAY = "EVERYDAY";
        public static final String WEEKEND = "WEEKEND";

    }


    /**
     * Intent Action 常量
     */
    public static class Action {

        //平板守护
        public static final String PAD_OPEN_CHANGE = "PAD_OPEN_CHANGE";//平板守护开放情况改变了
        //平板守护
        public static final String TV_OPEN_CHANGE = "TV_OPEN_CHANGE";//电视守护开放情况改变了

        //控制监测
        public static final String TAKE_PICTURE = "TAKE_PICTURE";//拍照

        public static final String HANDEL_PICTURE = "HANDEL_PICTURE";//处理图片

        public static final String LOCK_SCREEN = "LOCK_SCREEN";//锁屏

        public static final String PAD_WATCH_CHANGE = "PAD_WATCH_CHANGE";//平板守护设置改变了

        public static final String SET_WATCH_ALARM = "SET_WATCH_ALARM";//设置守护闹钟

        public static final String SET_WATCH_OPEN_TIME = "SET_WATCH_OPEN_TIME";//设置开放时间

        public static final String SET_WATCH_CLOSE_TIME = "SET_WATCH_CLOSE_TIME";//设置关闭时间

        public static final String LAST_TIME_END = "LAST_TIME_END";//持续时间结束

        public static final String INTERVAL_TIME_END = "INTERVAL_TIME_END";//间隔时间结束

        public static final String START_MONITOR = "START_MONITOR";//开始视距检测

        public static final String LOGOUT = "LOGOUT";//退出系统

        public static final String START_ALERT = "START_ALERT";//开始警告

        public static final String STOP_ALERT = "STOP_ALERT";//停止警告

        public static final String CHECK_PICTURE = "CHECK_PICTURE";//检查拍摄的照片

        public static final String ADD_MONITOR_VIEW = "ADD_MONITOR_VIEW";//加上检测view

        public static final String REMOVE_MONITOR_VIEW = "REMOVE_MONITOR_VIEW";//去除检测view

        public static final String SHOW_MONITOR_VIEW = "SHOW_MONITOR_VIEW";//显示检测view

        public static final String CLOSE_MONITOR_VIEW = "CLOSE_MONITOR_VIEW";//关闭检测view


        public static final String ALI_PAY = "ALI_PAY";//支付宝支付

        public static final String WEIXIN_PAY = "WEIXIN_PAY";//微信支付
    }

    /**
     * 请求常量
     */
    public static class RequestCode {

        //群成员
        public static final int GROUP_MEMBER = 1;

        //群邀请
        public static final int INVITE = 2;
    }

    /**
     * 返回常量
     */
    public static class ResultCode {

        //群成员
        public static final int GROUP_MEMBER = 1;

        //接受
        public static final int ACCEPT = 2;
        //绝句
        public static final int REJECT = 3;
    }


    /**
     * Intent 传值常量
     */
    public static class ExtraName {

        //平板守护
        public static final String PAD_OPEN = "PAD_OPEN";
        //电视守护
        public static final String TV_OPEN = "TV_OPEN";

        //视距监测
        public static final String IMAGE_PATH = "IMAGE_PATH";//图片路径

        //陪伴与守护
        public static final String GROUP = "GROUP";//群
        public static final String MEMBERS = "MEMBERS";//群成员
        public static final String NOTICE = "NOTICE";//群公告
        public static final String INVITE = "INVITE";//邀请
        public static final String CONTACT = "CONTACT";//联系人
        public static final String GID = "GID";//邀请


        //页面
        public static final String PAGE = "PAGE";

        //费用
        public static final String CHARGE = "CHARGE";
        public static final String MONTH = "MONTH";
        public static final String PACKAGE = "PACKAGE";
        public static final String PAY_RESULT = "PAY_RESULT";//支付结果

        //锁屏
        public static String LOCK = "LOCK";
        //密码
        public static String PASSWORD = "PASSWORD";
    }


    /**
     * 默认设置常量
     */
    public static class Setting {

        //平板守护
        public static final int LAST_TIME = 45;//默认持续观看时间

        public static final int INTERVAL_TIME = 15;//默认休息间隔时间


        //视距监测时间
        public static final int APP_MONITOR_TIME = 60; //app控制监测时间间隔，以秒为单位

        public static final int SIGHT_MONITOR_TIME = 5 * 60; //视距监测时间间隔，以秒为单位

        public static final int ALERT_LAST_TIME = 3; //警告持续时间
    }


    /**
     * 权限常量
     */
    public static class Permission {

        //拍照
        public static final int CAMERA = 0;
        //存储
        public static final int STORAGE = 1;

    }


    /**
     * 推送信息
     */
    public static class Push {

        //推送平台的appKey
        public static final String APP_KEY = "Td9obPr73HL5icURii91F7Rw";

        //信息推送行为
        public static class Action {
            //加好友
            public static final String ADD_CONTACT = "addContact";
            //回应好友
            public static final String REPLY_CONTACT = "replyContact";
            //删除好友
            public static final String DELETE_CONTACT = "deleteContactOrReject";
            //加群
            public static final String ADD_GROUP = "addGroup";
            //回应加群
            public static final String REPLY_GROUP = "replyGroup";
            //删除群成员
            public static final String DELETE_GROUP_MEMBER = "deleteGroupMember";
            //一键锁屏
            public static final String LOCK_SCREEN = "lockScreen";
            //邀请陪伴
            public static final String INVITE_COMPANY = "inviteCompany";
            //家长端设置推送
            public static final String SET_PAD_SETTING = "setPadSetting";
            //回应守护邀请
            public static final String REPLY_WATCH_APPLY = "replyWatchApply";
            //支付宝支付成功推送
            public static final String ALIPAY_REPLAY = "alipayReply";
            //微信支付推送
            public static final String WEIXIN_REPLAY = "weixinReply";
        }
    }

    public static int NOTIFICATION_ID = 0;


    /**
     * 微信开发各种常量
     */
    public static class Weixin {

        public static String APP_ID = "wxd930ea5d5a258f4f";
    }

}
