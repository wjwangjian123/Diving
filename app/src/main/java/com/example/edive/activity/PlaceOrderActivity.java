package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.edive.R;
import com.example.edive.bean.CourseProductBean;
import com.example.edive.bean.IntegralAllBean;
import com.example.edive.bean.SettlmBean;
import com.example.edive.design.GlideRoundTransform;
import com.example.edive.design.RoundOrCircleImage;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.ProductModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PlaceOrderActivity extends BaseMvpActivity<ProductModel> {

    @BindView(R.id.rl_item)
    RelativeLayout mRlItem;
    @BindView(R.id.iv_show)
    ImageView mIvShow;
    @BindView(R.id.iv_co_show)
    RoundOrCircleImage mIvCoShow;
    @BindView(R.id.tv_co_name)
    TextView mTvCoName;
    @BindView(R.id.tv_co_dj)
    TextView mTvCoDj;
    @BindView(R.id.tv_im)
    TextView mTvIm;
    @BindView(R.id.tv_picse)
    TextView mTvPicse;
    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.chek)
    CheckBox mCheck;
    @BindView(R.id.tv_integral)
    TextView mTvintegral;
//    @BindView(R.id.tv_order_num)
//    TextView mTvOrderNum;
//    @BindView(R.id.rl_one)
//    RelativeLayout mRlOne;
    @BindView(R.id.iv_jf)
    ImageView mIvJf;
    @BindView(R.id.bt_ok)
    Button mBtOk;
    private int productid;
    private int isUserIntegration = 0;
    private int useIntegration;
    private double price;
    private String data;
    private static final int DEF_DIV_SCALE = 2;

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
        return R.layout.activity_place_order;
    }

    @Override
    public void initView() {
        productid = getIntent().getExtras().getInt("productid");
//        DivingDestilasBean.ResultBean result = (DivingDestilasBean.ResultBean) getIntent().getSerializableExtra("bean");
//        DivingDestilasBean.ResultBean.UmsCoachVoBean umsCoachVo = (DivingDestilasBean.ResultBean.UmsCoachVoBean) getIntent().getSerializableExtra("beans");
//        CertifitDetailsBean.ResultBean.PmsCourseProductBean probean = (CertifitDetailsBean.ResultBean.PmsCourseProductBean) getIntent().getSerializableExtra("probean");
//        if (result != null && umsCoachVo != null) {
//            mTvText.setText(result.getTitle());
//            Glide.with(PlaceOrderActivity.this).load(result.getImage()).transform(new CenterCrop(),new GlideRoundTransform(PlaceOrderActivity.this,5)).into(mIvShow);
//            mTvPicse.setText("￥"+result.getPrice());
//
//            mTvCoDj.setText(umsCoachVo.getCoachGrade());
//            mTvCoName.setText(umsCoachVo.getCoachName());
//        }else {
//            mTvText.setText(probean.getProductName());
//            Glide.with(PlaceOrderActivity.this).load(probean.getImage()).transform(new CenterCrop(),new GlideRoundTransform(PlaceOrderActivity.this,5)).into(mIvShow);
//            mTvPicse.setText("￥"+probean.getPrice());
//            mTvCoDj.setText(probean.getCoachGrade()+"");
//            mTvCoName.setText(probean.getCoachName()+"");
//        }

            mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true) {
                        double v2 = price * 100;
                        int i3 = new Double(v2).intValue();
                        int i4 = i3 / 100;
                        Integer value = Integer.valueOf(data);
                        if (value > i3) {
                            isUserIntegration = 1;
                            useIntegration = i3;
                            mTvintegral.setText("可用"+i3+"积分抵扣"+i4+"元");
                            mTvPicse.setText("￥0.00");
                        }else {


//                        double sub = sub(price, Double.valueOf(data));
//                        if (sub < 0) {
                            Double valueOf = Double.valueOf(data);
                            double div1 = div(valueOf, 100, 0);
                            double v = price * 10;
                            double div = div(v, 100);
                            String s3 = String.valueOf(valueOf);
                            String s = String.valueOf(v);
                            String s1 = String.valueOf(div);
                            String s2 = String.valueOf(div1);
                            String s4 = subZeroAndDot(s3);
                            Integer integer = Integer.valueOf(s4);
                            int i2 = stringToInt(s2);
                            int i = stringToInt(s1);
                            int i1 = stringToInt(s);
                            mTvintegral.setText("可用" + s3 + "积分" + "抵扣" + s2 + "元");
                            isUserIntegration = 1;
                            useIntegration = integer;
                            double v1 = sub(price, div1);
                            if (v1 < 0) {
                                mTvPicse.setText("￥0.00");
                            } else {
                                mTvPicse.setText("￥" + v1);
                            }
//                        }else {
//                            mTvPicse.setText("￥"+sub);
//                            double v = sub * 100;
//                            String s = String.valueOf(v);
//                            int i = Integer.parseInt(s);
//                            useIntegration = 0;
//                        }
                        }
                    }else {
                        isUserIntegration = 0;
                        useIntegration = 0;
                        mTvPicse.setText("￥"+price);
                    }
                }
            });

    }
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }
    public int stringToInt(String string){
        int j = 0;
        String str = string.substring(0, string.indexOf(".")) + string.substring(string.indexOf(".") + 1);
        int intgeo = Integer.parseInt(str);
        return intgeo;
    }
    public static double sub(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.subtract(b2).doubleValue();
    }
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }


    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.SETTLEMPRODU,productid);
        String userid = mApplication.userid;
        mPresenter.getData(ApiConfig.INTEGRAL_ALL,Integer.parseInt(userid));
    }

    @Override
    public ProductModel getModel() {
        return new ProductModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showToast(e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.COURSEPRODICTORDER:
                CourseProductBean beans = (CourseProductBean) t[0];
                if (beans.getCode() == 200) {
                    showToast(beans.getMessage());
                    Intent intent = new Intent(PlaceOrderActivity.this, MyOrder.class);
                    intent.putExtra("name", "dfl");
                    startActivity(intent);
                    finish();
                }else {
                    showToast(beans.getCode()+":"+beans.getMessage());
                }
                break;
            case ApiConfig.SETTLEMPRODU:
                SettlmBean bean = (SettlmBean) t[0];
                if (bean.getCode() == 200) {
                    SettlmBean.ResultBean result = bean.getResult();
                    mTvCoName.setText(result.getCoachName());
                    Glide.with(PlaceOrderActivity.this).load(result.getCoachIcon()).into(mIvCoShow);
                    mTvCoDj.setText("V"+result.getCoachGrade());
                    price = result.getPrice();
                    mTvPicse.setText("￥"+ price);
                    mTvText.setText(result.getProductName());
                    Glide.with(PlaceOrderActivity.this).load(result.getImage()).transform(new CenterCrop(),new GlideRoundTransform(PlaceOrderActivity.this,5)).into(mIvShow);
                }else {
                    showToast(bean.getCode()+":"+bean.getMessage());
                }
                break;
            case ApiConfig.INTEGRAL_ALL:
                IntegralAllBean beanss = (IntegralAllBean) t[0];
                if (beanss.getCode() == 200) {
                    data = beanss.getData();
                    if (!TextUtils.isEmpty(data)) {
                        Integer integer = Integer.valueOf(data);
                        if (integer>0) {
                            mCheck.setVisibility(View.VISIBLE);
                            int i = Integer.parseInt(data);
                            if (i <1000) {
                                mTvintegral.setText("可用"+i+"积分"+"    满1000积分才可用");
                                mCheck.setVisibility(View.GONE);
                            }else {
                                double div = div(Double.valueOf(data), 100, 2);
                                mTvintegral.setText("可用"+ data + "积分抵扣"+div+"元");
                            }
                        }else if (Integer.valueOf(data)==0){
                            mTvintegral.setText("可用0积分 满1000积分才可用");
                            mCheck.setVisibility(View.GONE);
                        }else {
                            mTvintegral.setText("可用0积分 满1000积分才可用");
                            mCheck.setVisibility(View.GONE);
                        }
                    }else {
                        mTvintegral.setText("可用0积分 满1000积分才可用");
                        mCheck.setVisibility(View.GONE);
                    }



                }else {
                    String message = beanss.getMessage();
                    showToast("查询积分："+message);
                }
        }
    }
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @OnClick({R.id.bt_ok,R.id.tv_im})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_ok:
                        MediaType types = MediaType.parse("application/json;charset=UTF-8");
                        JSONObject jsonObjects = new JSONObject();
                        try {
                             jsonObjects.put("isUserIntegration", isUserIntegration);
                             jsonObjects.put("productId", productid);
                             jsonObjects.put("useIntegration", useIntegration);
                         } catch (JSONException e) {
                       e.printStackTrace();
                             }
        String strings = jsonObjects.toString();
        RequestBody bodys = RequestBody.create(types, strings);
        mPresenter.getData(ApiConfig.COURSEPRODICTORDER,bodys);
                break;
            case R.id.tv_im:

                break;
        }
    }

}
