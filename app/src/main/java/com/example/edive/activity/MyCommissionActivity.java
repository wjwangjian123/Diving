package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.design.CommonTitle;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.PersonModel;

import butterknife.BindView;

public class MyCommissionActivity extends BaseMvpActivity<PersonModel> {

    @BindView(R.id.titles)
    CommonTitle mTitles;
    @BindView(R.id.tv_commission)
    TextView mTvCommission;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.rec)
    RecyclerView mRec;

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
        return R.layout.activity_my_commission;
    }

    @Override
    public void initView() {
        mTitles.mMoreText1.setTextColor(getResources().getColor(R.color.commission));
        mTitles.mMoreText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCommissionActivity.this,MySubordinatesActivity.class));
            }
        });
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
}
