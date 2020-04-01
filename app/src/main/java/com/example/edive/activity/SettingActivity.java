package com.example.edive.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.PersonModel;
import com.example.edive.utils.DataCleanManager;
import com.example.edive.utils.NormalConfig;
import com.example.edive.utils.SharedPrefrenceUtils;
import com.example.edive.utils.ToastUtil;
import com.tencent.android.tpush.XGPushManager;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseMvpActivity<PersonModel> {
    @BindView(R.id.rl_setting_password)
    RelativeLayout mRlSettingPassword;
    @BindView(R.id.rl_setting_pay_password)
    RelativeLayout mRlSettingPayPassword;
    @BindView(R.id.rl_Feedback)
    RelativeLayout mRlFeedback;
    @BindView(R.id.rl_aboutwe)
    RelativeLayout mRlAboutwe;
    @BindView(R.id.tv_huancun)
    TextView mTvHuancun;
    @BindView(R.id.rl_Close)
    RelativeLayout mRlClose;
    @BindView(R.id.tv_update)
    TextView mTvUpdate;
    @BindView(R.id.rl_update)
    RelativeLayout mRlUpdate;
    @BindView(R.id.bt_ded)
    Button mBtDed;
    private PopupWindow popupWindow;

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
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(SettingActivity.this);
            mTvHuancun.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    @OnClick({R.id.rl_setting_password, R.id.rl_setting_pay_password, R.id.rl_Feedback, R.id.rl_aboutwe, R.id.rl_Close, R.id.rl_update, R.id.bt_ded})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_setting_password:
                //修改密码
                startActivity(new Intent(SettingActivity.this,UpdatePasswordActivity.class));
                break;
            case R.id.rl_setting_pay_password:
                //修改支付密码
                startActivity(new Intent(SettingActivity.this,UpdatePayPasswordActivity.class));
                break;
            case R.id.rl_Feedback:
                //意见反馈
                startActivity(new Intent(SettingActivity.this,SuggestionsActivity.class));
                break;
            case R.id.rl_aboutwe:
                //关于我们
                startActivity(new Intent(SettingActivity.this,FeedbackActivity.class));
                break;
            case R.id.rl_Close:
                //清除 缓存
                try {
                    String totalCacheSizes = DataCleanManager.getTotalCacheSize(SettingActivity.this);
                    if (totalCacheSizes.equals("0K")) {
                        ToastUtil.showLong("不需要清除缓存");
                    }else {
                        DataCleanManager.clearAllCache(SettingActivity.this);
                        ToastUtil.showLong("清除缓存成功");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    String totalCacheSize = DataCleanManager.getTotalCacheSize(SettingActivity.this);
                                    mTvHuancun.setText(totalCacheSize);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }




                break;
            case R.id.rl_update:
                //检查更新
                break;
            case R.id.bt_ded:
                 View inflate1 = LayoutInflater.from(SettingActivity.this).inflate(R.layout.layout_popo_setting, null);
                                 TextView tv_diss = inflate1.findViewById(R.id.tv_diss);
                                 TextView tv_ok = inflate1.findViewById(R.id.tv_ok);
                popupWindow = new PopupWindow(inflate1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                 popupWindow.setBackgroundDrawable(new ColorDrawable());
                                 popupWindow.setOutsideTouchable(true);
                                 popupWindow.setAnimationStyle(R.style.PopupAnimation);
                                 popupWindow.showAtLocation(inflate1, Gravity.CENTER,0,0);
                //         设置背景颜色变暗
                WindowManager.LayoutParams lp = this.getWindow().getAttributes();
                lp.alpha = 0.4f;
                this.getWindow().setAttributes(lp);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = SettingActivity.this.getWindow().getAttributes();
                        lp.alpha = 1f;
                        SettingActivity.this.getWindow().setAttributes(lp);
                    }
                });
                tv_diss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //注销
                        popupWindow.dismiss();
                        XGPushManager.unregisterPush(SettingActivity.this);
                        XGPushManager.delAccount(getApplicationContext(),mApplication.Phone);
                        SharedPrefrenceUtils.saveString(SettingActivity.this, NormalConfig.PHONE,"");
                        SharedPrefrenceUtils.saveString(SettingActivity.this, NormalConfig.USER_PHOTO,"");
                        SharedPrefrenceUtils.saveString(SettingActivity.this, NormalConfig.ICON,"");
                        SharedPrefrenceUtils.saveString(SettingActivity.this, NormalConfig.NICKNAME,"");
                        SharedPrefrenceUtils.saveString(SettingActivity.this, NormalConfig.TOKEN,"");
                        SharedPrefrenceUtils.saveString(SettingActivity.this, NormalConfig.PERSONALIZED,"");
                        mApplication.icon = "";
                        mApplication.nickname = "";
                        mApplication.Personalizedsignature = "";
                        mApplication.Phone = "";
                        mApplication.Token = "";
                        mApplication.userid = "";

                        startActivity(new Intent(SettingActivity.this,MainActivity.class));
                        finish();
                    }
                });

                break;
        }
    }
}
