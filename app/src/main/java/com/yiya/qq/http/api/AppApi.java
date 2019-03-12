package com.yiya.qq.http.api;

import com.yiya.qq.bean.BaseBean;
import com.yiya.qq.bean.ListBaseBean;
import com.yiya.qq.bean.NoteBean;
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

    //登陆
    @POST("/appApi/login")
    Observable<BaseBean<LoginBean>> login(@Query("uid") String uid,
                                          @Query("pwd") String pwd);

    //注册
    @POST("/appApi/register")
    Observable<BaseBean<LoginBean>> register(@Query("uid") String uid,
                                             @Query("pwd") String pwd);

    //通知
    @POST("/appApi/note")
    Observable<ListBaseBean<NoteBean>> notice(@Query("pageNum") int pageNum,
                                              @Query("size") int size);

    //通知详情
    @POST("/appApi/findNoteBeanById")
    Observable<BaseBean<NoteBean>> findNoteBeanById(@Query("id") int id);
}
