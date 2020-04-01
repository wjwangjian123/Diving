package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.bean.MyPersonBean;
import com.example.edive.bean.UpdatePhoneBean;
import com.example.edive.bean.VerificationBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.frame.MyServer;
import com.example.edive.model.PersonModel;
import com.example.edive.utils.AppValidationMgr;
import com.example.edive.utils.CountDownTimerUtils;
import com.example.edive.utils.NormalConfig;
import com.example.edive.utils.SharedPrefrenceUtils;
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

public class UpdatePhoneActivity extends BaseMvpActivity<PersonModel> {

    @BindView(R.id.iv_phone)
    ImageView mIvPhone;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.iv_pn)
    ImageView mIvPn;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.iv_yanz)
    ImageView mIvYanz;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.bt_getcode)
    Button mBtGetcode;
    @BindView(R.id.bt_succss)
    Button mBtSuccss;
    private CountDownTimerUtils downTimerUtils;

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
        return R.layout.activity_update_phone;
    }

    @Override
    public void initView() {
        downTimerUtils = new CountDownTimerUtils(mBtGetcode, 60000, 1000);
        String phone = SharedPrefrenceUtils.getString(UpdatePhoneActivity.this, NormalConfig.PHONE, "");
        if (!TextUtils.isEmpty(phone)) {
            mTvPhone.setText("当前手机号："+phone);
        }else {
            mTvPhone.setText("您还没有绑定手机号");
        }
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.MYPERSONDESTIALS);
    }

    @Override
    public PersonModel getModel() {
        return new PersonModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("sssssssssss",e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.UPDATEPHONE:
                UpdatePhoneBean updatePhoneBean = (UpdatePhoneBean) t[0];
                if (updatePhoneBean.getCode() == 200) {
                    showToast(updatePhoneBean.getMessage());
                    SharedPrefrenceUtils.saveString(UpdatePhoneActivity.this, NormalConfig.PHONE,"");
                    SharedPrefrenceUtils.saveString(UpdatePhoneActivity.this, NormalConfig.USER_PHOTO,"");
                    SharedPrefrenceUtils.saveString(UpdatePhoneActivity.this, NormalConfig.ICON,"");
                    SharedPrefrenceUtils.saveString(UpdatePhoneActivity.this, NormalConfig.NICKNAME,"");
                    SharedPrefrenceUtils.saveString(UpdatePhoneActivity.this, NormalConfig.TOKEN,"");
                    SharedPrefrenceUtils.saveString(UpdatePhoneActivity.this, NormalConfig.PERSONALIZED,"");
                    mApplication.icon = "";
                    mApplication.nickname = "";
                    mApplication.Personalizedsignature = "";
                    mApplication.Phone = "";
                    mApplication.Token = "";
                    mApplication.userid = "";
                    startActivity(new Intent(UpdatePhoneActivity.this,MainActivity.class));
                    finish();
                }else {
                    Log.e(updatePhoneBean.getCode()+"lllll",updatePhoneBean.getMessage());
                }
                break;
                case ApiConfig.MYPERSONDESTIALS:
                    MyPersonBean myPersonBean = (MyPersonBean) t[0];
                    if (myPersonBean.getCode() == 200) {
                        MyPersonBean.DataBean data = myPersonBean.getData();
                        mTvPhone.setText(data.getUsername());
//                        finish();
                    }else {
                        showToast(myPersonBean.getMessage());
                    }
                    break;
        }
    }

    private void initgetCode() {
        String uname = mEtPhone.getText().toString();
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
                                mEtCode.setText(data);
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

    @OnClick({R.id.bt_getcode, R.id.bt_succss})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_getcode:
            initgetCode();
                break;
            case R.id.bt_succss:
                initok();
                break;
        }
    }

    private void initok() {
        String phone = mEtPhone.getText().toString();
        String code = mEtCode.getText().toString();
        if (!phone.isEmpty()) {
            if (!code.isEmpty()) {
                mPresenter.getData(ApiConfig.UPDATEPHONE,phone,code);
            }else {
                showToast("验证码为空");
            }
        }else {
            showToast("手机号不能为空");
        }
    }
}
