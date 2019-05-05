package com.wanandroidapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.wanandroidapp.Adapter.HomeAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ShouNumber extends Fragment  {
    private Banner mBanner;
    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private String murl = "https://www.wanandroid.com/banner/json";
    private String mur2 = "https://wanandroid.com/article/listproject/0/json";
    private ListViewClass.DataBean.DatasBean sta;
    private List<ListViewClass.DataBean.DatasBean> statt = new ArrayList<>();
    private SwipeRefreshLayout refreshLayout;
    private String TAG = "students";


    private RecyclerView recyclerView;
    HomeAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shou_number, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sendOkHttpRequest();//发送banner数据请求
        sendSecondHttpRequest();//发送listview数据的请求
          refreshLayout = getActivity().findViewById(R.id.shou_swipefresh);
          refreshLayout.setColorSchemeResources(R.color.colorAccent);
          refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
              @Override
              public void onRefresh() {
                  refreshNumber();
                  adapter.setNewData(statt);
                  adapter.setEnableLoadMore(true);
                  adapter.notifyDataSetChanged();
                  refreshLayout.setRefreshing(false);
                  Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();

              }
          });

    }

    private void refreshNumber() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void sendOkHttpRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http.sendOkHttpRequest(murl, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //  Log.i("connection", "not connection" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        parseJSONWithJSON(responseData);
                      //  intedate();
                    }
                });
            }
        }).start();

    }

//    private void intedate() {
//        Activity activity = getActivity();
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mBanner = getActivity().findViewById(R.id.shou_banner);
//                mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);//设置标题和数字指示器横向排列
//                mBanner.setImageLoader(new GlideImageLoader());
//                mBanner.setImages(images);
//                mBanner.setBannerAnimation(Transformer.DepthPage);//动画效果
//                mBanner.isAutoPlay(true);
//                mBanner.setDelayTime(2000);
//                mBanner.setIndicatorGravity(BannerConfig.CENTER);//设置居中
//                mBanner.setBannerTitles(titles);
//                mBanner.setFocusable(true);//获取焦点
//                mBanner.setFocusableInTouchMode(true);//触摸是否能获取到焦点
//                mBanner.start();
//
//            }
//        });
//    }

    private void parseJSONWithJSON(String responseData) {
        Lunbo lunbo = new Gson().fromJson(responseData, Lunbo.class);
        int errorCode = lunbo.getErrorCode();
        String errorMsg = lunbo.getErrorMsg();
        List<Lunbo.DataBean> newLunbo = lunbo.getData();
        for (Lunbo.DataBean lunboBean : newLunbo) {
            images.add(lunboBean.getImagePath());
            titles.add(lunboBean.getTitle());
        }

    }




    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }

    private void sendSecondHttpRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http.sendOkHttpRequest(mur2, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //   Log.i("connection2", "connection2 lose" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();

                        ParseJSON(responseData);
                        initView();
                    }
                });
            }
        }).start();
    }


    private void ParseJSON(String responseData) {
        ListViewClass mListView = new Gson().fromJson(responseData, ListViewClass.class);

        ListViewClass.DataBean dataBean = mListView.getData();
        List<ListViewClass.DataBean.DatasBean> datasBeans = dataBean.getDatas();
        for (ListViewClass.DataBean.DatasBean datasBean : datasBeans) {
            String author = datasBean.getAuthor();
            String chapterName = datasBean.getChapterName();
            String niceDate = datasBean.getNiceDate();//时间
            String title = datasBean.getTitle();
            Log.i("connection", "author is" + author);
            Log.i("connection", "title is" + title);
            sta = new ListViewClass.DataBean.DatasBean(title, author, chapterName, niceDate);
            statt.add(sta);


        }

    }

    private void initView() {
        Activity activity = getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView = getActivity().findViewById(R.id.recylcerView);
                final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(manager);
                adapter = new HomeAdapter(statt);
                recyclerView.setAdapter(adapter);
                adapter.addHeaderView(getView());
                adapter.addFooterView(getanView());
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                    }
                });
               // adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
             //  adapter.isFirstOnly(false);


            }
        });


    }

    private View getanView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_foot,null);
        TextView textView = view.findViewById(R.id.foot_text);
        return view;
    }

    public View getView(){
     View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shou_banner,null);
       Banner banner = view.findViewById(R.id.shou_banner);
       banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);//设置标题和数字指示器横向排列
       banner.setImageLoader(new GlideImageLoader());
       banner.setImages(images);
       banner.setBannerAnimation(Transformer.DepthPage);//动画效果
       banner.isAutoPlay(true);
       banner.setDelayTime(2000);
       banner.setIndicatorGravity(BannerConfig.CENTER);//设置居中
       banner.setBannerTitles(titles);
       banner.setFocusable(true);//获取焦点
       banner.setFocusableInTouchMode(true);//触摸是否能获取到焦点
       banner.start();

        return view;
   }



}
