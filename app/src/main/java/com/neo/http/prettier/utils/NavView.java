package com.neo.http.prettier.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.neo.http.prettier.R;


/**
 * 加载viewpager上的圆点
 *
 */
public class NavView extends FrameLayout{

    private LinearLayout lltop,llbottom;
    private int bottomimage,topimage;
    private ImageView topimg;
    private LinearLayout.LayoutParams layoutParams;

    public NavView(Context context) {
       this(context,null);
    }

    public NavView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NavView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        paresAttrs(attrs);
    }

    /**
     * 初始化数据
     */
    private void init() {
        //设置图层构架
        LayoutInflater.from(getContext()).inflate(R.layout.navview,this,true);
        //获取子组件
        llbottom= (LinearLayout) findViewById(R.id.nav_bottom);
        lltop= (LinearLayout) findViewById(R.id.nav_top);

    }


    /**
     * 解析自定义属性
     * @param attrs
     */
    private void paresAttrs(AttributeSet attrs) {
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.NavView);
        topimage = typedArray.getResourceId(R.styleable.NavView_checked, 0);
        bottomimage = typedArray.getResourceId(R.styleable.NavView_unchecked, 0);
        typedArray.recycle();
    }

    public void setCount(int position){
        if (position>0){
            llbottom.removeAllViews();
            lltop.removeAllViews();
            //加载底部图片
            for (int i=0;i<position;i++){

                ImageView iv=new ImageView(getContext());
                iv.setImageResource(bottomimage);
                iv.setPadding(10,0,10,0);
                llbottom.addView(iv);
                if (i==0||i==position-1){
                    iv.setVisibility(INVISIBLE);
                }
            }
            //加载顶部图片
            topimg=new ImageView(getContext());
            topimg.setImageResource(topimage);
            topimg.setPadding(10,0,10,0);
            lltop.addView(topimg);
            layoutParams= (LinearLayout.LayoutParams) topimg.getLayoutParams();

        }
    }
    public void setViewPagerListener(ViewPager viewPager){
        if (viewPager!=null){
            //添加监听，比设置监听的优点在于，添加不会覆盖，而后者会被覆盖
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                    Log.d("TAG","topimg.getMeasuredWidth():"+topimg.getMeasuredWidth()+" "+position+" "+positionOffset);
                    layoutParams.leftMargin= (int) (topimg.getMeasuredWidth()*(position+positionOffset));
                    topimg.setLayoutParams(layoutParams);
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }
}
