package com.wanandroidapp.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroidapp.R;
import com.wanandroidapp.jiexi.HistoryClass;

import java.util.List;

public class HistorySearch extends BaseQuickAdapter<HistoryClass,BaseViewHolder> {
    public HistorySearch(@Nullable List<HistoryClass> data) {
        super(R.layout.history,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryClass item) {
        helper.setText(R.id.history_text,item.getText());
    }
}
