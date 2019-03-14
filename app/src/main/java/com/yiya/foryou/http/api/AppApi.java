package com.yiya.foryou.http.api;

import com.yiya.foryou.bean.BaseBean;
import com.yiya.foryou.bean.ListBaseBean;
import com.yiya.foryou.bean.NoDataBaseBean;
import com.yiya.foryou.bean.NoteBean;
import com.yiya.foryou.bean.LoginBean;

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
    Observable<NoDataBaseBean> register(@Query("uid") String uid,
                                        @Query("pwd") String pwd);

    //列表
    @POST("/appApi/note")
    Observable<ListBaseBean<NoteBean>> notice(@Query("uid") String uid,
                                              @Query("pageNum") int pageNum,
                                              @Query("size") int size);

    //添加
    @POST("/appApi/saveNote")
    Observable<NoDataBaseBean> saveNote(@Query("uid") String uid,
                                  @Query("time") String time,
                                  @Query("title") String title,
                                  @Query("details") String details);

    //通知详情
    @POST("/appApi/findNoteBeanById")
    Observable<BaseBean<NoteBean>> findNoteBeanById(@Query("id") int id);

    //更改
    @POST("/appApi/updateNote")
    Observable<NoDataBaseBean> updateNote(@Query("id") int id,
                                    @Query("time") String time,
                                    @Query("title") String title,
                                    @Query("details") String details);

    //删除
    @POST("/appApi/deleteNote")
    Observable<NoDataBaseBean> deleteNote(@Query("id") int id);
}
