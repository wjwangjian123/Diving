package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.VpMyorderAdapter;
import com.example.edive.fragment.presonfragment.AllFragment;
import com.example.edive.fragment.presonfragment.FinishedFragment;
import com.example.edive.fragment.presonfragment.IntegralOutFragment;
import com.example.edive.fragment.presonfragment.PendingPaymentFragment;
import com.example.edive.fragment.presonfragment.TobeShippedFragment;
import com.example.edive.frame.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrder extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Fragment> fs;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.app_setting));
        }
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        initView();
    }

    private void initView() {
        fs = new ArrayList<>();
        fs.add(new IntegralOutFragment());//待付款
        fs.add(new AllFragment());//已付款
        fs.add(new PendingPaymentFragment());//进行中
        fs.add(new TobeShippedFragment());//退款
        fs.add(new FinishedFragment());//已完成
        mTab.addTab(mTab.newTab().setText("待付款"));
        mTab.addTab(mTab.newTab().setText("已付款"));
        mTab.addTab(mTab.newTab().setText("进行中"));
        mTab.addTab(mTab.newTab().setText("退款"));
        mTab.addTab(mTab.newTab().setText("已完成"));
        VpMyorderAdapter vpMyorderAdapter = new VpMyorderAdapter(getSupportFragmentManager(), fs);
        mVp.setAdapter(vpMyorderAdapter);
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                    mVp.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        if (name.equals("home")) {
            mTab.getTabAt(1).select();
        } else if (name.equals("staypay")) {
            mTab.getTabAt(2).select();
        } else if (name.equals("staysend")) {
            mTab.getTabAt(3).select();
        } else if (name.equals("stayreap")){
            mTab.getTabAt(4).select();
        }else {
            mTab.getTabAt(0).select();
        }
        mTab.setTabIndicatorFullWidth(false);
    }

    @Override
    protected void onResume() {

        super.onResume();

    }
}
