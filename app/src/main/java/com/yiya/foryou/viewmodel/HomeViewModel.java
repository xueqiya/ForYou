package com.yiya.foryou.viewmodel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.yiya.foryou.bean.NoteLisBean;
import com.yiya.foryou.base.BaseViewModel;
import com.yiya.foryou.bean.OkBean;
import com.yiya.foryou.data.model.NoteModel;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:44
 * description:
 */
public class HomeViewModel extends BaseViewModel {

    private final NoteModel noteModel;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        noteModel = new NoteModel();
    }

    public MutableLiveData<NoteLisBean> getNoteLis(String uid, int page) {
        return noteModel.getNoteLis(uid, page);
    }
    public MutableLiveData<OkBean> delete(int id) {
        return noteModel.delete(id);
    }


}
