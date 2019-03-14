package com.yiya.foryou.viewmodel;

import android.app.Application;

import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.bean.NoteBean;
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
 * create at 2019/3/12	16:45
 * description:
 */
public class DetailViewModel extends BaseViewModel {
    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> details = new ObservableField<>();

    public MutableLiveData<String> updateData = new MutableLiveData<>();

    //获取通知详情
    public MutableLiveData<NoteBean> getNoteDetail(int id) {
        MutableLiveData<NoteBean> NoteDetailData = new MutableLiveData<>();
        NetWorkManager.getRequest().findNoteBeanById(id)
                //.compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new BaseObserver<NoteBean>() {

                    @Override
                    public void onSuccess(NoteBean result) {
                        NoteDetailData.setValue(result);
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        NoteDetailData.setValue(null);
                    }
                });
        return NoteDetailData;
    }

    //更改笔记
    public void update(int id, String nowDate) {
        NetWorkManager.getRequest().updateNote(id, nowDate, title.get(), details.get())
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new NoDataBaseObserver() {

                    @Override
                    public void onSuccess(String result) {
                        updateData.setValue(result);
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        updateData.setValue(errorMessage);
                    }
                });
    }
}
