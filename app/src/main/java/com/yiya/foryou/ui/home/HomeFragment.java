package com.yiya.foryou.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiya.foryou.R;
import com.yiya.foryou.adapter.HomeAdapter;
import com.yiya.foryou.app.App;
import com.yiya.foryou.base.BaseFragment;
import com.yiya.foryou.bean.NoteBean;
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

import androidx.annotation.Nullable;
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
    private List<NoteBean> beanList;
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
        viewModel.setPage(1);
        viewModel.getHome(uid, viewModel.getPage());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.listData.observe(this, homeBeans -> {
            showContentView();
            if (homeBeans != null) {
                if (bindingView.refresh.isRefreshing()) {
                    bindingView.refresh.setRefreshing(false);
                }
                if (viewModel.getPage() == 1) {
                    beanList.clear();
                    beanList.addAll(homeBeans);
                    homeAdapter.notifyDataSetChanged();
                    homeAdapter.loadMoreComplete();
                    bindingView.refresh.setRefreshing(false);
                    if (homeBeans.size() < 10) {
                        homeAdapter.loadMoreEnd();
                    }
                } else {
                    beanList.addAll(homeBeans);
                    homeAdapter.notifyDataSetChanged();
                    if (homeBeans.size() < 10) {
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
        viewModel.deleteData.observe(this, successBean -> {
            if (successBean != null) {
                homeAdapter.remove(LongPosition);
                homeAdapter.notifyDataSetChanged();
                T.showShort(App.getInstance(), "更新成功");
            }
        });
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
                viewModel.delete(id);
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
        viewModel.getHome(uid, viewModel.getPage());
    };

    /**
     * 刷新
     */
    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        viewModel.setPage(1);
        viewModel.getHome(uid, viewModel.getPage());
    };

    /**
     * 加载失败时点击刷新
     */
    @Override
    protected void onRefresh() {
        viewModel.setPage(1);
        viewModel.getHome(uid, viewModel.getPage());
    }

    private PerfectClickListener clickListener = new PerfectClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    startIntentForResult(DetailActivity.class, null, 2);
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
            case 2:
                onRefresh();
                break;
        }
    }
}
