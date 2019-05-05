package com.wanandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wanandroidapp.Adapter.HistorySearch;
import com.wanandroidapp.Adapter.HotSearchAdapter;
import com.wanandroidapp.jiexi.HistoryClass;
import com.wanandroidapp.jiexi.HotSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main_Search extends AppCompatActivity {
    List<HistoryClass> mClass=new ArrayList<>();
    HistoryClass historyClass;
    EditText sear;
    String sear_text;
    Button soso;
    String urls = "https://www.wanandroid.com//hotkey/json";
    RecyclerView recyclerView;
    HotSearch.DataBean mData;
    List<HotSearch.DataBean> datasbea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__search2);
        initView();




    }

    private void parseJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http.sendOkHttpRequest(urls, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                            String responseData  = response.body().string();
                            parseNumber(responseData);

                    }
                });
            }
        }).start();

    }

    private void init() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView = findViewById(R.id.hot_recycler);
                recyclerView.setLayoutManager(new GridLayoutManager(Main_Search.this,4));
                HotSearchAdapter adapter = new HotSearchAdapter(datasbea);
                recyclerView.setAdapter(adapter);
                adapter.addFooterView(getFoot());
            }
        });
    }

    private View getFoot() {
        View view = LayoutInflater.from(this).inflate(R.layout.history_search,null);
        TextView textView = view.findViewById(R.id.history_history);
       textView.setText("历史搜索");
        RecyclerView recyclerView = view.findViewById(R.id.history_recycler);
        GridLayoutManager managers = new GridLayoutManager(Main_Search.this,4);
        recyclerView.setLayoutManager(managers);

        HistorySearch search = new HistorySearch(mClass);
Log.i("kukuku","66"+mClass);
        recyclerView.setAdapter(search);
        return view;
    }

    private void parseNumber(String responseData) {
        HotSearch hotSearch = new Gson().fromJson(responseData,HotSearch.class);
        List<HotSearch.DataBean> dataBean = hotSearch.getData();
         datasbea = new ArrayList<>();
        for (HotSearch.DataBean mDataBeans :dataBean){
            mData = new HotSearch.DataBean(mDataBeans.getName());

            datasbea.add(mData);
        }
        init();
    }

    private void initView() {

        soso =findViewById(R.id.main_intent_sou);
        soso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sear = findViewById(R.id.main_intent_edit);
                sear_text = sear.getText().toString();
                Log.i("shuju","wow"+sear_text);
                historyClass  = new HistoryClass(sear_text);

               mClass.add(historyClass);

              Intent intent = new Intent(Main_Search.this,SecondSearch.class);
              intent.putExtra("number",sear_text);
              startActivity(intent);
                parseJson();
            }

        });

        parseJson();
    }



}
