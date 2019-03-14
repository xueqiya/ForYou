package com.yiya.foryou.viewmodel;

import android.app.Application;

import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.bean.LoginBean;
import com.yiya.foryou.http.api.NetWorkManager;
import com.yiya.foryou.http.baseobserver.BaseObserver;
import com.yiya.foryou.http.baseobserver.NoDataBaseObserver;
import com.yiya.foryou.utils.L;
import com.yiya.foryou.utils.RxUtils;

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

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<String> uid = new ObservableField<>();

    public ObservableField<String> pwd = new ObservableField<>();

    //注册
    public MutableLiveData<String> register() {
        final MutableLiveData<String> data = new MutableLiveData<>();
        NetWorkManager.getRequest().register(uid.get(), pwd.get())
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new NoDataBaseObserver() {

                    @Override
                    public void onSuccess(String success) {
                        data.setValue(success);
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        data.setValue(null);
                    }

                });
        return data;
    }

}
