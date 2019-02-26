package com.yiya.qq.ui.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yiya.qq.R;
import com.yiya.qq.adapter.HomeAdapter;
import com.yiya.qq.base.BaseFragment;
import com.yiya.qq.bean.HomeBean;
import com.yiya.qq.databinding.FragmentHomeBinding;
import com.yiya.qq.viewmodel.HomeViewModel;
import com.yiya.qq.webview.WebActitvity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:37
 * description:
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    private HomeAdapter homeAdapter;
    private List<HomeBean> homeList;

    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        homeList = new ArrayList();
        bindingView.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(R.layout.home_item, homeList);
        bindingView.recycleView.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener(itemClickListener);
        homeAdapter.setOnLoadMoreListener(loadMoreListener, bindingView.recycleView);
        bindingView.refresh.setOnRefreshListener(refreshListener);
        viewModel.getHome(viewModel.getPage());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.listData.observe(this, homeBeans -> {
            showContentView();
            if (homeBeans != null) {
                if (viewModel.getPage() == 1) {
                    homeList.clear();
                }
                if (homeBeans.size() < 20) {
                    homeAdapter.loadMoreEnd();
                } else {
                    homeAdapter.loadMoreComplete();
                }
                homeAdapter.addData(homeBeans);
                bindingView.refresh.setRefreshing(false);
                homeAdapter.notifyDataSetChanged();
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
        homeList.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putString("title", "资讯");
        bundle.putString("url", homeList.get(position).getImg());
        startIntent(WebActitvity.class, bundle);
    };

    /**
     * 载入更多
     */
    private HomeAdapter.RequestLoadMoreListener loadMoreListener = () -> {
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
