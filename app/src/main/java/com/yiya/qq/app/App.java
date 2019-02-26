package com.yiya.qq.app;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.yiya.qq.http.api.NetWorkManager;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/13	10:34
 * description:
 */
public class App extends Application {

    private static App qqApplication;
    private RefWatcher refWatcher;

    public static App getInstance() {
        return qqApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        qqApplication = this;
        refWatcher = LeakCanary.install(this);
        NetWorkManager.getInstance().init();
    }
    public static RefWatcher getRefWatcher(Context context){
        App application = (App)context.getApplicationContext();
        return application.refWatcher;
    }
}
