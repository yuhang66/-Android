package com.wanandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Register extends AppCompatActivity {
    EditText user_register;
    EditText pass_zhuce;
    EditText passregister;
    Button register;
    String mUrls = "https://www.wanandroid.com/user/register";
    String yonghu;
    String mima;
    String again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        user_register = findViewById(R.id.login_edtId_zhuce);//用户名
        pass_zhuce = findViewById(R.id.login_edtPwd_zhuce);//密码
        passregister = findViewById(R.id.login_pwdagain);//确认密码
        register = findViewById(R.id.btn_login_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            yonghu = user_register.getText().toString();
            mima = pass_zhuce.getText().toString();
            again = passregister.getText().toString();
            Log.i("androidre",yonghu+mima+again);
            if(mima.equals("")){
                Toast.makeText(Register.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            }else if(yonghu.equals("")){
                Toast.makeText(Register.this,"账号不能为空",Toast.LENGTH_SHORT).show();
            }else if(again.equals("")){
                Toast.makeText(Register.this,"请重新输入确认密码",Toast.LENGTH_SHORT).show();
            }else {
                sendPostHttp();
            }
            }
        });


    }

    private void sendPostHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
//Form表单格式的参数传递
                FormBody formBody = new FormBody.Builder().add("username",yonghu)//设置参数名称和参数值
                        .add("password",mima)
                        .add("repassword",again)
                        .build();
                final Request request = new Request.Builder().post(formBody)//Post请求的参数传递
                        .url(mUrls).build();
                okHttpClient.newCall(request).enqueue(new Callback() {//异步请求
                    @Override
                    public void onFailure(Call call, IOException e) {}
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //此方法运行在子线程中，不能在此方法中进行UI操作。
                        String result = response.body().string();
                        Log.d("zhucea", result);
                        init(result);
                        response.body().close(); }
                });
            }
        }).start();

    }

    private void init(final String result) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Register.this,result,Toast.LENGTH_SHORT).show();
            }
        });
    }
}

