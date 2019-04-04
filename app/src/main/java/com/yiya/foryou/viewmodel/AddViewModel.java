package com.yiya.foryou.viewmodel;

import android.app.Application;

import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.bean.OkBean;
import com.yiya.foryou.data.model.NoteModel;
import com.yiya.foryou.http.api.NetWorkManager;
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

    private final NoteModel noteModel;
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> details = new ObservableField<>();

    public AddViewModel(@NonNull Application application) {
        super(application);
        noteModel = new NoteModel();
    }

    public MutableLiveData<OkBean> add(String uid, String date) {
        return noteModel.add(uid, date, title.get(), details.get());
    }
}
