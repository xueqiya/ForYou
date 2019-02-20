package com.yiya.qq.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yiya.qq.R;
import com.yiya.qq.databinding.ToutiaoItemBinding;
import com.yiya.qq.model.bean.TouTiaoBean;

import java.util.ArrayList;

/**
 * @author xueqi
 * @emil jiaowoxueqiya@gmail.com
 * create at 2019/2/20	15:43
 * description:
 */
public class TouTiaoAdapter extends BaseQuickAdapter<TouTiaoBean.DataBean, BaseViewHolder> {

    public TouTiaoAdapter(int layoutResId, @Nullable ArrayList<TouTiaoBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TouTiaoBean.DataBean item) {
        ToutiaoItemBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.setBean(item);
        binding.executePendingBindings();
    }
}