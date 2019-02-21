package com.yiya.qq.ui.mine;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yiya.qq.R;
import com.yiya.qq.base.BaseFragment;
import com.yiya.qq.databinding.FragmentMineBinding;
import com.yiya.qq.viewmodel.MineViewModel;


/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/19	15:39
 * description:
 */
public class MineFragment extends BaseFragment<FragmentMineBinding, MineViewModel> {
    @Override
    public int setContent() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
