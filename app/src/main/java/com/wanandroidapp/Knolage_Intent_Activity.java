package com.wanandroidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.wanandroidapp.Adapter.KnolageIntentAdapter;
import com.wanandroidapp.jiexi.KnolageIntentClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Knolage_Intent_Activity extends AppCompatActivity {
  private int id;
  private String mUrls="https://www.wanandroid.com/article/list/0/json?cid=";
  List<KnolageIntentClass.DataBean.DatasBean> dataNumber = new ArrayList<>();
  private LinearLayoutManager manager = new LinearLayoutManager(this);
  KnolageIntentClass.DataBean.DatasBean mData;
    Intent intentWangzhi;
    WebView webView;
 RecyclerView recyclerView;
 int KeYCode =KeyEvent.KEYCODE_BACK;
 KeyEvent event;

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
//          webView.goBack();
//          webView.setVisibility(View.INVISIBLE);
//          recyclerView.setVisibility(View.VISIBLE);
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseNumber();
        setContentView(R.layout.activity_knolage__intent_);
        parseHttp();
    }

    private void parseHttp() {
        Log.i("qisiwol","made"+id);

        new Thread(new Runnable() {
            @Override
            public void run() {

             Http.sendOkHttpRequest(mUrls + id, new Callback() {
                 @Override
                 public void onFailure(Call call, IOException e) {
                     Log.i("intent","not connection"+e);
                 }

                 @Override
                 public void onResponse(Call call, Response response) throws IOException {
                       String responseData  = response.body().string();
                       parseJSONWithHttp(responseData);
                 }
             });
            }
        }).start();
    }

    private void parseJSONWithHttp(String responseData) {
        KnolageIntentClass knolageIntentClass = new Gson().fromJson(responseData,KnolageIntentClass.class);
        KnolageIntentClass.DataBean dataBean = knolageIntentClass.getData();
        List<KnolageIntentClass.DataBean.DatasBean> datasBeans = dataBean.getDatas();
        for (KnolageIntentClass.DataBean.DatasBean datasBeanArra :datasBeans){
            mData = new KnolageIntentClass.DataBean.DatasBean(datasBeanArra.getLink());
            dataNumber.add(datasBeanArra);

        }
        initView();
    }

    private void initView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 recyclerView= findViewById(R.id.knolage_intent_recycler);
                KnolageIntentAdapter adapter = new KnolageIntentAdapter(dataNumber);
               recyclerView.setLayoutManager(manager);
               recyclerView.setAdapter(adapter);
               adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                   @Override
                   public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                     intentWangzhi = new Intent(Knolage_Intent_Activity.this,Knolage_Intent_web.class);
//                     intentWangzhi.setAction("intenturl");
//                     intentWangzhi.putExtra("wangzhi",mData.getLink());
//                     startActivity(intentWangzhi);
                       recyclerView.setVisibility(View.GONE);
                       webView = findViewById(R.id.intent_webview);
                       webView.setVisibility(View.VISIBLE);
                       webView.getSettings().setJavaScriptEnabled(true);
                       webView.setWebViewClient(new WebViewClient());
                       webView.loadUrl(mData.getLink());

//                        if(webView.canGoBack()){
//                            webView.goBack();
//                            webView.setVisibility(View.INVISIBLE);
//                            recyclerView.setVisibility(View.VISIBLE);
//                        }



//                       webView.getSettings().setJavaScriptEnabled(true);
//                       webView.setWebViewClient(new WebViewClient());
//                       webView.loadUrl(mData.getLink());
                      Toast.makeText(Knolage_Intent_Activity.this,"wangzhi"+mData.getLink(),Toast.LENGTH_SHORT).show();


                   }
               });
            }
        });
    }



    private void parseNumber() {
        Intent intent = getIntent();
        if ("action".equals(intent.getAction())) {
             id = intent.getIntExtra("intent_image", 0);
Log.i("stttt","网址"+id);
        }
    }

}