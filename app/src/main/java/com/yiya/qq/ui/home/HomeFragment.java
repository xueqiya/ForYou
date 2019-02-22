package com.yiya.qq.ui.home;

import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yiya.qq.R;
import com.yiya.qq.adapter.TouTiaoAdapter;
import com.yiya.qq.base.BaseFragment;
import com.yiya.qq.databinding.FragmentHomeBinding;
import com.yiya.qq.viewmodel.HomeViewModel;

import java.util.ArrayList;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:37
 * description:
 */
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {
    private ArrayList touTiaoList = new ArrayList();
    private TouTiaoAdapter touTiaoAdapter;

    @Override
    public int setContent() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        bindingView.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        touTiaoAdapter = new TouTiaoAdapter(R.layout.toutiao_item, touTiaoList);
        bindingView.recycleView.setAdapter(touTiaoAdapter);
        viewModel.getTouTiao();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getTouTiao().observe(this, touTiaoBeans -> {
            showContentView();
            if (touTiaoBeans == null) {
                showError();
                return;
            }
            touTiaoAdapter.addData(touTiaoBeans);
            touTiaoAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onRefresh() {
        viewModel.getTouTiao();
    }
}
