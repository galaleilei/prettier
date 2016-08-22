package com.neo.http.prettier.mainactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.neo.http.prettier.R;
import com.neo.http.prettier.activity.HomePageActivity;
import com.neo.http.prettier.activity.WelcomeActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isFirst=false;//是否进入欢迎页面的状态值
    private Intent intent;//创建跳转Intent
    private SharedPreferences sdf;//共享数据，此sdf只用于保存是否进入欢迎页面的状态值
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取SharedPreferences，并初始化，模式私有
        sdf=getSharedPreferences("First",MODE_PRIVATE);
        isFirst=sdf.getBoolean("First",true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isFirst){
                    sdf.edit().putBoolean("First",false).commit();
                    intent=new Intent(MainActivity.this, WelcomeActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                }else{
                    intent=new Intent(MainActivity.this, HomePageActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                }
            }
        },3000);


    }
}
