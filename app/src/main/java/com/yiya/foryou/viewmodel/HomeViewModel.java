package com.yiya.foryou.viewmodel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.yiya.foryou.bean.NoteBean;
import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.http.api.NetWorkManager;
import com.yiya.foryou.http.baseobserver.ListBaseObserver;
import com.yiya.foryou.http.baseobserver.NoDataBaseObserver;
import com.yiya.foryou.utils.L;
import com.yiya.foryou.utils.RxUtils;

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

    public MutableLiveData<List<NoteBean>> listData = new MutableLiveData<>();
    public MutableLiveData<String> deleteData = new MutableLiveData<>();

    public void getHome(String uid,int page) {
        L.d("page" + page);
        NetWorkManager.getRequest().notice(uid,page, 10)
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
    }

    public  void delete(int id) {
        NetWorkManager.getRequest().deleteNote(id)
                //.compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new NoDataBaseObserver() {

                    @Override
                    public void onSuccess(String success) {
                        deleteData.setValue(success);
                    }

                    @Override
                    public void onFailure(int code, String errorMessage) {
                        deleteData.setValue(null);
                    }
                });
    }
}
