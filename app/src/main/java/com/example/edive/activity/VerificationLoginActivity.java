package com.example.edive.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.bean.VerificationBean;
import com.example.edive.bean.VerificationLgoinBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.frame.MyServer;
import com.example.edive.model.LoginModel;
import com.example.edive.utils.AppValidationMgr;
import com.example.edive.utils.CountDownTimerUtils;
import com.example.edive.utils.NormalConfig;
import com.example.edive.utils.SharedPrefrenceUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerificationLoginActivity extends BaseMvpActivity<LoginModel> {

    @BindView(R.id.tv_uanmeloginString)
    TextView mTvUanmeloginString;
    @BindView(R.id.et_uname)
    EditText mEtUname;
    @BindView(R.id.et_VerificationCode)
    EditText mEtVerificationCode;
    @BindView(R.id.tv_Registered)
    TextView mTvRegistered;
    @BindView(R.id.tv_ForgetPassword)
    TextView mTvForgetPassword;
    @BindView(R.id.iv_wechatLogin)
    ImageView mIvWechatLogin;
    @BindView(R.id.tv_getVerificationCode)
    TextView mTvGetVerificationCode;
    @BindView(R.id.iv_Login)
    ImageView mIvLogin;
    @BindView(R.id.tv_VerificationCodeLogin)
    TextView mTvVerificationCodeLogin;
    private CountDownTimerUtils downTimerUtils;
    // AAppID：wx24009bcc9adc6318
    private static final String APP_ID = "wx24009bcc9adc6318";
    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;
    private String username;
    private String loginType = "member";

    @Override
    public int getLayoutId() {
        return R.layout.activity_verification_login;
    }

    public void initView() {
        setTextViewStyles(mTvVerificationCodeLogin);
        makeStatusBarTransparent(VerificationLoginActivity.this);
        setTextViewStyles(mTvGetVerificationCode);
        //倒计时工具类
        downTimerUtils = new CountDownTimerUtils(mTvGetVerificationCode, 60000, 1000);
    }

    @Override
    public void initData() {

    }

    @Override
    public LoginModel getModel() {
        return new LoginModel();
    }


    private void setTextViewStyles(TextView textView) {
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textView.getPaint().getTextSize() * textView.getText().length(), 0, Color.parseColor("#FF076BF0"), Color.parseColor("#FF35A6FF"), Shader.TileMode.CLAMP);
        textView.getPaint().setShader(mLinearGradient);
        textView.invalidate();
    }

    @OnClick({R.id.tv_uanmeloginString, R.id.et_uname, R.id.et_VerificationCode, R.id.tv_Registered, R.id.tv_ForgetPassword, R.id.iv_wechatLogin, R.id.tv_getVerificationCode, R.id.iv_Login})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            //账号密码登陆
            case R.id.tv_uanmeloginString:
                startActivity(new Intent(VerificationLoginActivity.this, MainActivity.class));
                finish();
                break;
            //注册账号
            case R.id.tv_Registered:
                startActivity(new Intent(VerificationLoginActivity.this, RegisterActivity.class));
                break;
            //忘记密码
            case R.id.tv_ForgetPassword:
                startActivity(new Intent(VerificationLoginActivity.this, FindPasswordActivity.class));
                break;
            //微信登陆
            case R.id.iv_wechatLogin:
                // 微信登陆方法
                initWechat();
                // 微信登陆授权方法
                initSou();
                break;
            //获取验证码
            case R.id.tv_getVerificationCode:
                initGetCode();
                break;
            //登陆
            case R.id.iv_Login:
                initLogin();
                break;
        }
    }

    private void initLogin() {
        String uname = mEtUname.getText().toString();
        String code = mEtVerificationCode.getText().toString();
        if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(code)) {
            if (AppValidationMgr.isPhone(uname) || AppValidationMgr.isEmail(uname)) {
                //登陆
//                mPresenter.getData(ApiConfig.VER_LOGIN,code,uname);
                Retrofit build = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(MyServer.url)
                        .build();
                MyServer myServer = build.create(MyServer.class);
                Observable<VerificationLgoinBean> verLogin = myServer.getVerLogin(code, uname,loginType);
                verLogin.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<VerificationLgoinBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(VerificationLgoinBean been) {
                                if (been.getCode() == 200) {
                                    int id = been.getData().getAdditionalInformation().getId();
                                    mApplication.Phone = been.getData().getAdditionalInformation().getUsername();
                                    mApplication.Token = been.getData().getValue();
                                    mApplication.userid = String.valueOf(id);
                                    mApplication.nickname = been.getData().getAdditionalInformation().getNickName();
                                    mApplication.icon = been.getData().getAdditionalInformation().getIcon();
                                    mApplication.Personalizedsignature = been.getData().getAdditionalInformation().getPersonalizedSignature();
                                    SharedPrefrenceUtils.saveString(VerificationLoginActivity.this, NormalConfig.PERSONALIZED,been.getData().getAdditionalInformation().getPersonalizedSignature());
                                    SharedPrefrenceUtils.saveString(VerificationLoginActivity.this, NormalConfig.TOKEN,been.getData().getValue());
                                    SharedPrefrenceUtils.saveString(VerificationLoginActivity.this, NormalConfig.PHONE,been.getData().getAdditionalInformation().getUsername());
                                    SharedPrefrenceUtils.saveString(VerificationLoginActivity.this, NormalConfig.USER_PHOTO,String.valueOf(id));
                                    SharedPrefrenceUtils.saveString(VerificationLoginActivity.this, NormalConfig.NICKNAME,been.getData().getAdditionalInformation().getNickName());
                                    SharedPrefrenceUtils.saveString(VerificationLoginActivity.this, NormalConfig.ICON,been.getData().getAdditionalInformation().getIcon());
                                    startActivity(new Intent(VerificationLoginActivity.this,HomeActivity.class));
                                    showLongToast(been.getMessage());
                                    finish();
                                }else {
                                    showLog(been.getCode()+":"+been.getMessage());
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
            }else {
                showToast("请填写正确的手机号或邮箱");
            }
        }else {
            showToast("请填写手机号或邮箱获取验证码");
        }
    }

    private void initGetCode() {
        username = mEtUname.getText().toString();
        String code = mEtVerificationCode.getText().toString();
        if (!TextUtils.isEmpty(username)) {
            if (AppValidationMgr.isPhone(username) || AppValidationMgr.isEmail(username)) {
                downTimerUtils.start();
//                mPresenter.getData(ApiConfig.VER_GETCODE,urls);
                Retrofit build = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(MyServer.url)
                        .build();
                MyServer myServer = build.create(MyServer.class);
                Observable<VerificationBean> verGetcode = myServer.getVerGetcode(username);
                verGetcode.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<VerificationBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(VerificationBean bean) {
                                String data = bean.getData();
                                mEtVerificationCode.setText(data);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }else {
                showToast("请填写正确的手机号或邮箱");
            }
        }else {
            showToast("请填写手机号或邮箱");
        }
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showLog(e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.VER_GETCODE:
                VerificationBean bean = (VerificationBean) t[0];
                String data = bean.getData();
                mEtVerificationCode.setText(data);
                break;
            case ApiConfig.VER_LOGIN:
                VerificationLgoinBean been = (VerificationLgoinBean) t[0];
                if (been.getCode() == 200) {
                    mApplication.Token = been.getData().getValue();
                    SharedPrefrenceUtils.saveString(this, NormalConfig.TOKEN,been.getData().getValue());
                    startActivity(new Intent(VerificationLoginActivity.this,HomeActivity.class));
                    showLongToast(been.getMessage());
                    finish();
                }else {
                    showToast(been.getMessage());
                }
                break;
        }
    }

    private void initSou() {

        if (!api.isWXAppInstalled()) {
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        }

    }

    // 微信登陆
    private void initWechat() {

        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);

        // 将应用的appId注册到微信
        api.registerApp(APP_ID);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
//初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
//        textObj.text = "";

//用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
//        msg.description = text;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());  //transaction字段用与唯一标示一个请求
        req.message = msg;
//        req.scene = mTargetScene;

//调用api接口，发送数据到微信
        api.sendReq(req);
    }

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
}
