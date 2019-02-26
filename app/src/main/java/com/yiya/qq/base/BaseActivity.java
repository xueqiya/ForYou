package com.yiya.qq.base;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Build;

import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.ActionBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.squareup.leakcanary.RefWatcher;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yiya.qq.R;
import com.yiya.qq.app.App;
import com.yiya.qq.databinding.ActivityBaseBinding;
import com.yiya.qq.dialog.DefaultProgress;
import com.yiya.qq.utils.ClassUtil;

public abstract class BaseActivity<SV extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity {
    private DefaultProgress defaultProgress;
    // ViewModel
    protected VM viewModel;
    // 布局view
    protected SV bindingView;
    private View refresh;
    private View loadingView;
    protected ActivityBaseBinding mBaseBinding;

    protected  View getView(int id) {
        return  findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RefWatcher refWatcher = App.getRefWatcher(this);
        refWatcher.watch(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

        refresh = getView(R.id.ll_error_refresh);
        loadingView = getView(R.id.progress);
        setToolBar();

        bindingView.getRoot().setVisibility(View.GONE);
        showLoading();
        initViewModel();
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
        initView();
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        setSupportActionBar(mBaseBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        mBaseBinding.toolBar.setNavigationOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                onBackPressed();
            }
        });
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

    @Override
    public void setTitle(CharSequence text) {
        mBaseBinding.toolBar.setTitle(text);
    }

    protected void showLoading() {
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (loadingView.getVisibility() != View.VISIBLE) {
            loadingView.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (refresh.getVisibility() != View.VISIBLE) {
            refresh.setVisibility(View.VISIBLE);
        }
        if (loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    public void showProgress() {
        if (defaultProgress == null) {
            defaultProgress = new DefaultProgress();
            defaultProgress.setStyle(DialogFragment.STYLE_NORMAL, R.style.TransparentDialogStyle);
            defaultProgress.show(getSupportFragmentManager(), "defaultProgress");
        } else {
            defaultProgress.show(getSupportFragmentManager(), "defaultProgress");
        }
    }

    public void hideProgress() {
        if (defaultProgress != null) {
            defaultProgress.dismiss();
        }
    }

    /**
     * 初始化view
     */
    public abstract void initView();

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }
}
