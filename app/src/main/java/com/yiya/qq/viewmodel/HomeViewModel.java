package com.yiya.qq.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.yiya.qq.api.BaseObserver;
import com.yiya.qq.api.ListBaseObserver;
import com.yiya.qq.api.NetWorkManager;
import com.yiya.qq.base.BaseViewModel;
import com.yiya.qq.model.bean.ListBaseBean;
import com.yiya.qq.model.bean.TouTiaoBean;
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

    public MutableLiveData<List<TouTiaoBean>> getTouTiao() {
        final MutableLiveData<List<TouTiaoBean>> listData = new MutableLiveData<>();
        NetWorkManager.getRequest().toutiao(1)
                .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new ListBaseObserver<TouTiaoBean>() {

                    @Override
                    public void onSuccess(List<TouTiaoBean> result) {
                        listData.postValue(result);
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
