package com.neo.http.prettier.activity;

import android.content.Intent;
import android.os.Handler;

import com.neo.http.prettier.R;
import com.neo.http.prettier.object.ImageEntity;
import com.neo.http.prettier.viewpager.CombinationViewPage;
import com.neo.http.utillibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/20 0020.
 */
public class WelcomeActivity extends BaseActivity{
    private List<ImageEntity> list;
    private CombinationViewPage viewPage;
    /**
     * 加载视视图
     */
    @Override
    public int getContentViewId() {
        return R.layout.welcome_activity_view;
    }

    @Override
    protected void loadDatas() {
        list = new ArrayList<>();
        ImageEntity img1 = new ImageEntity();
        img1.setImg_url("null");
        img1.setSrc(R.drawable.bg_welcome_1);
        ImageEntity img2 = new ImageEntity();
        img2.setImg_url("null");
        img2.setSrc(R.drawable.bg_welcome_2);
        ImageEntity img3 = new ImageEntity();
        img3.setImg_url("null");
        img3.setSrc(R.drawable.bg_welcome_3);
        list.add(img1);
        list.add(img2);
        list.add(img3);
        viewPage.setFragmentManager(getSupportFragmentManager());
        viewPage.setViewPagerAdapter(list);
    }
    /**
     * 重写初始化方法
     */
    @Override
    protected void init() {
    viewPage=findViewByIds(R.id.ViewPager);

        //延时跳转到主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeActivity.this, HomePageActivity.class);
                WelcomeActivity.this.startActivity(intent);
                WelcomeActivity.this.finish();

            }
        },3000);
    }



}
