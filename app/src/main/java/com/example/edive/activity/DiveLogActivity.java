package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.VpDiveLogAdapter;
import com.example.edive.fragment.DiveOneFragment;
import com.example.edive.fragment.DiveTwoFragment;
import com.example.edive.frame.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 潜水日志
 */
public class DiveLogActivity extends BaseActivity {

    @BindView(R.id.dive_log_tab)
    TabLayout mDiveLogTab;
    @BindView(R.id.dive_vp)
    ViewPager mDiveVp;
    private ArrayList<Fragment> fs;

    //    private DiveLogAdapter diveLogAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.app_setting));
        }
        setContentView(R.layout.activity_dive_log);
        ButterKnife.bind(this);
        initView();
        initListener();
    }

    private void initView() {
        fs = new ArrayList<>();
        fs.add(new DiveOneFragment());
        fs.add(new DiveTwoFragment());
        mDiveLogTab.addTab(mDiveLogTab.newTab().setText("水肺潜水"));
        mDiveLogTab.addTab(mDiveLogTab.newTab().setText("自由潜水"));
        VpDiveLogAdapter vpDiveLogAdapter = new VpDiveLogAdapter(getSupportFragmentManager(),fs);
        mDiveVp.setAdapter(vpDiveLogAdapter);
        mDiveLogTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mDiveVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mDiveVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDiveLogTab));
        mDiveLogTab.setTabIndicatorFullWidth(false);
    }

    private void initListener() {

    }


}