package com.yiya.foryou.data.model;


import com.yiya.foryou.bean.NoteBean;
import com.yiya.foryou.bean.NoteLisBean;
import com.yiya.foryou.bean.OkBean;
import com.yiya.foryou.http.api.NetWorkManager;
import com.yiya.foryou.utils.L;
import com.yiya.foryou.utils.RxUtils;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/26	9:11
 * description:
 */
public class NoteModel {

    //获取列表
    public MutableLiveData<NoteLisBean> getNoteLis(String uid, int page) {
        MutableLiveData<NoteLisBean> noteBeanLiveData = new MutableLiveData<>();
        L.d("page" + page);
        NetWorkManager.getRequest().notice(uid, page, 10)
                //.compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Observer<NoteLisBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NoteLisBean noteLisBean) {
                        noteBeanLiveData.setValue(noteLisBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        noteBeanLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return noteBeanLiveData;
    }

    //添加
    public MutableLiveData<OkBean> add(String uid, String date, String title, String details) {
        MutableLiveData<OkBean> okBeanLiveData = new MutableLiveData<>();
        NetWorkManager.getRequest().saveNote(uid, date, title, details)
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Observer<OkBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OkBean okBean) {
                        okBeanLiveData.setValue(okBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        okBeanLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return okBeanLiveData;
    }

    //删除
    public MutableLiveData<OkBean> delete(int id) {
        MutableLiveData<OkBean> okBeanLiveData = new MutableLiveData<>();
        NetWorkManager.getRequest().deleteNote(id)
                //.compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Observer<OkBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OkBean okBean) {
                        okBeanLiveData.setValue(okBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        okBeanLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return okBeanLiveData;
    }

    //获取详情
    public MutableLiveData<NoteBean> getNoteDetail(int id) {
        MutableLiveData<NoteBean> NoteDetailData = new MutableLiveData<>();
        NetWorkManager.getRequest().findNoteBeanById(id)
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Observer<NoteBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NoteBean noteBean) {
                        NoteDetailData.setValue(noteBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        NoteDetailData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return NoteDetailData;
    }

    //更改笔记
    public MutableLiveData<OkBean> update(int id, String nowDate, String title, String details) {
        MutableLiveData<OkBean> okBeanLiveData = new MutableLiveData<>();
        NetWorkManager.getRequest().updateNote(id, nowDate, title, details)
                .compose(RxUtils.schedulersTransformer())
                .subscribe(new Observer<OkBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OkBean okBean) {
                        okBeanLiveData.setValue(okBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        okBeanLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return okBeanLiveData;
    }
}
