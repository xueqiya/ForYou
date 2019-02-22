package com.yiya.qq.viewmodel;

import android.app.Application;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.yiya.qq.api.ListBaseObserver;
import com.yiya.qq.api.NetWorkManager;
import com.yiya.qq.app.App;
import com.yiya.qq.base.BaseViewModel;
import com.yiya.qq.model.bean.HomeBean;
import com.yiya.qq.model.room.AppDatabase;
import com.yiya.qq.utils.L;
import com.yiya.qq.utils.RxUtils;

import java.util.List;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:44
 * description:
 */
public class HomeViewModel extends BaseViewModel implements LifecycleObserver {
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<HomeBean>> getHome() {
        final MutableLiveData<List<HomeBean>> listData = new MutableLiveData<>();
        NetWorkManager.getRequest().home(1)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new ListBaseObserver<HomeBean>() {

                    @Override
                    public void onSuccess(List<HomeBean> result) {
                        listData.postValue(result);
                        for (int i = 0; i < result.size(); i++) {
                            AppDatabase.getDatabase().homeDao().insertTitle(result.get(i));
                        }
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        listData.setValue(null);
                        L.e("错误代码" + code + "   " + "错误信息" + errorMessage);
                    }
                });
        return listData;
    }
}
