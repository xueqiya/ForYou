package com.yiya.foryou.viewmodel;

import android.app.Application;

import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.bean.LoginBean;
import com.yiya.foryou.data.model.LoginModel;
import com.yiya.foryou.http.api.NetWorkManager;
import com.yiya.foryou.utils.L;
import com.yiya.foryou.utils.RxUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/3/11	15:25
 * description:
 */
public class LoginViewModel extends BaseViewModel {

    public ObservableField<String> uid = new ObservableField<>();
    public ObservableField<String> pwd = new ObservableField<>();
    private final LoginModel loginModel;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginModel = new LoginModel();
    }

    public MutableLiveData<LoginBean> login() {
        return loginModel.login(uid.get(), pwd.get());
    }
}
