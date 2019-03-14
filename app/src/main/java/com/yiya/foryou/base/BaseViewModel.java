package com.yiya.foryou.base;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.annotation.NonNull;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:33
 * description:
 */
public class BaseViewModel extends AndroidViewModel implements LifecycleObserver {

    public int mPage = 1;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int mPage) {
        this.mPage = mPage;
    }
}
