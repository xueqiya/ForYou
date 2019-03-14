package com.yiya.foryou.base;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yiya.foryou.R;
import com.yiya.foryou.utils.ClassUtil;
import com.yiya.foryou.utils.L;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/13	13:04
 * description:
 */
public abstract class BaseFragment<SV extends ViewDataBinding, VM extends BaseViewModel> extends RxFragment {

    // ViewModel
    protected VM viewModel;
    // 布局view
    protected SV bindingView;
    // 加载中
    private View loadingView;
    // 加载失败
    private LinearLayout mRefresh;
    // 内容布局
    protected RelativeLayout mContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ll = inflater.inflate(R.layout.fragment_base, null);
        bindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContent(), null, false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        mContainer = ll.findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        loadingView = ll.findViewById(R.id.progress);
        mRefresh = ll.findViewById(R.id.ll_error_refresh);
        return ll;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 点击加载失败布局
        mRefresh.setOnClickListener(v -> {
            L.d("点击刷新");
            showLoading();
            onRefresh();
        });
        showLoading();
        initViewModel();
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        initView();
    }

    /**
     * 初始化ViewModel
     */
    private void initViewModel() {
        Class<VM> viewModelClass = ClassUtil.getViewModel(this);
        if (viewModelClass != null) {
            this.viewModel = ViewModelProviders.of(this).get(viewModelClass);
        }
    }

    protected View getView(int id) {
        return getView().findViewById(id);
    }

    protected void showLoading() {
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (loadingView.getVisibility() != View.VISIBLE) {
            loadingView.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (mRefresh.getVisibility() != View.VISIBLE) {
            mRefresh.setVisibility(View.VISIBLE);
        }
        if (loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    protected void startIntent(Class c, @Nullable Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(getActivity(), c);
        startActivity(intent);
    }

    protected void startIntentForResult(Class c, @Nullable Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(getActivity(), c);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 布局
     */
    public abstract int setContent();

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 加载失败后点击后的操作
     */
    protected void onRefresh() {

    }
}
