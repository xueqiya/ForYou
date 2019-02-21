package com.yiya.qq.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.annotation.NonNull;

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
