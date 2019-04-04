package com.yiya.foryou.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yiya.foryou.R;
import com.yiya.foryou.adapter.HomeAdapter;
import com.yiya.foryou.app.App;
import com.yiya.foryou.base.BaseFragment;
import com.yiya.foryou.bean.NoteLisBean;
import com.yiya.foryou.databinding.FragmentHomeBinding;
import com.yiya.foryou.dialog.NormalDialog;
import com.yiya.foryou.utils.AppConstants;
import com.yiya.foryou.utils.L;
import com.yiya.foryou.utils.PerfectClickListener;
import com.yiya.foryou.utils.SPUtil;
import com.yiya.foryou.utils.T;
import com.yiya.foryou.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:37
 * description:
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    private HomeAdapter homeAdapter;
    private List<NoteLisBean.DataBean> beanList;
    private String uid;
    private int LongPosition;

    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        bindingView.add.setOnClickListener(clickListener);

        beanList = new ArrayList<>();
        bindingView.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(R.layout.home_item, beanList);
        homeAdapter.setOnItemClickListener(itemClickListener);
        homeAdapter.setOnItemLongClickListener(itemLongClickListener);
        homeAdapter.setOnLoadMoreListener(loadMoreListener, bindingView.recycleView);
        bindingView.recycleView.setAdapter(homeAdapter);
        bindingView.refresh.setOnRefreshListener(refreshListener);
        uid = (String) SPUtil.get(App.getInstance(), AppConstants.KEY_UID, "");

        onRefresh();
    }

    /**
     * 列表点击
     */
    private HomeAdapter.OnItemClickListener itemClickListener = (adapter, view, position) -> {
        int id = beanList.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        startIntentForResult(DetailActivity.class, bundle, 1);
    };

    /**
     * 列表长按暗处
     */
    private HomeAdapter.OnItemLongClickListener itemLongClickListener = (adapter, view, position) -> {

        this.LongPosition = position;
        Bundle bundle = new Bundle();
        bundle.putString("title", "提示");
        bundle.putString("message", "您确定要删除吗？");
        final NormalDialog normalDialog = new NormalDialog();
        normalDialog.setArguments(bundle);
        normalDialog.show(getFragmentManager(), "normalDialog");
        normalDialog.setDialogBackCall(new NormalDialog.DialogBackCall() {
            @Override
            public void onConfirm() {
                int id = beanList.get(position).getId();
                viewModel.delete(id).observe(getActivity(), okBean -> {
                    if (okBean != null) {
                        homeAdapter.remove(LongPosition);
                        homeAdapter.notifyDataSetChanged();
                        T.showShort(App.getInstance(), "更新成功");
                    }
                });
            }

            @Override
            public void onCancel() {

            }
        });
        return false;
    };
    /**
     * 载入更多
     */
    private HomeAdapter.RequestLoadMoreListener loadMoreListener = () -> {
        L.d("载入更多");
        viewModel.setPage(viewModel.getPage() + 1);
        onRefresh();
    };

    /**
     * 刷新
     */
    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        viewModel.setPage(1);
        onRefresh();
    };

    @Override
    protected void onRefresh() {
        viewModel.getNoteLis(uid, viewModel.getPage()).observe(this, noteLisBean -> {
            showContentView();
            if (noteLisBean != null) {
                if (bindingView.refresh.isRefreshing()) {
                    bindingView.refresh.setRefreshing(false);
                }
                if (viewModel.getPage() == 1) {
                    beanList.clear();
                    beanList.addAll(noteLisBean.getData());
                    homeAdapter.notifyDataSetChanged();
                    homeAdapter.loadMoreComplete();
                    bindingView.refresh.setRefreshing(false);
                    if (noteLisBean.getData().size() < 10) {
                        homeAdapter.loadMoreEnd();
                    }
                } else {
                    beanList.addAll(noteLisBean.getData());
                    homeAdapter.notifyDataSetChanged();
                    if (noteLisBean.getData().size() < 10) {
                        homeAdapter.loadMoreEnd();
                    } else {
                        homeAdapter.loadMoreComplete();
                    }
                }
            } else {
                if (viewModel.getPage() == 1) {
                    showError();
                } else {
                    homeAdapter.loadMoreEnd();
                }
            }
        });
    }

    private PerfectClickListener clickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    startIntentForResult(AddActivity.class, null, 1);
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                L.d("运行了吗");
                onRefresh();
                break;
        }
    }
}
