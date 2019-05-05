package com.wanandroidapp.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroidapp.R;
import com.wanandroidapp.jiexi.ProjectClass;
import com.wanandroidapp.jiexi.ProjectSecond;

import java.util.List;

public class ProjectAdapter extends BaseQuickAdapter<ProjectSecond.DataBean.DatasBean,BaseViewHolder> {
    public ProjectAdapter(@Nullable List<ProjectSecond.DataBean.DatasBean> data) {
        super(R.layout.peoject_second_item,data);
    }


    //   Glide.with(inflater.getContext()).load(mSeconddata.get(position).getEnvelopePic()).into(viewHolder.second_phone); //加载图片

    @Override
    protected void convert(BaseViewHolder helper, ProjectSecond.DataBean.DatasBean item) {
        helper.setImageResource(R.id.project_second_img,R.drawable.shengdanlaoren);
        helper.setText(R.id.project_second_title,item.getTitle())//标题
                .setText(R.id.project_second_text,item.getDesc())
                .setText(R.id.project_second_time,item.getNiceDate())
                .setText(R.id.project_second_author,item.getAuthor());
        Glide.with(mContext).load(item.getEnvelopePic()).into((ImageView) helper.getView(R.id.project_second_phone));
    }

}
