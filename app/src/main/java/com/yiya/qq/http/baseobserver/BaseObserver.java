package com.yiya.qq.http.baseobserver;

import com.yiya.qq.app.App;
import com.yiya.qq.bean.BaseBean;
import com.yiya.qq.http.api.ApiException;
import com.yiya.qq.utils.L;
import com.yiya.qq.utils.T;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/20	13:19
 * description:
 */
public abstract class BaseObserver<T> implements Observer<BaseBean<T>> {
    @Override
    public final void onNext(@NonNull BaseBean<T> baseBean) {
        if (baseBean.getCode() == 200) {
            onSuccess(baseBean.getData());
        } else {
            com.yiya.qq.utils.T.showShort(App.getInstance(), baseBean.getMsg());
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

    public abstract void onSuccess(T result);

    public abstract void onFailure(int code, String errorMessage);
}