package com.yiya.qq.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleObserver;
import android.support.annotation.NonNull;

import com.yiya.qq.base.BaseViewModel;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:44
 * description:
 */
public class MineViewModel extends BaseViewModel implements LifecycleObserver {
    public MineViewModel(@NonNull Application application) {
        super(application);
    }
}
