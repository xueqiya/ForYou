package com.yiya.qq.api;

import com.yiya.qq.model.bean.BaseBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2018/11/7	16:53
 * description:
 */
public interface AppApi {

    @Headers({"url_name:client"})
    @POST("UPDapp")
    Observable<BaseBean> UPDapp(@Body RequestBody requestBody);

}
