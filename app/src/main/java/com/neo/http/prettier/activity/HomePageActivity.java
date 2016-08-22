package com.neo.http.prettier.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.neo.http.prettier.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/20 0020.
 */
public class HomePageActivity extends AppCompatActivity {


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

        homepageRbShouye.performClick();
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
                break;
            case R.id.homepage_rb_mine:
                break;
        }
    }
}
