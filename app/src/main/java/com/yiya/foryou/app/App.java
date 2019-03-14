package com.yiya.foryou.app;

import android.app.Application;

import com.yiya.foryou.http.api.NetWorkManager;
import com.yiya.foryou.utils.AppConstants;
import com.yiya.foryou.utils.SPUtil;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/13	10:34
 * description:
 */
public class App extends Application {

    private static App qqApplication;

    public static App getInstance() {
        return qqApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        qqApplication = this;
        NetWorkManager.getInstance().init();

    }
}
