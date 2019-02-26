package com.yiya.qq.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiya.qq.databinding.HomeItemBinding;
import com.yiya.qq.bean.HomeBean;

import java.util.List;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/20	15:43
 * description:
 */
public class HomeAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {

    public HomeAdapter(int layoutResId, @Nullable List<HomeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        HomeItemBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.setBean(item);
        binding.executePendingBindings();
    }
}
