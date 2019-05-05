package com.wanandroidapp;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private TextView mFirst;
   private TextView mSecond;
   private TextView mThird;
   private TextView mFourth;
   private FrameLayout mFrameLayout;
    private DrawerLayout drawerLayout;
   private CircleImageView head_img;
   private TextView head_title;
   private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
       head_img = findViewById(R.id.head_image);
       head_img.setOnClickListener(this);
       head_title = findViewById(R.id.head_title);
       imageView = findViewById(R.id.main_search);
       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,Main_Search.class);
               startActivity(intent);
           }
       });
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.qiqi);

        }


        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_us:
                        Toast.makeText(MainActivity.this,"clicked",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,PleaseHolder.class);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        initView();
    mFirst.setSelected(true);
    replaceFragment(new ShouNumber());

    }
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//再按一次退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void initView() {
        mFirst = findViewById(R.id.wan_main_f);
        mSecond = findViewById(R.id.wan_main_s);
        mThird = findViewById(R.id.wan_main_t);
        mFourth = findViewById(R.id.wan_main_e);
        mFrameLayout = findViewById(R.id.wan_main_fra);
        mFirst.setOnClickListener(this);
        mSecond.setOnClickListener(this);
        mThird.setOnClickListener(this);
        mFourth.setOnClickListener(this);

    }

    private void selected() {
        mFirst.setSelected(false);
        mSecond.setSelected(false);
        mThird.setSelected(false);
        mFourth.setSelected(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wan_main_f:
                selected();
                mFirst.setSelected(true);
                replaceFragment(new ShouNumber());
                head_title.setText("首页");
                break;
            case R.id.wan_main_s:
                selected();
                mSecond.setSelected(true);
                replaceFragment(new KnolageNumber());
                head_title.setText("知识系统");
                break;
            case R.id.wan_main_t:
                selected();
                mThird.setSelected(true);
                replaceFragment(new GpsNumber());
                head_title.setText("导航");
                break;
            case R.id.wan_main_e:
                selected();
                mFourth.setSelected(true);
                replaceFragment(new ProjectNumber());
                head_title.setText("项目");
                break;
            case R.id.head_image:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
                default:
                    break;
        }

    }
    private void replaceFragment(android.support.v4.app.Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.wan_main_fra,fragment);
        transaction.commit();
    }
}
