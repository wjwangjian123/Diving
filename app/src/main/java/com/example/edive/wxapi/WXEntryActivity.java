package com.example.edive.wxapi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.edive.activity.HomeActivity;
import com.example.edive.activity.MainActivity;
import com.example.edive.bean.LoginBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseActivity;
import com.example.edive.frame.BaseApplication;
import com.example.edive.frame.MyServer;
import com.example.edive.utils.NormalConfig;
import com.example.edive.utils.SharedPrefrenceUtils;
import com.example.edive.utils.ToastUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private String logintype = "member";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiConfig.wx_api.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        SendAuth.Resp resp = (SendAuth.Resp) baseResp;
        String code = resp.code;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //成功
                Log.e("code",code);
                EventBus.getDefault().postSticky(code);
                finish();
//                initWechatLogin(code);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //取消
                Toast.makeText(WXEntryActivity.this, "您已取消授权", Toast.LENGTH_SHORT).show();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(WXEntryActivity.this, "您已拒绝消授权", Toast.LENGTH_SHORT).show();
                //被拒绝
                break;
            default:
                //返回
                break;

        }
//        finish();
    }

    private void initWechatLogin(String code) {
        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(MyServer.url)
                .build();
        MyServer myServer = build.create(MyServer.class);
        Observable<LoginBean> wechatLogin = myServer.getWechatLogin(code, logintype);
        wechatLogin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getCode() == 200) {
                            ToastUtil.showLong(loginBean.getMessage());
                            BaseApplication mApplication = new BaseApplication();
                            mApplication.Token = loginBean.getData().getValue();
                            mApplication.userid = String.valueOf(loginBean.getData().getAdditionalInformation().getId());
                            mApplication.nickname = loginBean.getData().getAdditionalInformation().getNickName();
                            mApplication.icon = loginBean.getData().getAdditionalInformation().getIcon();
                            String token = mApplication.Token;

//                            SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.PERSONALIZED,bean.getData().getAdditionalInformation().getPersonalizedSignature());
                            SharedPrefrenceUtils.saveString(WXEntryActivity.this, NormalConfig.TOKEN,loginBean.getData().getValue());
//                            SharedPrefrenceUtils.saveString(MainActivity.this, NormalConfig.PHONE,bean.getData().getAdditionalInformation().getUsername());
                            SharedPrefrenceUtils.saveString(WXEntryActivity.this, NormalConfig.USER_PHOTO,String.valueOf(loginBean.getData().getAdditionalInformation().getId()));
                            SharedPrefrenceUtils.saveString(WXEntryActivity.this, NormalConfig.NICKNAME,loginBean.getData().getAdditionalInformation().getNickName());
                            SharedPrefrenceUtils.saveString(WXEntryActivity.this, NormalConfig.ICON,loginBean.getData().getAdditionalInformation().getIcon());
                            startActivity(new Intent(WXEntryActivity.this, HomeActivity.class));
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
}
