package com.yiya.qq.viewmodel;

import android.app.Application;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.yiya.qq.data.model.HomeModel;
import com.yiya.qq.http.RequestImpl;
import com.yiya.qq.base.BaseViewModel;
import com.yiya.qq.bean.HomeBean;

import java.util.List;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:44
 * description:
 */
public class HomeViewModel extends BaseViewModel implements LifecycleObserver {

    private final HomeModel model;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        model = new HomeModel();
    }

    public MutableLiveData<List<HomeBean>> getHome() {
        final MutableLiveData<List<HomeBean>> listData = new MutableLiveData<>();
        model.getHome(new RequestImpl() {
            @Override
            public void loadSuccess(Object object) {
                listData.setValue((List<HomeBean>) object);
            }

            @Override
            public void loadFailed() {
                listData.setValue(null);
            }
        });
        return listData;
    }
}
