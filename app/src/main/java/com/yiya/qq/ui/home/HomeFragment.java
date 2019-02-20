package com.yiya.qq.ui.home;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.yiya.qq.R;
import com.yiya.qq.adapter.TouTiaoAdapter;
import com.yiya.qq.base.BaseFragment;
import com.yiya.qq.databinding.FragmentHomeBinding;
import com.yiya.qq.model.bean.BaseBean;
import com.yiya.qq.model.bean.TouTiaoBean;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        bindingView.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bindingView.recycleView.setItemAnimator(null);
        touTiaoAdapter = new TouTiaoAdapter(R.layout.toutiao_item, touTiaoList);
        viewModel.getTouTiao();

        viewModel.getTouTiao().observe(this, new Observer<TouTiaoBean>() {
            @Override
            public void onChanged(@Nullable TouTiaoBean touTiaoBean) {
                showContentView();
                if (touTiaoBean == null) {
                    return;
                }
                touTiaoList.addAll(touTiaoBean.getData());
                touTiaoAdapter.notifyDataSetChanged();
            }
        });
    }
}
