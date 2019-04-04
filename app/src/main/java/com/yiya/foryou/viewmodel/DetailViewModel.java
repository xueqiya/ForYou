package com.yiya.foryou.viewmodel;

import android.app.Application;

import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.bean.NoteBean;
import com.yiya.foryou.bean.NoteLisBean;
import com.yiya.foryou.bean.OkBean;
import com.yiya.foryou.data.model.NoteModel;

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

    private final NoteModel noteModel;
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> details = new ObservableField<>();

    public DetailViewModel(@NonNull Application application) {
        super(application);
        noteModel = new NoteModel();
    }

    public MutableLiveData<NoteBean> getNoteDetail(int id) {
        return noteModel.getNoteDetail(id);
    }

    public MutableLiveData<OkBean> update(int id, String nowDate) {
        return noteModel.update(id, nowDate, title.get(), details.get());
    }

}
