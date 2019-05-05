package com.wanandroidapp;

import android.content.Intent;
import android.graphics.DashPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wanandroidapp.Adapter.SecondAdapter;
import com.wanandroidapp.jiexi.SecondClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecondSearch extends AppCompatActivity {
    String num;
    EditText mContent;
    TextView back;
   String urls="https://www.wanandroid.com/article/query/0/json";
   List<SecondClass.DataBean.DatasBean> datasBeanList = new ArrayList<>();
   SecondClass.DataBean.DatasBean mDatas;
   RecyclerView recyclerView;
   LinearLayoutManager manager  = new LinearLayoutManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_search);
        parseNumber();
    }

    private void parseNumber() {


        Intent intent = getIntent();
        num  = intent.getStringExtra("number");
        mContent = findViewById(R.id.second_edit);
        mContent.setText(num);
         sendHttp();
    }
    private void sendHttp() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
//Form表单格式的参数传递
                FormBody formBody = new FormBody.Builder().add("k",num)//设置参数名称和参数值
                        .build();
                final Request request = new Request.Builder().post(formBody)//Post请求的参数传递
                        .url(urls).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        Log.i("androidfour","not"+e);
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //此方法运行在子线程中，不能在此方法中进行UI操作。
                        String result = response.body().string();
                        Log.d("androixx.cn", result);
                        // parseOkhttp(result);
                       parseJsonWith(result);
                        init();
                        response.body().close(); }
                });
            }
        }).start();
    }

    private void parseJsonWith(String result) {
        SecondClass secondClass = new Gson().fromJson(result,SecondClass.class);
        SecondClass.DataBean dataBean = secondClass.getData();//第一层解析
        List<SecondClass.DataBean.DatasBean> datasBean = dataBean.getDatas();
        for(SecondClass.DataBean.DatasBean mDatasBeans:datasBean){//第二层
            mDatas = new SecondClass.DataBean.DatasBean(mDatasBeans.getTitle(),mDatasBeans.getAuthor(),mDatasBeans.getSuperChapterName(),mDatasBeans.getNiceDate(),mDatasBeans.getLink());

            datasBeanList.add(mDatas);
        }


    }

    private void init() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                 recyclerView = findViewById(R.id.second_recycler);
                 recyclerView.setLayoutManager(manager);
                SecondAdapter adapter = new SecondAdapter(datasBeanList);
                recyclerView.setAdapter(adapter);
                back = findViewById(R.id.second_back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SecondSearch.this,Main_Search.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
