package com.yiya.qq.ui.home;

import android.app.Application;
import android.support.annotation.NonNull;

import com.yiya.qq.api.NetWorkManager;
import com.yiya.qq.base.BaseListViewModel;
import com.yiya.qq.model.bean.BaseBean;
import com.yiya.qq.model.bean.TouTiaoBean;
import com.yiya.qq.utils.AppConstants;
import com.yiya.qq.utils.L;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:44
 * description:
 */
public class HomeViewModel extends BaseListViewModel {
    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void getTouTiao() {
        NetWorkManager.getRequest().toutiao("top", "060b5543a5db2cf5b99279f837738df2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TouTiaoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TouTiaoBean touTiaoBean) {
                        if (touTiaoBean.getResult().getStat() == "1") {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
