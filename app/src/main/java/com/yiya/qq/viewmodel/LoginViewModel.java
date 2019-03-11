package com.yiya.qq.viewmodel;

import android.app.Application;

import com.yiya.qq.base.BaseViewModel;
import com.yiya.qq.bean.LoginBean;
import com.yiya.qq.http.api.NetWorkManager;
import com.yiya.qq.http.baseobserver.BaseObserver;
import com.yiya.qq.utils.L;
import com.yiya.qq.utils.RxUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/3/11	15:25
 * description:
 */
public class LoginViewModel extends BaseViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public  ObservableField<String> uid = new ObservableField<>();

    public  ObservableField<String> pwd = new ObservableField<>();

    public MutableLiveData<LoginBean> login() {
        final MutableLiveData<LoginBean> data = new MutableLiveData<>();
        L.d("用户名：" + uid.get() + "密码：" + pwd.get());
        NetWorkManager.getRequest().login("xueqi", "123")
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new BaseObserver<LoginBean>() {

                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        data.setValue(loginBean);
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        data.setValue(null);
                    }

                });
        return data;
    }
}
