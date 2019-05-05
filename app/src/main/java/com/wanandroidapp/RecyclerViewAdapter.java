package com.wanandroidapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder>{

    private List<ListViewClass.DataBean.DatasBean> list = null;
    private LayoutInflater inflater;
    /**
     *
     * 定义此构造方法 是为了接收数据 和 Context
     */
    public RecyclerViewAdapter(Context context, List<ListViewClass.DataBean.DatasBean> list) {
        inflater = LayoutInflater.from(context);
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.shou_list_item, viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.list_image.setImageResource(R.drawable.shengdanlaoren);
        viewHolder.mTextView_title.setText(list.get(position).getTitle());//获取标题
        viewHolder. mTextView_author_name.setText(list.get(position).getAuthor());//获取作者名
        viewHolder.  mTextView_classname.setText(list.get(position).getChapterName());//获取分类
        viewHolder. mTextView_timesta.setText(list.get(position).getNiceDate());//获取时间
        Log.i("student","时间"+list.get(position).getNiceDate());
        viewHolder. item_heart.setImageResource(R.drawable.ltem_heart);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView_title;
        private TextView mTextView_author;
        private TextView mTextView_author_name;
        private TextView mTextView_class;
        private TextView mTextView_classname;
        private TextView mTextView_time;
        private TextView mTextView_timesta;
        private ImageView list_image;
        private ImageView item_heart;
        public ViewHolder(@NonNull View view) {
            super(view);
            mTextView_title = view.findViewById(R.id.item_title);
            mTextView_author = view.findViewById(R.id.item_author);
            mTextView_author_name = view.findViewById(R.id.author_name);
            mTextView_class = view.findViewById(R.id.item_class);
            mTextView_classname = view.findViewById(R.id.class_name);
            mTextView_time = view.findViewById(R.id.item_time);
            mTextView_timesta = view.findViewById(R.id.time_name);
            list_image = view.findViewById(R.id.list_image);
            item_heart = view.findViewById(R.id.item_heart);
        }
    }
}
