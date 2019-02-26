package com.yiya.qq.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yiya.qq.R;
import com.yiya.qq.adapter.HomeAdapter;
import com.yiya.qq.base.BaseFragment;
import com.yiya.qq.bean.HomeBean;
import com.yiya.qq.databinding.FragmentHomeBinding;
import com.yiya.qq.utils.L;
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
        homeAdapter.setOnItemClickListener(itemClickListener);
        bindingView.recycleView.setAdapter(homeAdapter);
        viewModel.getHome();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getHome().observe(this, homeBeans -> {
            showContentView();
            if (homeBeans == null) {
                showError();
                return;
            }
            homeList.addAll(homeBeans);
            homeAdapter.notifyDataSetChanged();
        });
    }

    private HomeAdapter.OnItemClickListener itemClickListener = (adapter, view, position) -> {
        homeList.get(position).getId();
        Bundle bundle = new Bundle();
        bundle.putString("title", "资讯");
        bundle.putString("url", homeList.get(position).getImg());
        startIntent(bundle, WebActitvity.class);
    };

    @Override
    protected void onRefresh() {
        viewModel.getHome();
    }
}
