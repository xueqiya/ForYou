package com.yiya.qq.api;

import com.yiya.qq.model.bean.BaseBean;
import com.yiya.qq.utils.L;

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
        if (baseBean.getReason().equals("成功的返回")) {
            onSuccess(baseBean.getResult());
        } else {
            onFailure(baseBean.getResultcode(), new ApiException(baseBean.getResultcode()).getMessage());
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