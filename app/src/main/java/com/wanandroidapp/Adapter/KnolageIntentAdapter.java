package com.wanandroidapp.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroidapp.R;
import com.wanandroidapp.jiexi.KnolageIntentClass;

import java.util.List;

public class KnolageIntentAdapter extends BaseQuickAdapter<KnolageIntentClass.DataBean.DatasBean,BaseViewHolder> {
    public KnolageIntentAdapter(@Nullable List<KnolageIntentClass.DataBean.DatasBean> data) {
        super(R.layout.knolage_intent_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnolageIntentClass.DataBean.DatasBean item) {
           helper.setImageResource(R.id.intent_image,R.drawable.shengdanlaoren);
           helper.setText(R.id.intent_title,item.getTitle())//标题
                   .setText(R.id.intent_author_name,item.getAuthor())//作者
                   .setText(R.id.intent_class_name,item.getChapterName())//分类
                   .setText(R.id.intent_time_name,item.getNiceDate());//时间

    }
}
