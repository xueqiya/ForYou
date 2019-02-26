package com.yiya.qq.http.api;

import com.yiya.qq.bean.ListBaseBean;
import com.yiya.qq.bean.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2018/11/7	16:53
 * description:
 */
public interface AppApi {

    @POST("/api/goods/list")
    Observable<ListBaseBean<HomeBean>> home(@Query("pageNum") int pageNum);

}
