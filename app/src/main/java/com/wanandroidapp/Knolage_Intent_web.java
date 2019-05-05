package com.wanandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wanandroidapp.jiexi.KnolageIntentClass;

import java.lang.reflect.Type;

public class Knolage_Intent_web extends AppCompatActivity {

   KnolageIntentClass.DataBean.DatasBean ddata = new KnolageIntentClass.DataBean.DatasBean("wangzhi");
  String didi="haha";
  String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knolage__intent_web);
        parseWangzhi();
        initView();
    }

    private void initView() {
        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);

    }

    private void parseWangzhi() {
        Intent intent = getIntent();
        if("action".equals(intent.getAction())){
          link = intent.getStringExtra("intent_url");
        }

    }
}
