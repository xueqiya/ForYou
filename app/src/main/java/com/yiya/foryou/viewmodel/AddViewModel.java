package com.yiya.foryou.viewmodel;

import android.app.Application;

import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.http.api.NetWorkManager;
import com.yiya.foryou.http.baseobserver.BaseObserver;
import com.yiya.foryou.http.baseobserver.NoDataBaseObserver;
import com.yiya.foryou.utils.RxUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/3/13	12:31
 * description:
 */
public class AddViewModel extends BaseViewModel {
    public AddViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> details = new ObservableField<>();

    public MutableLiveData<String> Adddata = new MutableLiveData<>();

    public MutableLiveData add(String uid, String date) {
        NetWorkManager.getRequest().saveNote(uid, date, title.get(), details.get())
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new NoDataBaseObserver() {

                    @Override
                    public void onSuccess(String success) {
                        Adddata.setValue(success);
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        Adddata.setValue(null);
                    }
                });
        return Adddata;
    }
}
