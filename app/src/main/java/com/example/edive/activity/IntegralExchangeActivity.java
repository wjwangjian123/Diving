package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.VpIntegralTanAdapter;
import com.example.edive.fragment.FisherFragment;
import com.example.edive.fragment.presonfragment.CompletedFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntegralExchangeActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Fragment> fs;
    private VpIntegralTanAdapter vpIntegralTanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.app_setting));
        }
        setContentView(R.layout.activity_integral_exchange);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fs = new ArrayList<>();
        fs.add(new FisherFragment());
        fs.add(new CompletedFragment());
        mTab.addTab(mTab.newTab().setText("进行中"));
        mTab.addTab(mTab.newTab().setText("已完成"));
        vpIntegralTanAdapter = new VpIntegralTanAdapter(getSupportFragmentManager(), fs);
        mVp.setAdapter(vpIntegralTanAdapter);
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
    }
}
