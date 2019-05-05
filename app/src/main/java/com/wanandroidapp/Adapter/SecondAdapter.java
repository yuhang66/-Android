package com.wanandroidapp.Adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroidapp.R;
import com.wanandroidapp.jiexi.SecondClass;

import java.util.List;

public class SecondAdapter extends BaseQuickAdapter<SecondClass.DataBean.DatasBean,BaseViewHolder> {
    public SecondAdapter(@Nullable List<SecondClass.DataBean.DatasBean> data) {
        super(R.layout.second_recyclerview_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecondClass.DataBean.DatasBean item) {
        helper.setImageResource(R.id.second_intent_img,R.drawable.shengdanlaoren).setImageResource(R.id.second_intent_heart,R.drawable.ltem_heart);
        helper.setText(R.id.second_intent_title,item.getTitle())//标题
                .setText(R.id.second_intent_name,item.getAuthor())//作者
                .setText(R.id.second_intent_classname,item.getSuperChapterName())//分类
                .setText(R.id.second_intent_timename,item.getNiceDate());//时间

    }
}
