package com.yiya.qq.app;

import android.app.Application;

import com.yiya.qq.api.NetWorkManager;
import com.yiya.qq.model.room.AppDatabase;

import androidx.room.Room;

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
