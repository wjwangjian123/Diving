package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.edive.R;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;

import butterknife.BindView;
import butterknife.OnClick;

public class RestorationActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.iv_hyvideo)
    ImageView mIvHyvideo;
    @BindView(R.id.iv_hyphoto)
    ImageView mIvHyphoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.app_setting));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_restoration;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

    @OnClick({R.id.iv_hyvideo, R.id.iv_hyphoto})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_hyvideo:

                break;
            case R.id.iv_hyphoto:

                break;
        }
    }
}
