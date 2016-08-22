package com.neo.http.prettier.network;

import com.neo.http.prettier.object.MeigouBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ga on 2016/8/22.
 */
public interface HttpUtils {
    /**
     * 获取数据
     * 美购模块
     */

    @GET(HttpURLs.HTTP_MEIGOU)
    Call<MeigouBean> getMeigouBean();
}
