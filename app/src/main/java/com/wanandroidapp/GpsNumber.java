package com.wanandroidapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.wanandroidapp.Adapter.GpsAdapter;
import com.wanandroidapp.Adapter.GpsSection;
import com.wanandroidapp.jiexi.GpsClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class GpsNumber extends Fragment {
    private String mUrls = "https://www.wanandroid.com/navi/json";
    private List<GpsClass.DataBean> dDataBeans = new ArrayList<>();
    private RecyclerView recyclerView;
    private View nodata;
    GpsAdapter adapter;

    public GpsNumber() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gps_number, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parseJSON();
    }

    private void parseJSON() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http.sendOkHttpRequest(mUrls, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("gps", "not connection" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        parseWithHttp(responseData);
                    }
                });
            }
        }).start();
    }

    private void parseWithHttp(String responseData) {
        GpsClass gpsClass = new Gson().fromJson(responseData, GpsClass.class);
        List<GpsClass.DataBean> dataBeans = gpsClass.getData();
        List<GpsClass> gpsClasses = new ArrayList<>();
        for (GpsClass.DataBean dataBean : dataBeans) {//获取标题
            GpsClass.DataBean dataBean1 = new GpsClass.DataBean(dataBean.getName());
            List<GpsClass.DataBean.ArticlesBean> mArticlesCount = new ArrayList<>();
            List<GpsClass.DataBean.ArticlesBean> articlesBeans = dataBean.getArticles();
            for (GpsClass.DataBean.ArticlesBean mArticles : articlesBeans) {
                GpsClass.DataBean.ArticlesBean articlesBean = new GpsClass.DataBean.ArticlesBean(mArticles.getTitle(),mArticles.getLink());
                mArticlesCount.add(articlesBean);
                dataBean1.setArticles(mArticlesCount);

            }
            dDataBeans.add(dataBean1);

            initDate();

        }
    }

    private void initDate() {
        Activity activity = getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView = getActivity().findViewById(R.id.gps_recycler);
                nodata = LayoutInflater.from(getActivity()).inflate(R.layout.empty_layout, (ViewGroup) recyclerView.getParent(), false);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
                adapter = new GpsAdapter(R.layout.gps_item_text, R.layout.gps_item_title, new ArrayList<GpsSection>());
                recyclerView.setAdapter(adapter);
                setListData();
                adapter.addFooterView(getFootView());
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        GpsClass.DataBean.ArticlesBean mChild = ((GpsSection)adapter.getItem(position)).t;
                        Intent intent = new Intent(getActivity(),Knolage_Intent_web.class);
                        intent.setAction("action");
                        intent.putExtra("intent_url",mChild.getLink());
                         getActivity().startActivity(intent);
                        Toast.makeText(getActivity(),"加载中，请稍后....",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void setListData() {

                if (dDataBeans == null || dDataBeans.size() == 0) {
                    adapter.setEmptyView(nodata);
                    adapter.setNewData(new ArrayList<GpsSection>());
                } else {
                    adapter.setNewData(initList());
                }
            }

        });
    }

    private View getFootView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_foot,null);
        TextView textView = view.findViewById(R.id.foot_text);
        return view;
    }

    private List<GpsSection> initList() {
        List<GpsSection> sections = new ArrayList<>();
        for (int i = 0; i < dDataBeans.size(); i++) {

            GpsSection gpsSectionHead = new GpsSection(true, dDataBeans.get(i).getName());
            sections.add(gpsSectionHead);
            if (dDataBeans.get(i).getArticles().size() != 0) {
                for (int j = 0; j < dDataBeans.get(i).getArticles().size(); j++) {
                    GpsSection GpsSectionText = new GpsSection(dDataBeans.get(i).getArticles().get(j));
                    sections.add(GpsSectionText);
                }
            }

        }
        return sections;
    }
}