package com.yiya.qq.viewmodel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.yiya.qq.bean.NoteBean;
import com.yiya.qq.base.BaseViewModel;
import com.yiya.qq.http.api.NetWorkManager;
import com.yiya.qq.http.baseobserver.ListBaseObserver;
import com.yiya.qq.utils.L;
import com.yiya.qq.utils.RxUtils;

import java.util.List;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:44
 * description:
 */
public class HomeViewModel extends BaseViewModel {

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<List<NoteBean>> getHome(int page) {
        MutableLiveData<List<NoteBean>> listData = new MutableLiveData<>();
        L.d("page" + page);
        NetWorkManager.getRequest().notice(page, 10)
                //.compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new ListBaseObserver<NoteBean>() {

                    @Override
                    public void onSuccess(List<NoteBean> result) {
                        listData.setValue(result);
//                        for (int i = 0; i < result.size(); i++) {
//                            AppDatabase.getDatabase().homeDao().insertTitle(result.get(i));
//                        }
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
