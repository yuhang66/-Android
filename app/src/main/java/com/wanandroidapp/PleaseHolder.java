package com.wanandroidapp;

import android.content.Intent;
import android.nfc.TagLostException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wanandroidapp.jiexi.HolderClass;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PleaseHolder extends AppCompatActivity {
  EditText username;
   EditText password;
   Button denglu;
   String urls;
   String userText="";
   String passText="";
   HolderClass holderClass;
   TextView zhuce;
    TextView lose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_please_holder);
        initView();
    }

    private void sendHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
//Form表单格式的参数传递
                FormBody formBody = new FormBody.Builder().add("username",userText)//设置参数名称和参数值
                        .add("password",passText).build();
                final Request request = new Request.Builder().post(formBody)//Post请求的参数传递
                        .url(urls).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {}
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //此方法运行在子线程中，不能在此方法中进行UI操作。
                        String result = response.body().string();
                        Log.d("androixx.cn", result);
                       // parseOkhttp(result);
                        init(result);
                        response.body().close(); }
                });
            }
        }).start();

                                                       }

//    private void parseOkhttp(String result) {
//         holderClass = new Gson().fromJson(result,HolderClass.class);
//        Log.i("androixx",holderClass.getErrorMsg());
//        init();
//    }

    private void init(final String result) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(PleaseHolder.this,result ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        lose = findViewById(R.id.login_txtForgotPwd);
        lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PleaseHolder.this,LosePass.class);
                startActivity(intent);
            }
        });
        username = findViewById(R.id.login_edtId);
        password  =findViewById(R.id.login_edtPwd);
        zhuce = findViewById(R.id.btn_register);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PleaseHolder.this,Register.class);
                startActivity(intent);
            }
        });

        denglu = findViewById(R.id.btn_login);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userText = username.getText().toString();
                passText = password.getText().toString();
                if(userText.equals("")){
                    Toast.makeText(PleaseHolder.this,"请输入账号",Toast.LENGTH_SHORT).show();
                }else if(passText.equals("")){
                    Toast.makeText(PleaseHolder.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else{
                   urls= "https://www.wanandroid.com/user/login";
                   sendHttp();
                }
            }
        });


    }
}
