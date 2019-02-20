package com.yiya.qq.api;

import com.yiya.qq.model.bean.BaseBean;
import com.yiya.qq.model.bean.TouTiaoBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2018/11/7	16:53
 * description:
 */
public interface AppApi {
    String key = "060b5543a5db2cf5b99279f837738df2";

    @POST("/toutiao/index")
    Observable<BaseBean<TouTiaoBean>> toutiao(@Query("type") String type, @Query("key") String key);

}
