package com.yiya.qq.viewmodel;

import android.app.Application;

import com.yiya.qq.base.BaseViewModel;
import com.yiya.qq.bean.NoteBean;
import com.yiya.qq.http.api.NetWorkManager;
import com.yiya.qq.http.baseobserver.BaseObserver;
import com.yiya.qq.http.baseobserver.ListBaseObserver;
import com.yiya.qq.utils.L;
import com.yiya.qq.utils.RxUtils;

import java.util.List;

import androidx.annotation.NonNull;
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

    public MutableLiveData<NoteBean> getNoteDetail(int id) {
        MutableLiveData<NoteBean> listData = new MutableLiveData<>();
        NetWorkManager.getRequest().findNoteBeanById(id)
                //.compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new BaseObserver<NoteBean>() {

                    @Override
                    public void onSuccess(NoteBean result) {
                        listData.setValue(result);
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        if (mPage < 1) {
                            mPage--;
                        }
                        listData.setValue(null);
                    }
                });
        return listData;
    }
}
