package com.wanandroidapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wanandroidapp.jiexi.ProjectClass;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private TextView textView;

    private LayoutInflater inflater;
    List<ProjectClass.DataBean> projectClasses;

    public ListViewAdapter(Context context,List<ProjectClass.DataBean> ss){
        inflater = LayoutInflater.from(context);
        this.projectClasses = ss;
    }
    @Override
    public int getCount() {
        return projectClasses.size() ;
    }
    @Override
    public Object getItem(int position) {
        return projectClasses.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.project_recyclerview,parent,false);
        textView = view.findViewById(R.id.project_item_text);
        textView.setText(projectClasses.get(position).getName());
        return view;
    }
}
