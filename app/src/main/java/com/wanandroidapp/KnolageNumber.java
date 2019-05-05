package com.wanandroidapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.wanandroidapp.Adapter.KnolageAdapter;
import com.wanandroidapp.Adapter.KnolageSection;
import com.wanandroidapp.jiexi.KnolageClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class KnolageNumber extends Fragment{
    private RecyclerView knolage_list;
    private String mUrl="https://www.wanandroid.com/tree/json";
    private List<KnolageClass.DataBean> dDataBean  = new ArrayList<>();

   // private List<KnolageClass.DataBean.ChildrenBean> cChild = new ArrayList<>();
   // private  List<String> mString  = new ArrayList<>();
    private KnolageAdapter adapter;
    View nodata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_knolage_number, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sendRequestResponse();





    }

    private void setListData() {
        if(dDataBean==null||dDataBean.size()==0){
            adapter.setEmptyView(nodata);
            adapter.setNewData(new ArrayList<KnolageSection>());
        }else {
            adapter.setNewData(initList());
        }
    }


    private List<KnolageSection> initList() {
        List<KnolageSection> list = new ArrayList<>();
//        if(dDataBean.size()==0){
//            return list;
//        }
        for(int i=0;i<dDataBean.size();i++){

            KnolageSection knolageSectionHead = new KnolageSection(true,dDataBean.get(i).getName());
            list.add(knolageSectionHead);
            if(dDataBean.get(i).getChildren().size()!=0){
               for(int j=0;j<dDataBean.get(i).getChildren().size();j++){
                   KnolageSection knolageSectionText = new KnolageSection(dDataBean.get(i).getChildren().get(j));
                   list.add(knolageSectionText);
               }
            }

        }
        return list;
    }

    private void sendRequestResponse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http.sendOkHttpRequest(mUrl, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("KnolageRequest","not connection"+e);
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                             String responseData = response.body().string();
                             parseJSONWith(responseData);
                    }
                });
            }
        }).start();
    }
    private void parseJSONWith(String responseData) {
        KnolageClass mKnolageClass = new Gson().fromJson(responseData, KnolageClass.class);

        //KnolageClass.DataBean dataBeanclass = new KnolageClass.DataBean();
        List<KnolageClass.DataBean> mDataBean = mKnolageClass.getData();//获取标题
        for (KnolageClass.DataBean dataBean : mDataBean) {//databeans中有所有的数据  1步
            String name = dataBean.getName();//标题
            KnolageClass.DataBean dataBean1 = new KnolageClass.DataBean(name);
            List<KnolageClass.DataBean.ChildrenBean> childrenBeans = new ArrayList<>();
            List<KnolageClass.DataBean.ChildrenBean> mChildenBean = dataBean.getChildren();
            for (KnolageClass.DataBean.ChildrenBean childrenBean : mChildenBean) {//childbean中所有的文本数据
                KnolageClass.DataBean.ChildrenBean childrenBean1 = new KnolageClass.DataBean.ChildrenBean(childrenBean.getName(),childrenBean.getId());

                Log.i("hangbaba","文本类所有数据"+childrenBean);
                childrenBeans.add(childrenBean1);//
                dataBean1.setChildren(childrenBeans);

            }
            dDataBean.add(dataBean1);//获取所有的数据 包括孩子
            initView();


        }
        //initView();
        Log.i("hangbaba","data所有数据"+dDataBean);
    }
    private void initView() {
        Activity activity = getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                knolage_list = getActivity().findViewById(R.id.knolage_listview);
                nodata = LayoutInflater.from(getActivity()).inflate(R.layout.empty_layout, (ViewGroup) knolage_list.getParent(),false);
                adapter = new KnolageAdapter(R.layout.knolage_listtext,R.layout.kenlage_listitem,new ArrayList<KnolageSection>());
                knolage_list.setLayoutManager(new GridLayoutManager(getActivity(),3));
                knolage_list.setAdapter(adapter);
                setListData();
                adapter.addFooterView(footView());
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        KnolageClass.DataBean.ChildrenBean mChild = ((KnolageSection)adapter.getItem(position)).t;
                       Intent intent = new Intent(getActivity(),Knolage_Intent_Activity.class);
                        intent.setAction("action");
                      intent.putExtra("intent_image",mChild.getId());
                        getActivity().startActivity(intent);

                        Toast.makeText(getActivity(),"选择了"+mChild.getId(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private View footView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_foot,null);
        TextView textView = view.findViewById(R.id.foot_text);
        return view;
    }


//    @Override
//    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//       KnolageClass.DataBean.ChildrenBean mChild = ((KnolageSection)adapter.getItem(position)).t;
//        Toast.makeText(getActivity(),"选择了"+mChild.getId(),Toast.LENGTH_SHORT).show();
//    }


//          for (KnolageClass.DataBean.ChildrenBean childrenBean:mChildenBean){
//              String titlename = childrenBean.getName();//获取所有子名称
//              int lianjie  = childrenBean.getId();
//              KnolageClass.DataBean.ChildrenBean children = new KnolageClass.DataBean.ChildrenBean(titlename,lianjie);
//                 childrenBeans.add();
//                 dataBeanclass.getChildren(childrenBeans);

        }
            // KnolageClass.DataBean a = new KnolageClass.DataBean(name);
        //  dDataBean.add(a);
        //  List<KnolageClass.DataBean.ChildrenBean> mChildenBean = dataBean.getChildren();


//        KnolageClass mKnolageClass = new Gson().fromJson(responseData,KnolageClass.class);
//        List<KnolageClass.DataBean> mDataBean = mKnolageClass.getData();
//        for(KnolageClass.DataBean dataBean :mDataBean){
//             name = dataBean.getName();//获取所有的父名称
//            KnolageClass.DataBean a = new KnolageClass.DataBean(name);
//           dDataBean.add(a);
//            Log.i("xueyuhang","title name is "+name);
//            List<KnolageClass.DataBean.ChildrenBean> cChildBean = new ArrayList<>();
//            List<KnolageClass.DataBean.ChildrenBean> mChildenBean = dataBean.getChildren();
//            for (KnolageClass.DataBean.ChildrenBean childrenBean:mChildenBean){
//                String titlename = childrenBean.getName();//获取所有子名称
//                int lianjie  = childrenBean.getId();
//                KnolageClass.DataBean.ChildrenBean children = new KnolageClass.DataBean.ChildrenBean(titlename,lianjie);
//                    cChildBean.add(children);
//                }




