package com.yiya.foryou.viewmodel;

import android.app.Application;

import androidx.lifecycle.LifecycleObserver;
import androidx.annotation.NonNull;

import com.yiya.foryou.base.BaseViewModel;


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
