package com.wanandroidapp.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroidapp.R;
import com.wanandroidapp.jiexi.HotSearch;

import java.util.List;

public class HotSearchAdapter extends BaseQuickAdapter<HotSearch.DataBean,BaseViewHolder> {
    public HotSearchAdapter(@Nullable List<HotSearch.DataBean> data) {
        super(R.layout.search_hot,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotSearch.DataBean item) {
          helper.setText(R.id.hot_text,item.getName());
    }
}
