package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.adapter.VpIntegralAdapter;
import com.example.edive.fragment.presonfragment.IntegralInFragment;
import com.example.edive.fragment.presonfragment.IntegralOutFragment;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.PersonModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class IntegralBillActivity extends BaseMvpActivity<PersonModel> {

    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Fragment> fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.app_setting));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_integral_bill;
    }

    @Override
    public void initView() {
        fs = new ArrayList<>();
        fs.add(new IntegralInFragment());
        fs.add(new IntegralOutFragment());
        mTab.addTab(mTab.newTab().setText("积分收入"));
        mTab.addTab(mTab.newTab().setText("积分支出"));
        VpIntegralAdapter vpIntegralAdapter = new VpIntegralAdapter(getSupportFragmentManager(), fs);
        mVp.setAdapter(vpIntegralAdapter);
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
        mTab.setTabIndicatorFullWidth(false);
    }

    @Override
    public void initData() {

    }

    @Override
    public PersonModel getModel() {
        return new PersonModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick(R.id.iv_more)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_more:
                break;
        }
    }
}
