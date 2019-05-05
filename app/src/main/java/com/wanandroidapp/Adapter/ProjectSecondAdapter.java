package com.wanandroidapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroidapp.R;
import com.wanandroidapp.jiexi.ProjectClass;
import com.wanandroidapp.jiexi.ProjectSecond;

import java.util.List;
public class ProjectSecondAdapter extends BaseQuickAdapter<ProjectClass.DataBean,BaseViewHolder>{
    public ProjectSecondAdapter(@Nullable List<ProjectClass.DataBean> data) {
        super(R.layout.project_recyclerview,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectClass.DataBean item) {
        helper.setText(R.id.project_item_text,item.getName());
    }


//public class ProjectSecondAdapter extends BaseAdapter{
//    List<ProjectSecond.DataBean.DatasBean> mSeconddata;
//    LayoutInflater inflater;
//    public ProjectSecondAdapter(Context context, List<ProjectSecond.DataBean.DatasBean> datasBeans){
//        inflater = LayoutInflater.from(context);
//        this.mSeconddata = datasBeans;
//    }
//
//    @Override
//    public int getCount() {
//        return mSeconddata.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return mSeconddata.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if(convertView==null){
//            convertView = inflater.inflate(R.layout.peoject_second_item,parent,false);
//            viewHolder = new ViewHolder();
//            viewHolder.second_phone = convertView.findViewById(R.id.project_second_phone);
//           viewHolder.second_img = convertView.findViewById(R.id.project_second_img);
//            viewHolder.second_title = convertView.findViewById(R.id.project_second_title);
//            viewHolder.second_text = convertView.findViewById(R.id.project_second_text);
//            viewHolder.second_author = convertView.findViewById(R.id.project_second_author);
//            viewHolder.second_time = convertView.findViewById(R.id.project_second_time);
//            convertView.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//      Glide.with(inflater.getContext()).load(mSeconddata.get(position).getEnvelopePic()).into(viewHolder.second_phone); //加载图片
//        viewHolder.second_img.setImageResource(R.drawable.shengdanlaoren);
//        viewHolder.second_title.setText(mSeconddata.get(position).getTitle());
//        viewHolder.second_text.setText(mSeconddata.get(position).getDesc());
//        viewHolder.second_time.setText(mSeconddata.get(position).getNiceDate());
//        viewHolder.second_author.setText(mSeconddata.get(position).getAuthor());
//
//        return convertView;
//    }
//
//  private class ViewHolder{
//       ImageView second_phone;
//       ImageView second_img;
//       TextView second_title;
//       TextView second_text;
//       TextView second_author;
//       TextView second_time;
//    }


//        RecyclerView.Adapter<ProjectSecondAdapter.Viewholder> {
//
//    List<ProjectSecond.DataBean.DatasBean> mSeconddata ;
//    LayoutInflater inflater;
// public ProjectSecondAdapter(Context context,List<ProjectSecond.DataBean.DatasBean> datasBeans){
//     inflater = LayoutInflater.from(context);
//     this.mSeconddata = datasBeans;
// }
//    @NonNull
//    @Override
//    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//   final View view = inflater.inflate(R.layout.peoject_second_item,viewGroup,false);
//      final Viewholder holder = new Viewholder(view);
//     return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(Viewholder viewholder, int i) {
//        ProjectSecond.DataBean.DatasBean ddatabeans = mSeconddata.get(i);
//        Glide.with(inflater.getContext()).load(ddatabeans.getEnvelopePic()).into(viewholder.second_phone); //加载图片
//        viewholder.second_img.setImageResource(R.drawable.shengdanlaoren);
//        viewholder.second_title.setText(ddatabeans.getTitle());
//        viewholder.second_text.setText(ddatabeans.getDesc());
//        viewholder.second_time.setText(ddatabeans.getNiceDate());
//        viewholder.second_author.setText(ddatabeans.getAuthor());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mSeconddata.size();
//    }
//
//    public class Viewholder extends RecyclerView.ViewHolder {
//        ImageView second_phone;
//        ImageView second_img;
//        TextView second_title;
//        TextView second_text;
//        TextView second_author;
//        TextView second_time;
//        public Viewholder(@NonNull View view) {
//            super(view);
//            second_phone = view.findViewById(R.id.project_second_phone);
//            second_img = view.findViewById(R.id.project_second_img);
//            second_title = view.findViewById(R.id.project_second_title);
//            second_text = view.findViewById(R.id.project_second_text);
//            second_author = view.findViewById(R.id.project_second_author);
//            second_time = view.findViewById(R.id.project_second_time);
//        }
//    }
}
