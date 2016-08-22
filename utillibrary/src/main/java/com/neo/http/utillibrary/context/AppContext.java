package com.neo.http.utillibrary.context;

import android.app.Application;

import com.neo.http.utillibrary.util.ShareUtil;


/**
 * Created by Ken on 2016/8/1.
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化共享参数
        ShareUtil.init(this);
    }
}
