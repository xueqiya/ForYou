package com.yiya.qq.api;

import retrofit2.Retrofit;

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

    public static AppApi getRequest() {
        if (request == null) {
            request = retrofit.create(AppApi.class);
        }
        return request;
    }
}
