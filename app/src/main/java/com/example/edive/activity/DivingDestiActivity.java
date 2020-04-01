package com.example.edive.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.adapter.RlvDivingCetificateXcapAdapter;
import com.example.edive.bean.ContentBean;
import com.example.edive.bean.DivingDestilasBean;
import com.example.edive.design.RoundOrCircleImage;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.StringUtils;
import com.example.edive.model.ProductModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DivingDestiActivity extends BaseMvpActivity<ProductModel> {

    @BindView(R.id.rec)
    Banner mRec;
    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.tv_picse)
    TextView mTvPicse;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_lengthPlay)
    TextView mTvLengthPlay;
    @BindView(R.id.tv_jjjj)
    TextView mTvJjjj;
    @BindView(R.id.iv_co_pic)
    RoundOrCircleImage mIvCoPic;
    @BindView(R.id.tv_co_name)
    TextView mTvCoName;
    @BindView(R.id.tv_co_dj)
    TextView mTvCoDj;
    @BindView(R.id.tv_co_text)
    TextView mTvCoText;
    @BindView(R.id.tv_start_time)
    TextView mTvStartTime;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.tv_coastincludes)
    TextView mTvCoastincludes;
    @BindView(R.id.tv_costNotIncludes)
    TextView mTvCostNotIncludes;
    @BindView(R.id.rec_xcap)
    RecyclerView mRecXcap;
    @BindView(R.id.tv_pic)
    TextView mTvPic;
    @BindView(R.id.bt_gm)
    Button mBtGm;
    @BindView(R.id.bt_zx)
    Button mBtZx;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.tv_Message)
    TextView mTvMessage;
    private int id;
    private ArrayList<String> list;
    private DivingDestilasBean bean;
    private DivingDestilasBean.ResultBean.UmsCoachVoBean umsCoachVo;
    private DivingDestilasBean.ResultBean resultBean;
    private RlvDivingCetificateXcapAdapter xcapAdapter;
    private int status;

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
        return R.layout.activity_diving_desti;
    }

    @Override
    public void initView() {
        id = getIntent().getExtras().getInt("id");
        list = new ArrayList<>();

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.DIVING_DESTILAS, id);
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
        switch (whichApi) {
            case ApiConfig.DIVING_DESTILAS:
                bean = (DivingDestilasBean) t[0];
                if (bean.getCode() == 200) {

                    String image = bean.getResult().getImage();
                    String[] split = image.split(",");
                    for (int i = 0; i < split.length; i++) {
                        list.add(split[i]);
                    }
                    mRec.setImages(list);
                    mRec.setImageLoader(new MyLoader());
                    mRec.start();
                    resultBean = bean.getResult();
                    status = resultBean.getStatus();
                    mTvText.setText(resultBean.getTitle());
                    mTvLengthPlay.setText(resultBean.getLengthPlay());
                    mTvPic.setText("￥" + resultBean.getPrice());
                    mTvPicse.setText("￥" + resultBean.getPrice());
                    mTvLocation.setText(resultBean.getLocation());
                    mTvCoastincludes.setText(resultBean.getCostIncludes());
                    mTvCostNotIncludes.setText(resultBean.getCostNotIncludes());
                    mTvStartTime.setText(resultBean.getStartTime());
                    mTvEndTime.setText(resultBean.getEndTime());
                    umsCoachVo = bean.getResult().getUmsCoachVo();
                    mTvCoDj.setText("V"+umsCoachVo.getCoachGrade());
                    mTvCoText.setText(umsCoachVo.getInvitationCode());
                    mTvCoName.setText(umsCoachVo.getNickName());
                    Glide.with(DivingDestiActivity.this).load(umsCoachVo.getIcon()).placeholder(R.mipmap.morentouxiang).into(mIvCoPic);

                    String contentArrangement = resultBean.getContentArrangement();
                    JsonParser parser = new JsonParser();
                    //将JSON的String 转成一个JsonArray对象
                    JsonArray jsonArray = parser.parse(contentArrangement).getAsJsonArray();

                    Gson gson = new Gson();
                    ArrayList<ContentBean> userBeanList = new ArrayList<>();

                    //加强for循环遍历JsonArray
                    for (JsonElement user : jsonArray) {
                        //使用GSON，直接转成Bean对象
                        ContentBean userBean = gson.fromJson(user, ContentBean.class);
                        userBeanList.add(userBean);
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DivingDestiActivity.this);
                    mRecXcap.setLayoutManager(linearLayoutManager);
                    xcapAdapter = new RlvDivingCetificateXcapAdapter(DivingDestiActivity.this, userBeanList);
                    mRecXcap.setAdapter(xcapAdapter);
//                    ArrayList<String> date = new ArrayList<>();
//                    for (int i = 0; i < userBeanList.size(); i++) {
//                        date.add("   "+userBeanList.get(i).getDate()+"\n"+"        "+userBeanList.get(i).getMessage()+"\n");
//                    }
//                    String join = StringUtils.join(date, ",");
//                    String replace = join.replace(",", "");
//                    mTvXcap.setText(replace);
                } else {
                    showToast(bean.getCode() + ":" + bean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.bt_gm, R.id.bt_zx, R.id.iv_back, R.id.iv_share, R.id.tv_Message})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_gm:
                if (status==2) {
                    showToast("商品已下架");
                } else if (status==3) {
                    showToast("商品已删除");
                } else if (status==4) {
                    showToast("商品停止销售");
                } else if (status == 5) {
                    showToast("商品已售完");
                }else {
                    Intent intent = new Intent(DivingDestiActivity.this, PlaceOrderActivity.class);
                    intent.putExtra("productid", id);
                    startActivity(intent);
                }
//                intent.putExtra("bean", (Serializable) resultBean);
//                intent.putExtra("beans", (Serializable) umsCoachVo);
                break;
            case R.id.bt_zx:

                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                //分享
                initpoposhare();
                break;
            case R.id.tv_Message:
                //
                Intent intent1 = new Intent(DivingDestiActivity.this, AllraiseMessagerListActivity.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
                break;
        }
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
    private void initpoposhare() {
        View inflate1 = LayoutInflater.from(DivingDestiActivity.this).inflate(R.layout.layout_popo_share, null);
        TextView tv_wechat = inflate1.findViewById(R.id.tv_wechat);
        TextView tv_penyouq = inflate1.findViewById(R.id.tv_penyouq);
        TextView tv_club = inflate1.findViewById(R.id.tv_club);
        RelativeLayout rl_diss = inflate1.findViewById(R.id.rl_diss);
        final PopupWindow popupWindow = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAtLocation(inflate1, Gravity.BOTTOM,0,0);
        //         设置背景颜色变暗
        WindowManager.LayoutParams lp = DivingDestiActivity.this.getWindow().getAttributes();
        lp.alpha = 0.6f;
        DivingDestiActivity.this.getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = DivingDestiActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                DivingDestiActivity.this.getWindow().setAttributes(lp);
            }
        });
        rl_diss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
