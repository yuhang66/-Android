package com.wanandroidapp.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroidapp.R;
import com.wanandroidapp.jiexi.KnolageClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KnolageAdapter extends BaseSectionQuickAdapter<KnolageSection,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public KnolageAdapter(int layoutResId, int sectionHeadResId, List<KnolageSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, KnolageSection item) {
        helper.setText(R.id.knolage_head,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnolageSection item) {
        KnolageClass.DataBean.ChildrenBean child = item.t;
        helper.setText(R.id.knolage_text,child.getName());
    }
}


