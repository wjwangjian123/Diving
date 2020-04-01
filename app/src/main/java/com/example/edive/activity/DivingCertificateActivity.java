package com.example.edive.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.adapter.RlvCertificateMoreAdapter;
import com.example.edive.adapter.RlvDivingCetificateXcapAdapter;
import com.example.edive.bean.CertificateProductBean;
import com.example.edive.bean.CertifitDetailsBean;
import com.example.edive.bean.ContentBean;
import com.example.edive.bean.LocationBean;
import com.example.edive.design.CommonTitle;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.SpacesItemDecoration;
import com.example.edive.local_utils.StringUtils;
import com.example.edive.model.ProductModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zyyoona7.wheel.WheelView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DivingCertificateActivity extends BaseMvpActivity<ProductModel> {

    @BindView(R.id.tv_loction)
    TextView mTvLoction;
    @BindView(R.id.title)
    CommonTitle mTitle;
    @BindView(R.id.iv_show)
    ImageView mIvShow;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_en_name)
    TextView mTvEnName;
    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.tv_jbxx)
    TextView mTvJbxx;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_lengthplay)
    TextView mTvLengthplay;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_coastincludes)
    TextView mTvCoastincludes;
    @BindView(R.id.tv_costNotIncludes)
    TextView mTvCostNotIncludes;
    @BindView(R.id.tv_pic)
    TextView mTvPic;
    @BindView(R.id.rec_xcap)
    RecyclerView MRecXcap;
    @BindView(R.id.bt_gm)
    Button mBtGm;
    @BindView(R.id.bt_zx)
    Button mBtZx;
    private int id;
    private ArrayList<LocationBean.ResultBean> list;
    private ArrayList<CertificateProductBean.ResultBean.UmsCoachVosBean> vosBeanArrayList;
    private RlvCertificateMoreAdapter adapter;
    private int addressId;
    private int ids;
    private int productid;
    private CertifitDetailsBean.ResultBean.PmsCertificateBean pmsCertificate;
    private CertifitDetailsBean.ResultBean.PmsCourseProductBean product;
    private int addressId1;
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
        return R.layout.activity_diving_certificate;
    }

    @Override
    public void initView() {
        id = getIntent().getExtras().getInt("id");
        list = new ArrayList<>();
        mTitle.mMoreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initpoposhare();
            }
        });
        vosBeanArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DivingCertificateActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvCertificateMoreAdapter(DivingCertificateActivity.this, vosBeanArrayList);
        mRec.addItemDecoration(new SpacesItemDecoration(5,0));
        mRec.setAdapter(adapter);
        adapter.setonclick(new RlvCertificateMoreAdapter.setonclcik() {
            @Override
            public void setonclick(int pos) {
                ids = vosBeanArrayList.get(pos).getId();
                MediaType types = MediaType.parse("application/json;charset=UTF-8");
                JSONObject jsonObjects = new JSONObject();
                try {
                    jsonObjects.put("addressId", addressId);
                    jsonObjects.put("certificateId", id);
                    jsonObjects.put("userId", ids);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String strings = jsonObjects.toString();
                RequestBody bodys = RequestBody.create(types, strings);
                mPresenter.getData(ApiConfig.CERTIFICATEDETAILS,bodys);
                adapter.setThisPosition(pos);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.DIBINGCERTIFICATE);
        mPresenter.getData(ApiConfig.CERTIFICATE_MORE,id);

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
            case ApiConfig.DIBINGCERTIFICATE:
                list.clear();
                LocationBean bean = (LocationBean) t[0];
                List<LocationBean.ResultBean> result = bean.getResult();
                list.addAll(result);
                break;
            case ApiConfig.CERTIFICATE_MORE:
                vosBeanArrayList.clear();
                CertificateProductBean productBean = (CertificateProductBean) t[0];
                if (productBean.getCode() == 200) {
                    CertificateProductBean.ResultBean beanResult = productBean.getResult();
                    String location = beanResult.getLocation();
                    mTvLoction.setText(location);
                    addressId = beanResult.getAddressId();
                    status = beanResult.getStatus();
//                    productid = productBean.getCode();
                    List<CertificateProductBean.ResultBean.UmsCoachVosBean> umsCoachVos = productBean.getResult().getUmsCoachVos();
                    productid = umsCoachVos.get(0).getId();
                    ids = umsCoachVos.get(0).getId();
                    vosBeanArrayList.addAll(umsCoachVos);
                    adapter.notifyDataSetChanged();
                    CertificateProductBean.ResultBean.PmsCertificateVoBean pmsCertificateVo = productBean.getResult().getPmsCertificateVo();
                    mTvName.setText(pmsCertificateVo.getEnglishShorthand()+"潜水证书");
                    mTvEnName.setText(pmsCertificateVo.getEnglishName());
                    Glide.with(DivingCertificateActivity.this)
                            .load(pmsCertificateVo.getPic())
                            .into(mIvShow);
//                    mTvJbxx.setText(pmsCertificateVo.getEnglishName());
                    mTvText.setText(pmsCertificateVo.getIntroduction());
                    CertificateProductBean.ResultBean result1 = productBean.getResult();
                    mTvPrice.setText("￥"+result1.getPrice());
                    mTvPic.setText("￥"+result1.getPrice());
                    mTvCoastincludes.setText(result1.getCostIncludes());
                    mTvCostNotIncludes.setText(result1.getCostNotIncludes());
                    mTvLocation.setText(result1.getLocation());
                    mTvLengthplay.setText(result1.getLengthPlay());
                    String contentArrangement = result1.getContentArrangement();
                    //Json的解析类对象
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
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DivingCertificateActivity.this);
                    MRecXcap.setLayoutManager(linearLayoutManager);
                    xcapAdapter = new RlvDivingCetificateXcapAdapter(DivingCertificateActivity.this, userBeanList);
                    MRecXcap.setAdapter(xcapAdapter);

//                    ArrayList<String> date = new ArrayList<>();
//                    for (int i = 0; i < userBeanList.size(); i++) {
//                        date.add(R.drawable.yuan+"   "+userBeanList.get(i).getDate()+"\n"+"   "+userBeanList.get(i).getMessage()+"\n"+"\n");
//                    }
//                    String join = StringUtils.join(date, ",");
//                    String replace = join.replace(",", "");

                    MediaType types = MediaType.parse("application/json;charset=UTF-8");
                    JSONObject jsonObjects = new JSONObject();
                    try {
                        jsonObjects.put("addressId", addressId);
                        jsonObjects.put("certificateId", id);
                        jsonObjects.put("userId", ids);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String strings = jsonObjects.toString();
                    RequestBody bodys = RequestBody.create(types, strings);
                    mPresenter.getData(ApiConfig.CERTIFICATEDETAILS,bodys);
                }else {
                    showToast(productBean.getCode()+":"+productBean.getMessage());
                }

                break;
            case ApiConfig.CERTIFICATE_MORES:
                vosBeanArrayList.clear();
                CertificateProductBean productBeans = (CertificateProductBean) t[0];
                if (productBeans.getCode() == 200) {
                    CertificateProductBean.ResultBean beanResult = productBeans.getResult();
                    String location = beanResult.getLocation();
                    mTvLoction.setText(location);
                    addressId = beanResult.getAddressId();
//                    productid = productBean.getCode();
                    List<CertificateProductBean.ResultBean.UmsCoachVosBean> umsCoachVos = productBeans.getResult().getUmsCoachVos();
                    productid = umsCoachVos.get(0).getId();
                    ids = umsCoachVos.get(0).getId();
                    vosBeanArrayList.addAll(umsCoachVos);
                    adapter.notifyDataSetChanged();
                    CertificateProductBean.ResultBean.PmsCertificateVoBean pmsCertificateVo = productBeans.getResult().getPmsCertificateVo();
                    mTvName.setText(pmsCertificateVo.getEnglishShorthand()+"潜水证书");
                    mTvEnName.setText(pmsCertificateVo.getEnglishName());
                    Glide.with(DivingCertificateActivity.this)
                            .load(pmsCertificateVo.getPic())
                            .into(mIvShow);
//                    mTvJbxx.setText(pmsCertificateVo.getEnglishName());
                    mTvText.setText(pmsCertificateVo.getIntroduction());
                    CertificateProductBean.ResultBean result1 = productBeans.getResult();
                    mTvPrice.setText("￥"+result1.getPrice());
                    mTvPic.setText("￥"+result1.getPrice());
                    mTvCoastincludes.setText(result1.getCostIncludes());
                    mTvCostNotIncludes.setText(result1.getCostNotIncludes());
                    mTvLocation.setText(result1.getLocation());
                    mTvLengthplay.setText(result1.getLengthPlay());
                    MediaType types = MediaType.parse("application/json;charset=UTF-8");
                    JSONObject jsonObjects = new JSONObject();
                    try {
                        jsonObjects.put("addressId", addressId);
                        jsonObjects.put("certificateId", id);
                        jsonObjects.put("userId", ids);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String strings = jsonObjects.toString();
                    RequestBody bodys = RequestBody.create(types, strings);
                    mPresenter.getData(ApiConfig.CERTIFICATEDETAILS,bodys);
                }else {
                    showToast(productBeans.getCode()+":"+productBeans.getMessage());
                }

                break;
            case ApiConfig.CERTIFICATEDETAILS:
                CertifitDetailsBean detailsBean = (CertifitDetailsBean) t[0];
                if (detailsBean.getCode() == 200) {
                    product = detailsBean.getResult().getPmsCourseProduct();
                    pmsCertificate = detailsBean.getResult().getPmsCertificate();
                    productid = product.getId();
                    mTvJbxx.setText(product.getTitle());
                    mTvName.setText(pmsCertificate.getEnglishShorthand()+"潜水证书");
                    mTvEnName.setText(pmsCertificate.getEnglishName());
                    Glide.with(DivingCertificateActivity.this)
                            .load(pmsCertificate.getPic())
                            .into(mIvShow);
                    mTvText.setText(pmsCertificate.getIntroduction());

                    mTvPrice.setText("￥"+ product.getPrice());
                    mTvPic.setText("￥"+ product.getPrice());
                    mTvCoastincludes.setText(product.getCostIncludes());
                    mTvCostNotIncludes.setText(product.getCostNotIncludes());
                    mTvLocation.setText(product.getLocation());
                    mTvLengthplay.setText(product.getLengthPlay());
                }else {
                    showToast(detailsBean.getCode()+":"+detailsBean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.tv_loction, R.id.bt_gm, R.id.bt_zx})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_loction:
                initPopo();
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
                    Intent intent = new Intent(DivingCertificateActivity.this, PlaceOrderActivity.class);
                    intent.putExtra("productid", productid);
                    startActivity(intent);
                }

                break;
            case R.id.bt_zx:
                break;
        }
    }
    private void initpoposhare() {
        View inflate1 = LayoutInflater.from(DivingCertificateActivity.this).inflate(R.layout.layout_popo_share, null);
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
        WindowManager.LayoutParams lp = DivingCertificateActivity.this.getWindow().getAttributes();
        lp.alpha = 0.6f;
        DivingCertificateActivity.this.getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = DivingCertificateActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                DivingCertificateActivity.this.getWindow().setAttributes(lp);
            }
        });
        rl_diss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    private void initPopo() {
         View inflate1 = LayoutInflater.from(DivingCertificateActivity.this).inflate(R.layout.layout_popo_certificate, null);
         final WheelView<LocationBean.ResultBean> mRec = inflate1.findViewById(R.id.rec);
        TextView tv_cancel = inflate1.findViewById(R.id.tv_cancel);
        TextView tv_confirm = inflate1.findViewById(R.id.tv_confirm);
        final PopupWindow popupWindow = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                         popupWindow.setBackgroundDrawable(new ColorDrawable());
                         popupWindow.setOutsideTouchable(true);
                         popupWindow.setAnimationStyle(R.style.PopupAnimation);
                         popupWindow.showAtLocation(inflate1, Gravity.BOTTOM,0,0);
                         mRec.setData(list);
                         mRec.setNormalItemTextColor(getResources().getColor(R.color.white));
                         mRec.setSelectedItemTextColor(getResources().getColor(R.color.blue));
                         mRec.setTextSize(15,true);
              tv_cancel.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      popupWindow.dismiss();
                  }
              });
              tv_confirm.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      mTvLoction.setText(String.valueOf(mRec.getSelectedItemData().getAddress()));
                      addressId = mRec.getSelectedItemData().getId();
//                      list.clear();
                      mPresenter.getData(ApiConfig.CERTIFICATE_MORES,id, addressId);
                      popupWindow.dismiss();
                  }
              });
    }
    public String ArrayListToString(ArrayList<String> PicList) {
        String item = "";
        if (PicList != null && PicList.size() > 0) {
            for (String str : PicList) {
                item = str + ",";
            }
            item = item.substring(0, item.length() - 1);
        }
        return item;
    }
}
