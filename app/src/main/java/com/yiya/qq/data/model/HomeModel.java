package com.yiya.qq.data.model;

import com.yiya.qq.bean.HomeBean;
import com.yiya.qq.data.room.AppDatabase;
import com.yiya.qq.http.RequestImpl;
import com.yiya.qq.http.api.NetWorkManager;
import com.yiya.qq.http.baseobserver.ListBaseObserver;
import com.yiya.qq.utils.L;
import com.yiya.qq.utils.RxUtils;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/26	9:11
 * description:
 */
public class HomeModel {

    public void getHome(final RequestImpl listener) {
        NetWorkManager.getRequest().home(1)
                //.compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new ListBaseObserver<HomeBean>() {

                    @Override
                    public void onSuccess(List<HomeBean> result) {
                        listener.loadSuccess(result);
                        for (int i = 0; i < result.size(); i++) {
                            AppDatabase.getDatabase().homeDao().insertTitle(result.get(i));
                        }
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        listener.loadFailed();
                    }
                });
    }
}
