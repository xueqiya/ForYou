package com.yiya.foryou.http.baseobserver;

import com.yiya.foryou.app.App;
import com.yiya.foryou.bean.BaseBean;
import com.yiya.foryou.bean.NoDataBaseBean;
import com.yiya.foryou.http.api.ApiException;
import com.yiya.foryou.utils.T;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/20	13:19
 * description:
 */
public abstract class NoDataBaseObserver implements Observer<NoDataBaseBean> {
    @Override
    public final void onNext(@NonNull NoDataBaseBean baseBean) {
        if (baseBean.isSuccess()) {
            onSuccess("success");
        } else {
            com.yiya.foryou.utils.T.showShort(App.getInstance(), baseBean.getMsg());
            onFailure(baseBean.getCode(), new ApiException(baseBean.getMsg()).getMessage());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(0, e.toString());
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    public abstract void onSuccess(String success);

    public abstract void onFailure(int code, String errorMessage);
}