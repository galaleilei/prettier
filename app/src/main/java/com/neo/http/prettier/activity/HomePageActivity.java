package com.neo.http.prettier.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.neo.http.prettier.R;
import com.neo.http.prettier.fragment.MeiGouFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/20 0020.
 */
public class HomePageActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment showFragment;

    @Bind(R.id.home_fl)
    FrameLayout homeFl;
    @Bind(R.id.homepage_rb_shouye)
    RadioButton homepageRbShouye;
    @Bind(R.id.homepage_rb_shequ)
    RadioButton homepageRbShequ;
    @Bind(R.id.homepage_rb_zixun)
    RadioButton homepageRbZixun;
    @Bind(R.id.homepage_rb_meigou)
    RadioButton homepageRbMeigou;
    @Bind(R.id.homepage_rb_mine)
    RadioButton homepageRbMine;
    @Bind(R.id.homepage_rg)
    RadioGroup homepageRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_view);
        ButterKnife.bind(this);

        fragmentManager=getSupportFragmentManager();
        homepageRbShouye.performClick();//虚拟点击首页
    }




    @OnClick({R.id.homepage_rb_shouye, R.id.homepage_rb_shequ, R.id.homepage_rb_zixun, R.id.homepage_rb_meigou, R.id.homepage_rb_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.homepage_rb_shouye:
                break;
            case R.id.homepage_rb_shequ:
                break;
            case R.id.homepage_rb_zixun:
                break;
            case R.id.homepage_rb_meigou:
                MeiGouFragment meiGouFragment=new MeiGouFragment();
                        fragmentManager(R.id.home_fl,meiGouFragment,"meigou");
                break;
            case R.id.homepage_rb_mine:
                break;
        }

    }
    /**
     * 管理fragment的显示与隐藏
     */
    protected void fragmentManager(int resid, Fragment fragment, String tag){
        //开启一个事务
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //隐藏正在显示的Fragment对象
        if (showFragment!=null) {
            fragmentTransaction.hide(showFragment);
        }
        Fragment mFragment=fragmentManager.findFragmentByTag(tag);
        if (mFragment!=null) {
            fragmentTransaction.show(mFragment);
        }else {
            mFragment=fragment;
            fragmentTransaction.add(resid,mFragment,tag);
        }
        showFragment=mFragment;
        fragmentTransaction.commit();
    }


}
