package com.yiya.foryou.http.api;

import com.yiya.foryou.http.log.LoggingInterceptor;
import com.yiya.foryou.utils.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2018/11/13	9:45
 * description:
 */
public class NetWorkManager {
    private static NetWorkManager mInstance;
    private static Retrofit retrofit;
    private static AppApi request = null;

    public static NetWorkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkManager();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        // 初始化okhttp
        OkHttpClient client = new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();

        // 初始化Retrofit
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static AppApi getRequest() {
        if (request == null) {
            synchronized (Request.class) {
                request = retrofit.create(AppApi.class);
            }
        }
        return request;
    }
}
