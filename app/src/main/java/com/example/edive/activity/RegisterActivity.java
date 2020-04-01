package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.bean.RegisterBean;
import com.example.edive.bean.VerificationBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.frame.MyServer;
import com.example.edive.model.LoginModel;
import com.example.edive.utils.AppValidationMgr;
import com.example.edive.utils.CountDownTimerUtils;
import com.example.edive.utils.ToastUtil;

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

public class RegisterActivity extends BaseMvpActivity<LoginModel> {

    @BindView(R.id.register_back)
    ImageView mRegisterBack;
    @BindView(R.id.register_code_iv)
    ImageView mRegisterCodeIv;
    @BindView(R.id.findpass_ed_code)
    EditText mFindpassEdCode;
    @BindView(R.id.btn_getcode)
    TextView mBtnGetcode;
    @BindView(R.id.register_et_pass)
    EditText mRegisterEtPass;
    @BindView(R.id.ll_register)
    LinearLayout mLlRegister;
    @BindView(R.id.register_et_uname)
    EditText mRegisterEtUname;
    @BindView(R.id.findpass_ok_btn)
    Button mFindpassOkBtn;
    @BindView(R.id.et_phone_emial)
    EditText mEtPhoneEmial;
    private CountDownTimerUtils downTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.findpassword_bg));//设置颜色
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        //倒计时工具类
        downTimerUtils = new CountDownTimerUtils(mBtnGetcode, 60000, 1000);
    }

    @Override
    public void initData() {

    }

    @Override
    public LoginModel getModel() {
        return new LoginModel();
    }

    @OnClick({R.id.btn_getcode, R.id.findpass_ok_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.btn_getcode:
                initgetCode();
                break;
            case R.id.findpass_ok_btn:
                initRegister();
                break;
        }
    }

    private void initgetCode() {
        String uname = mEtPhoneEmial.getText().toString();
        if (!TextUtils.isEmpty(uname)) {
            if (AppValidationMgr.isPhone(uname) || AppValidationMgr.isEmail(uname)) {
                //获取验证码
                downTimerUtils.start();
//                mPresenter.getData(ApiConfig.VER_GETCODE,uname);
                Retrofit build = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(MyServer.url)
                        .build();
                MyServer myServer = build.create(MyServer.class);
                Observable<VerificationBean> verGetcode = myServer.getVerGetcode(uname);
                verGetcode.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<VerificationBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(VerificationBean bean) {
                                String data = bean.getData();
                                mFindpassEdCode.setText(data);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }else {
                ToastUtil.showLong("请填写正确的手机号或邮箱");
            }
        }else {
            ToastUtil.showLong("手机号或邮箱不能为空");
        }
    }

    private void initRegister() {
        String password = mRegisterEtPass.getText().toString();
        String invitationcode = mRegisterEtUname.getText().toString();
        String uname = mEtPhoneEmial.getText().toString();
        String VerificationCode = mFindpassEdCode.getText().toString();
        if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(VerificationCode)) {
            if (AppValidationMgr.isPhone(uname) || AppValidationMgr.isEmail(uname)) {
                if (!TextUtils.isEmpty(password)) {
                    Retrofit build = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(MyServer.url)
                            .build();
                    MyServer myServer = build.create(MyServer.class);
                    Observable<RegisterBean> registerBean = myServer.getRegisterBean(uname, password, VerificationCode, invitationcode);
                    registerBean.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<RegisterBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(RegisterBean registerBean) {
                                    if (registerBean.getCode() == 200) {
                                        showLongToast(registerBean.getMessage());
                                        finish();
                                    }else {
                                        showToast(registerBean.getMessage());
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    showToast(e.getMessage());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
//                    mPresenter.getData(ApiConfig.REGISTER_DATA,uname,password,VerificationCode,invitationcode);
                }else {
                    showToast("请输入密码");
                }
            }else {
                showToast("请输入正确的手机号或邮箱");
            }
        }else {
            ToastUtil.showLong("请获取验证码");
        }
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
            switch (whichApi){
                case ApiConfig.VER_GETCODE:
                    VerificationBean bean = (VerificationBean) t[0];
                    showLongToast(bean.getData());
                    break;
                case ApiConfig.REGISTER_DATA:
                    RegisterBean beans = (RegisterBean) t[0];
                    showLongToast(beans.getMessage());
                    if (beans.getCode() == 200) {
                        finish();
                    }
                    break;
            }
    }
}
