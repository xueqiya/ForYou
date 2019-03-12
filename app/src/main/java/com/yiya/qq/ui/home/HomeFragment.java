package com.yiya.qq.ui.home;

import android.os.Bundle;

import com.yiya.qq.R;
import com.yiya.qq.adapter.HomeAdapter;
import com.yiya.qq.base.BaseFragment;
import com.yiya.qq.bean.NoteBean;
import com.yiya.qq.databinding.FragmentHomeBinding;
import com.yiya.qq.utils.L;
import com.yiya.qq.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
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

    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        beanList = new ArrayList<>();
        bindingView.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(R.layout.home_item, beanList);
        homeAdapter.setOnItemClickListener(itemClickListener);
        homeAdapter.setOnLoadMoreListener(loadMoreListener, bindingView.recycleView);
        bindingView.recycleView.setAdapter(homeAdapter);
        bindingView.refresh.setOnRefreshListener(refreshListener);
        viewModel.setPage(1);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getHome(viewModel.getPage()).observe(this, homeBeans -> {
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
    }

    /**
     * 列表点击
     */
    private HomeAdapter.OnItemClickListener itemClickListener = (adapter, view, position) -> {
        int id = beanList.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        startIntent(DetailActivity.class, bundle);
    };

    /**
     * 载入更多
     */
    private HomeAdapter.RequestLoadMoreListener loadMoreListener = () -> {
        L.d("载入更多");
        viewModel.setPage(viewModel.getPage() + 1);
        viewModel.getHome(viewModel.getPage());
    };

    /**
     * 刷新
     */
    private SwipeRefreshLayout.OnRefreshListener refreshListener = () -> {
        viewModel.setPage(1);
        viewModel.getHome(viewModel.getPage());
    };

    /**
     * 加载失败时点击刷新
     */
    @Override
    protected void onRefresh() {
        viewModel.setPage(1);
        viewModel.getHome(viewModel.getPage());
    }
}
