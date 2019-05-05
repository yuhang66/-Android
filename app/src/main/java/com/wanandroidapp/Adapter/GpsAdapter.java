package com.wanandroidapp.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.wanandroidapp.R;
import com.wanandroidapp.jiexi.GpsClass;

import java.util.List;

public class GpsAdapter extends BaseSectionQuickAdapter<GpsSection,BaseViewHolder>{


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public GpsAdapter(int layoutResId, int sectionHeadResId, List<GpsSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, GpsSection item) {
         helper.setText(R.id.gps_title,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, GpsSection item) {
        GpsClass.DataBean.ArticlesBean articlesBean = item.t;
        helper.setText(R.id.gps_text,articlesBean.getTitle());
    }
}
