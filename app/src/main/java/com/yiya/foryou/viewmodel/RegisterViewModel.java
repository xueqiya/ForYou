package com.yiya.foryou.viewmodel;

import android.app.Application;

import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.bean.OkBean;
import com.yiya.foryou.data.model.LoginModel;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/3/11	17:01
 * description:
 */
public class RegisterViewModel extends BaseViewModel {

    private final LoginModel loginModel;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        loginModel = new LoginModel();
    }

    public ObservableField<String> uid = new ObservableField<>();

    public ObservableField<String> pwd = new ObservableField<>();

    //注册
    public MutableLiveData<OkBean> register() {
        return loginModel.register(uid.get(), pwd.get());
    }

}
