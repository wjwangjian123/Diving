package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.bean.VerificationBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.frame.MyServer;
import com.example.edive.model.HomeModel;
import com.example.edive.utils.AppValidationMgr;
import com.example.edive.utils.CountDownTimerUtils;

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

//修改支付密码
public class UpdatePayPasswordActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.updatepay_ed_phone)
    EditText mUpdatepayEdPhone;
    @BindView(R.id.updatepay_ed_code)
    EditText mUpdatepayEdCode;
    @BindView(R.id.updatepay_btn_getcode)
    TextView mUpdatepayBtnGetcode;
    @BindView(R.id.updatepay_et_new_pass)
    EditText mUpdatepayEtNewPass;
    @BindView(R.id.updatepay_et_ok_new_pass)
    EditText mUpdatepayEtOkNewPass;
    @BindView(R.id.updatepay_ok_btn)
    Button mUpdatepayOkBtn;
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
        return R.layout.activity_update_pay_password;
    }

    @Override
    public void initView() {
        downTimerUtils = new CountDownTimerUtils(mUpdatepayBtnGetcode, 60000, 1000);
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
        switch (whichApi){
            case ApiConfig.VER_GETCODE:
                VerificationBean bean = (VerificationBean) t[0];
                int code = bean.getCode();
                mUpdatepayEdCode.setText(code);
                break;
        }
    }

    @OnClick({R.id.updatepay_btn_getcode, R.id.updatepay_ok_btn})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.updatepay_btn_getcode:
                String phone = mUpdatepayEdPhone.getText().toString();
                if (AppValidationMgr.isPhone(phone)) {
                    downTimerUtils.start();
                    mPresenter.getData(ApiConfig.VER_GETCODE);
                    Retrofit build = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(MyServer.url)
                            .build();
                    MyServer myServer = build.create(MyServer.class);
                    Observable<VerificationBean> verGetcode = myServer.getVerGetcode(phone);
                    verGetcode.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<VerificationBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(VerificationBean bean) {
                                    String data = bean.getData();
                                    mUpdatepayEdCode.setText(data);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }else {
                    showLongToast("请输入正确的手机号");
                }
                break;
            case R.id.updatepay_ok_btn:
                String newpassword = mUpdatepayEtNewPass.getText().toString();
                String okpassword = mUpdatepayEtOkNewPass.getText().toString();
                if (newpassword.equals(okpassword)) {

                }else {
                    showLongToast("密码不一致");
                }
                break;
        }
    }
}
