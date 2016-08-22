package com.neo.http.prettier.json_download;

import android.os.Handler;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell on 2016/7/29.
 */
public class JSON_DownLoad {

    private static ExecutorService executorService= Executors.newFixedThreadPool(5);
   private static String strJson=null;
    //接受子线程的数据
    private static Handler handler=new Handler();
        public static byte[] jsonDownLoadString(String url_http){
            HttpURLConnection conn = null;
            InputStream in = null;
            ByteArrayOutputStream out = null;
            byte[] bytes=null;
            try {
                URL url = new URL(url_http);
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setConnectTimeout(5000);//设置最长请求时间
                conn.setRequestMethod("GET");//设置请求模式:GET
                conn.connect();
                in = conn.getInputStream();
                out = new ByteArrayOutputStream();

                byte[] bt = new byte[1024];
                int len = 0;
                while ((len = in.read(bt)) != -1) {
                    out.write(bt, 0, len);
                }
                bytes = out.toByteArray();
            } catch (Exception e) {
                Log.d("TAG", "json_DownLoad:异常");
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
            return bytes;
        }


    public static void json_DownLoad(final String url_http, final DownLoadJsonSucc downLoadJsonSucc){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                byte[] bytes=jsonDownLoadString(url_http);
                if (bytes!=null){
                    final String strJson=new String(bytes);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            downLoadJsonSucc.downLoadSucc(url_http,strJson);
                        }
                    });
                }

            }
        });
    }

    public interface DownLoadJsonSucc{
        void downLoadSucc(String url, String strJson);
    }
}
