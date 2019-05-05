package com.wanandroidapp;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import com.wanandroidapp.Adapter.ProjectAdapter;
import com.wanandroidapp.Adapter.ProjectSecondAdapter;

import com.wanandroidapp.jiexi.ProjectClass;
import com.wanandroidapp.jiexi.ProjectSecond;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;



public class ProjectNumber extends Fragment{
    private RecyclerView recyclerView;
    public ProjectClass.DataBean PdataBean;
    private List<ProjectClass.DataBean> dDataBean = new ArrayList<>();
  public String mUrl = "https://www.wanandroid.com/project/tree/json";
  public String mUrls = "https://www.wanandroid.com/project/list/1/json?cid=";
  public long secondsrc = 294;
  private ProjectSecond.DataBean.DatasBean mmSecondData;
  private List<ProjectSecond.DataBean.DatasBean> mmSecondDatasBean = new ArrayList<>();
  private RecyclerView secondItem;
    ProjectAdapter adapter;
    int wowtype = 0;
    List<ProjectSecond.DataBean.DatasBean> secondBean;

  List<String> teams = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_number, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       sendSecondRequestWith();
        sendRequestWith();

    }
    private void sendSecondRequestWith() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.i("projectsecond","wnagzhi"+mUrls+secondsrc);
                Http.sendOkHttpRequest(mUrls+secondsrc, new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("projectsecond","canot connection"+e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();

                        parseSecondJSONWith(responseData);

                    }
                });
            }
        }).start();
    }

    private void initSecondView() {
        Activity activity = getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                  secondItem =getActivity().findViewById(R.id.project_rec);
                  secondItem.setLayoutManager(new LinearLayoutManager(getActivity()));
                   adapter  = new ProjectAdapter(mmSecondDatasBean);
                Log.i("projectsecond","我不知道"+mmSecondDatasBean);
                  secondItem.setAdapter(adapter);
                  adapter.addHeaderView(getWowView());
                setListData();


            }
        });
    }

    private void setListData() {
        if(mmSecondDatasBean.size()!=0){
            adapter.setNewData(initData());
        }
    }

    private List<ProjectSecond.DataBean.DatasBean> initData() {

//        for (int i=0;i<mmSecondDatasBean.size();i++){
//            ProjectSecond.DataBean.DatasBean datasBean1 = mmSecondDatasBean.get(i);
//            datasBean.add(datasBean1);
//
//        }
        //datasBean.add(secondBean)
       //datasBean.add(mmSecondData);
        Log.i("conntent","让我看看你有几个"+secondBean);

        return secondBean;
    }

    private View getWowView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.project_top,null);
        RecyclerView recyclerViewhaha = view.findViewById(R.id.haha);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewhaha.setLayoutManager(manager);
        ProjectSecondAdapter adapter = new ProjectSecondAdapter(dDataBean);
        recyclerViewhaha.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProjectClass.DataBean dataBean = (ProjectClass.DataBean) adapter.getItem(position);
                secondsrc = dataBean.getId();
                sendSecondRequestWith();


            }
        });
       // adapter.notifyDataSetChanged();

        return  view;

    }


    private void parseSecondJSONWith (String responseData){
            ProjectSecond mProjectSecond = new Gson().fromJson(responseData, ProjectSecond.class);
            ProjectSecond.DataBean secondData = mProjectSecond.getData();
            List<ProjectSecond.DataBean.DatasBean> secondDatasBean = secondData.getDatas();
        secondBean=new ArrayList<>();
            for (ProjectSecond.DataBean.DatasBean secondDatass : secondDatasBean) {
                String phone = secondDatass.getEnvelopePic();
                String title = secondDatass.getTitle();
                String text = secondDatass.getDesc();
                String time = secondDatass.getNiceDate();
                String author = secondDatass.getAuthor();
                mmSecondData = new ProjectSecond.DataBean.DatasBean(phone, title, text, time, author);
                secondBean.add(mmSecondData);
                    mmSecondDatasBean.add(mmSecondData);
               // mmSecondDatasBean.add(mmSecondData);
            }
            Log.i("conntent","初始数据"+secondBean);
            initSecondView();
        }

        private void sendRequestWith () {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Http.sendOkHttpRequest(mUrl, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i("project", "canot connection" + e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData = response.body().string();
                            parseJSONWith(responseData);
                            //initView();
                        }
                    });
                }
            }).start();
        }
//

    private void parseJSONWith (String responseData){
        ProjectClass mProjectClass = new Gson().fromJson(responseData, ProjectClass.class);
        List<ProjectClass.DataBean> mDataBean = mProjectClass.getData();
        for (ProjectClass.DataBean dataBean : mDataBean) {
            String name = dataBean.getName();
            int id = dataBean.getId();
            Log.i("project", "number" + name + "id" + id);
            PdataBean = new ProjectClass.DataBean(id, name);
            teams.add(name);
            dDataBean.add(dataBean);
        }

    }
//
//        private void initView () {
//            Activity activity = getActivity();
//            activity.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    recyclerView = getActivity().findViewById(R.id.project_hor);
//                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//                    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                    recyclerView.setLayoutManager(manager);
//                    ProjectAdapter adapter = new ProjectAdapter(dDataBean);
//                    recyclerView.setAdapter(adapter);
//                }
//            });
//        }


}
