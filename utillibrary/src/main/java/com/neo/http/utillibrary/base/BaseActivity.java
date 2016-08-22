package com.neo.http.utillibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/8/20 0020.
 *
 * 所有Activity的父类
 * Created by Ken on 2016/8/1.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        init();
        loadDatas();
    }

    /**
     * 返回当前activity所显示的布局ID
     * @return
     */
    public abstract int getContentViewId();

    /**
     * 初始化方法
     */
    protected void init(){

    }

    /**
     * 加载数据的方法
     */
    protected void loadDatas(){

    }

    /**
     * 查找页面布局ID所对应的控件对象，不用强制转换
     * @param resId
     * @param <T>
     * @return
     */
    protected <T> T findViewByIds(int resId){
        return (T) findViewById(resId);
    }
}
