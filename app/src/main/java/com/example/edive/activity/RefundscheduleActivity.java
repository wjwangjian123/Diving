package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.edive.R;
import com.example.edive.adapter.RlvRefundAdapter;
import com.example.edive.bean.ApplyDeatilsBean;
import com.example.edive.bean.OrderDetailsBean;
import com.example.edive.design.GlideRoundTransform;
import com.example.edive.design.RoundOrCircleImage;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.TimeUtils;
import com.example.edive.local_utils.TimesUtils;
import com.example.edive.model.ProductModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class RefundscheduleActivity extends BaseMvpActivity<ProductModel> {

    @BindView(R.id.iv_tk)
    ImageView mIvTk;
    @BindView(R.id.tv_order)
    TextView mTvOrder;
    @BindView(R.id.tv_Destials)
    TextView mTvDestials;
    @BindView(R.id.iv_show)
    ImageView mIvShow;
    @BindView(R.id.iv_co_show)
    RoundOrCircleImage mIvCoShow;
    @BindView(R.id.tv_co_name)
    TextView mTvCoName;
    @BindView(R.id.tv_co_dj)
    TextView mTvCoDj;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_picse)
    TextView mTvPicse;
    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.tv_tkje)
    TextView mTvTkje;
    @BindView(R.id.tv_tk_num)
    TextView mTvTkNum;
    @BindView(R.id.viewt)
    View mViewt;
    @BindView(R.id.tv_tk_yy)
    TextView mTvTkYy;
    @BindView(R.id.tv_pay_num)
    TextView mTvPayNum;
    @BindView(R.id.rec)
    RecyclerView mRec;

    private String orderid;
    private OrderDetailsBean.ResultBean result;
    private RlvRefundAdapter rlvRefundAdapter;
    private CountDownTimer countDownTimer;

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
        return R.layout.activity_refundschedule;
    }

    @Override
    public void initView() {
        orderid = getIntent().getStringExtra("orderid");

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ORDER_DETAILS,orderid);
        mPresenter.getData(ApiConfig.APPLYDEATILS,orderid);
    }

    @Override
    public ProductModel getModel() {
        return new ProductModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.APPLYDEATILS:
                ApplyDeatilsBean applyDeatilsBean = (ApplyDeatilsBean) t[0];
                if (applyDeatilsBean.getCode() == 200) {
                    int status1 = applyDeatilsBean.getResult().getStatus();
                    if (status1 == 1) {
                        mTvOrder.setText("订单退款中");
                    }
                    ApplyDeatilsBean.ResultBean results = applyDeatilsBean.getResult();
                    mTvTkYy.setText(results.getReason());
                    String description = results.getDescription();
                    mTvPayNum.setText(description);
                    mTvTkNum.setText("￥"+results.getReturnAmount()+"");
                    List<String> picList = results.getPicList();
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(RefundscheduleActivity.this, 3);
                    mRec.setLayoutManager(gridLayoutManager);
                    rlvRefundAdapter = new RlvRefundAdapter(RefundscheduleActivity.this, picList);
                    mRec.setAdapter(rlvRefundAdapter);
                    rlvRefundAdapter.notifyDataSetChanged();
                    String createdTime = results.getCreatedTime();
                    String s4 = TimesUtils.addDateMinut(createdTime, 24);
                    String date = TimeUtils.getStringDate();
//                    int compare_date = TimesUtils.compare_date(s4, date);
                    int gapMinutes = getGapMinutes(date, s4);
                    String stringDate = TimeUtils.getStringDate();
//                    if (gapMinutes > 0) {
//                        countDownTimer = new CountDownTimer(gapMinutes, 1000) {
//                            @Override
//                            public void onTick(long millisUntilFinished) {
//                                if (!RefundscheduleActivity.this.isFinishing()) {
//
//                                    long day = millisUntilFinished / (1000 * 24 * 60 * 60); //单位天
//
//                                    long hour = (millisUntilFinished - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60);
//                                    //单位时
//                                    long minute = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60);
//                                    //单位分
//                                    long second = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;
//                                    //单位秒
//                                    mTvDestials.setText(hour + "小时" + minute + "分钟");
//                                }
//                            }
//
//                            /**
//                             *倒计时结束后调用的
//                             */
//                            @Override
//                            public void onFinish() {
//
//                            }
//
//                        };
//                        countDownTimer.start();
//                    }
                    int status = results.getStatus();
                    if (status == 0) {
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try
                        {
                            Date d1 = df.parse(s4);
                            Date d2 = df.parse(date);
                            long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
                            long days = diff / (1000 * 60 * 60 * 24);

                            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
                            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
                            mTvDestials.setText(hours + "小时" + minutes + "分钟自动确认");
                        }catch (Exception e)
                        {
                        }
                    } else if (status == 1) {
                        mTvDestials.setVisibility(View.GONE);
                    }

                }
                break;
            case ApiConfig.ORDER_DETAILS:
                OrderDetailsBean bean = (OrderDetailsBean) t[0];
                if (bean.getCode() == 200) {
                    result = bean.getResult();
                    mTvCoName.setText(result.getUserName()+"");
                    mTvCoDj.setText("V"+result.getCoachGrade());
                    Glide.with(RefundscheduleActivity.this).load(result.getUserIcon()).placeholder(R.mipmap.bg).into(mIvCoShow);
                    Glide.with(RefundscheduleActivity.this).load(result.getProductPic()).transform(new CenterCrop(),new GlideRoundTransform(RefundscheduleActivity.this,5)).placeholder(R.mipmap.bg).into(mIvShow);
                    mTvText.setText(result.getProductTitle()+"");
                    mTvPicse.setText("￥"+result.getPayAmount());
                }else {
                    showToast(bean.getCode()+":"+bean.getMessage());
                }
                break;
        }
    }
    private static int getGapMinutes(String startDate, String endDate) {

        long start = 0;

        long end = 0;

//LogUtils.e("开始结束时间" + startDate + "  " + endDate);

            try {

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                start = df.parse(startDate).getTime();

                end = df.parse(endDate).getTime();

                } catch (Exception e) {

            // TODO: handle exception

                }

//        CLog.e("开始结束时间1", (end - start) + "");

            int minutes = (int) ((end - start) / (1000 * 60));

// LogUtils.e("开始结束时间" + minutes + "");

            return minutes;

    }

}
