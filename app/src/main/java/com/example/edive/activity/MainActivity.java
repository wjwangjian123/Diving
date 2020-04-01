package com.example.edive.activity;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edive.R;
import com.example.edive.bean.LoginBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseApplication;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.frame.MyServer;
import com.example.edive.model.LoginModel;
import com.example.edive.utils.AppValidationMgr;
import com.example.edive.utils.NormalConfig;
import com.example.edive.utils.SharedPrefrenceUtils;
import com.example.edive.utils.ToastUtil;
import com.example.edive.wxapi.WXEntryActivity;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yanzhenjie.album.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseMvpActivity<LoginModel> {


    @BindView(R.id.et_uname)
    EditText mEtUname;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.lltwo)
    LinearLayout mLltwo;
    @BindView(R.id.tv_VerificationCodeLogin)
    TextView mTvVerificationCodeLogin;
    @BindView(R.id.llthree)
    LinearLayout mLlthree;
    @BindView(R.id.tv_QuickLogin)
    TextView mTvQuickLogin;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.iv_wechatLogin)
    ImageView mIvWechatLogin;
    @BindView(R.id.tv_Registered)
    TextView mTvRegistered;
    @BindView(R.id.tv_ForgetPassword)
    TextView mTvForgetPassword;
    @BindView(R.id.tv_uanmeloginString)
    TextView mTvUanmeloginString;
    @BindView(R.id.iv_unameandpasswordLogin)
    ImageView mIvUnameandpasswordLogin;

    private String uname;
    private String password;
    private String type = "password";
    private String loginType = "member";
    private static final String APP_ID = "wxecf55d2536e35e80";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initget();
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        ApiConfig.wx_api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        ApiConfig.wx_api.registerApp(APP_ID);
        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                ApiConfig.wx_api.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        makeStatusBarTransparent(MainActivity.this);
        setTextViewStyles(mTvUanmeloginString);
        EventBus.getDefault().register(this);
    }

    private void initget() {
        XXPermissions.with(this).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (denied.size() != 0) Toast.makeText(getApplicationContext(), "拒绝权限影响您正常使用", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }
    @Override
    public void initData() {

    }

    @Override
    public LoginModel getModel() {
        return new LoginModel();
    }

    @OnClick({R.id.tv_VerificationCodeLogin, R.id.tv_QuickLogin, R.id.iv_wechatLogin, R.id.tv_Registered, R.id.tv_ForgetPassword, R.id.tv_uanmeloginString, R.id.iv_unameandpasswordLogin})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            //验证码登陆
            case R.id.tv_VerificationCodeLogin:
                startActivity(new Intent(MainActivity.this, VerificationLoginActivity.class));
                finish();
                break;
            //微信登陆
            case R.id.iv_wechatLogin:
                // 微信登陆方法
                if (!ApiConfig.wx_api.isWXAppInstalled()) {
                    Toast.makeText(MainActivity.this,"请先安装微信客户端！",Toast.LENGTH_LONG).show();
                    return;
                }
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                ApiConfig.wx_api.sendReq(req);
                break;
            //注册
            case R.id.tv_Registered:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            //找回密码
            case R.id.tv_ForgetPassword:
                startActivity(new Intent(MainActivity.this, FindPasswordActivity.class));
                break;
            case R.id.tv_QuickLogin:
                break;
            case R.id.tv_uanmeloginString:
                break;
            //登陆
            case R.id.iv_unameandpasswordLogin:
                initLogin();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initLogin() {
        uname = mEtUname.getText().toString();
        password = mEtPassword.getText().toString();
        if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(password)) {
            if (AppValidationMgr.isPhone(uname) || AppValidationMgr.isEmail(uname)) {
//                mPresenter.getData(ApiConfig.PASSWORD_LOGIN,type,uname,password);
                Retrofit build = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(MyServer.url)
                        .build();
                MyServer myServer = build.create(MyServer.class);
                Observable<LoginBean> login = myServer.getPasswordLogin(type, uname, password,loginType);
                login.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LoginBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LoginBean bean) {
                                if (bean.getCode() == 200) {
                                    int id = bean.getData().getAdditionalInformation().getId();
                                    ToastUtil.showShort(bean.getMessage()+bean.getCode()+"2");
                                    mApplication.Phone = bean.getData().getAdditionalInformation().getUsername();
                                    mApplication.Token = bean.getData().getValue();
                                    mApplication.userid = String.valueOf(id);
                                    mApplication.nickname = bean.getData().getAdditionalInformation().getNickName();
                                    mApplication.icon = bean.getData().getAdditionalInformation().getIcon();
                                    mApplication.Personalizedsignature = bean.getData().getAdditionalInformation().getPersonalizedSignature();
                                    SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.PERSONALIZED,bean.getData().getAdditionalInformation().getPersonalizedSignature());
                                    SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.TOKEN,bean.getData().getValue());
                                    SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.PHONE,bean.getData().getAdditionalInformation().getUsername());
                                    SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.USER_PHOTO,String.valueOf(id));
                                    SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.NICKNAME,bean.getData().getAdditionalInformation().getNickName());
                                    SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.ICON,bean.getData().getAdditionalInformation().getIcon());
                                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                    finish();
                                }else {
                                    ToastUtil.showShort(bean.getMessage()+bean.getCode());
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                showLog(e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }else{
                ToastUtil.showLong("请输入正确的手机号或邮箱号");
            }
        }else {
            ToastUtil.showLong("请输入账号或密码");
        }
    }


    //沉浸式状态栏
    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void setTextViewStyles(TextView textView) {
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textView.getPaint().getTextSize() * textView.getText().length(), 0, Color.parseColor("#FF076BF0"), Color.parseColor("#FF35A6FF"), Shader.TileMode.CLAMP);
        textView.getPaint().setShader(mLinearGradient);
        textView.invalidate();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showLog(e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.PASSWORD_LOGIN:
                LoginBean bean = (LoginBean) t[0];
                if (bean.getCode() == 200) {

                    showLongToast(bean.getMessage());
                    mApplication.Token = bean.getData().getValue();
                    showLog(bean.getData().getValue());
                    SharedPrefrenceUtils.saveString(this, NormalConfig.TOKEN,bean.getData().getValue());
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
                }else {
                    showToast(bean.getMessage());
                }
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getcode(String code){
        initWechatLogin(code);
    }

    private void initWechatLogin(String code) {
        showLoadingDialog();
        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MyServer.url)
                .build();
        MyServer myServer = build.create(MyServer.class);
        Observable<LoginBean> wechatLogin = myServer.getWechatLogin(code, loginType);
        wechatLogin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode() == 200) {
                            hideLoadingDialog();
                            ToastUtil.showLong(loginBean.getMessage());
                            mApplication.Token = loginBean.getData().getValue();
                            mApplication.userid = String.valueOf(loginBean.getData().getAdditionalInformation().getId());
                            mApplication.nickname = loginBean.getData().getAdditionalInformation().getNickName();
                            mApplication.icon = loginBean.getData().getAdditionalInformation().getIcon();

//                            SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.PERSONALIZED,bean.getData().getAdditionalInformation().getPersonalizedSignature());
                            SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.TOKEN,loginBean.getData().getValue());
                            SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.PHONE,loginBean.getData().getAdditionalInformation().getUsername());
                            SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.USER_PHOTO,String.valueOf(loginBean.getData().getAdditionalInformation().getId()));
                            SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.NICKNAME,loginBean.getData().getAdditionalInformation().getNickName());
                            SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.ICON,loginBean.getData().getAdditionalInformation().getIcon());
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            finish();
                        }else {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showShort(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ApiConfig.wx_api.unregisterApp();
        EventBus.getDefault().unregister(this);
    }
}
