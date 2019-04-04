package com.yiya.foryou.data.model;

import com.yiya.foryou.bean.LoginBean;
import com.yiya.foryou.bean.OkBean;
import com.yiya.foryou.http.api.NetWorkManager;
import com.yiya.foryou.utils.RxUtils;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginModel {

    //登陆
    public MutableLiveData<LoginBean> login(String uid, String pwd) {
        MutableLiveData<LoginBean> LoginBeanLiveData = new MutableLiveData<>();
        NetWorkManager.getRequest().login(uid, pwd)
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Observer<LoginBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        LoginBeanLiveData.setValue(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoginBeanLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return LoginBeanLiveData;
    }

    //注册
    public MutableLiveData<OkBean> register(String uid, String pwd) {
        MutableLiveData<OkBean> okBeanLiveData = new MutableLiveData<>();
        NetWorkManager.getRequest().register(uid, pwd)
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Observer<OkBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OkBean okBean) {
                        okBeanLiveData.setValue(okBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        okBeanLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }

                });
        return okBeanLiveData;
    }
}
