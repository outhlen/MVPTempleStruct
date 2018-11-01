package com.kelin.app.struct_pro.comm;

import android.os.Environment;

public class Constant {

    public static final String TX_KEY = "287b50acef572d1716e8a7c7ca504c22";
    public static final String TX_HTTP = "http://api.tianapi.com/";
    public static String HOST = "https://www.xxx.com/app_v5/";

    public static final String KEY_FIRST_SPLASH = "first_splash";                 //是否第一次启动
    public static final String KEY_IS_LOGIN = "is_login";

    public static final int REALM_VERSION = 0;
    public static final String REALM_NAME = "sc";
    public static final String SP_NAME = "nearby";
    public static final String ExternalStorageDirectory =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    public static final String KEY_BANNER_URL = "banner_url";           //播放图片地址


    public class status{
        public static final int success=200;
        public static final int error=-1;
    }

    public interface viewType{
        int typeBanner = 1;         //轮播图
        int typeGv = 2;             //九宫格
        int typeTitle = 3;          //标题
        int typeList = 4;           //list
        int typeNews = 5;           //新闻
        int typeMarquee = 6;        //跑马灯
        int typePlus = 7 ;          //不规则视图
        int typeSticky = 8;         //指示器
        int typeFooter = 9;         //底部
        int typeGvSecond = 10;      //九宫格
    }


}
