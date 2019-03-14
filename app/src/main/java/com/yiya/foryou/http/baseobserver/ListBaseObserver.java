package com.yiya.foryou.http.baseobserver;

import com.yiya.foryou.bean.ListBaseBean;
import com.yiya.foryou.http.api.ApiException;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/20	13:19
 * description:
 */
public abstract class ListBaseObserver<T> implements Observer<ListBaseBean<T>> {
    @Override
    public final void onNext(@NonNull ListBaseBean<T> baseBean) {
        if (baseBean.getCode() == 200) {
            onSuccess(baseBean.getData());
        } else {
            onFailure(baseBean.getCode(), new ApiException(baseBean.getMessage()).getMessage());
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

    public abstract void onSuccess(List<T> result);

    public abstract void onFailure(int code, String errorMessage);
}