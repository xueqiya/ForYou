package com.yiya.qq.http.api;

import com.yiya.qq.bean.BaseBean;
import com.yiya.qq.bean.ListBaseBean;
import com.yiya.qq.bean.HomeBean;
import com.yiya.qq.bean.LoginBean;

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

    //登陆
    @POST("/appApi/login")
    Observable<BaseBean<LoginBean>> login(@Query("uid") String uid,
                                          @Query("pwd") String pwd);

    //注册
    @POST("/appApi/register")
    Observable<BaseBean<LoginBean>> register(@Query("uid") String uid,
                                          @Query("pwd") String pwd);

}
