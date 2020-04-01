package com.example.edive.activity;

import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.edive.R;
import com.example.edive.bean.IntegralDeatlisBean;
import com.example.edive.design.GlideRoundTransform;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ExchangeIntegralDeatilsActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_Integral)
    TextView mTvIntegral;
    @BindView(R.id.tv_one)
    TextView mTvOne;
    @BindView(R.id.tv_two)
    TextView mTvTwo;
    @BindView(R.id.tv_three)
    TextView mTvThree;
    @BindView(R.id.tv_four)
    TextView mTvFour;
    @BindView(R.id.tv_s)
    TextView mTvS;
    @BindView(R.id.tv_m)
    TextView mTvM;
    @BindView(R.id.tv_l)
    TextView mTvL;
    @BindView(R.id.tv_xl)
    TextView mTvXl;
    @BindView(R.id.tv_xxl)
    TextView mTvXxl;
    @BindView(R.id.bt_ok)
    Button mBtOk;
    private ArrayList<IntegralDeatlisBean.DataBean> list;
    private ArrayList<String> strList = new ArrayList<>();

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
        return R.layout.activity_exchange_integral_deatils;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        list = getIntent().getParcelableArrayListExtra("in");
        mTvName.setText(list.get(0).getName());
        mTvIntegral.setText(list.get(0).getIntegral()+"积分");
        mBanner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, -20, view.getWidth(), view.getHeight(), 60);
            }
        });

        mBanner.setClipToOutline(true);
        String picture = list.get(0).getPicture();
        String[] split = picture.split(",");
        for (int i = 0; i < split.length; i++) {
            strList.add(split[i]);
        }
        mBanner.setImages(strList);
        mBanner.setImageLoader(new MyLoader());
        mBanner.start();

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

    @OnClick({R.id.iv_back, R.id.tv_one, R.id.tv_two, R.id.tv_three, R.id.tv_four, R.id.tv_s, R.id.tv_m, R.id.tv_l, R.id.tv_xl, R.id.tv_xxl, R.id.bt_ok})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_one:
                break;
            case R.id.tv_two:
                break;
            case R.id.tv_three:
                break;
            case R.id.tv_four:
                break;
            case R.id.tv_s:
                break;
            case R.id.tv_m:
                break;
            case R.id.tv_l:
                break;
            case R.id.tv_xl:
                break;
            case R.id.tv_xxl:
                break;
            case R.id.bt_ok:
                break;
        }
    }
    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).transform(new CenterCrop(),new GlideRoundTransform(ExchangeIntegralDeatilsActivity.this,40)).into(imageView);
        }
    }
}
