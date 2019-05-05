package com.wanandroidapp.Adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroidapp.ListViewClass;
import com.wanandroidapp.R;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<ListViewClass.DataBean.DatasBean,BaseViewHolder> {
    
    public HomeAdapter( List<ListViewClass.DataBean.DatasBean> data) {
        super(R.layout.shou_list_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListViewClass.DataBean.DatasBean item) {
        helper.setImageResource(R.id.list_image,R.drawable.shengdanlaoren).setImageResource(R.id.item_heart,R.drawable.ltem_heart).addOnClickListener(R.id.item_heart);//圣诞老人
        helper.setText(R.id.item_title,item.getTitle()).//标题名
                setText(R.id.author_name,item.getAuthor()).//作者名
                setText(R.id.class_name,item.getChapterName()).//分类
               setText(R.id.time_name,item.getNiceDate());//时间


    }
}
