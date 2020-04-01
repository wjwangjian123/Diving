package com.example.edive.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.edive.R;
import com.example.edive.adapter.QuestionFeedbackAdapter;
import com.example.edive.bean.ApplyBean;
import com.example.edive.bean.ApplyTuiBean;
import com.example.edive.bean.OrderDetailsBean;
import com.example.edive.bean.UpLoadBean;
import com.example.edive.design.GlideRoundTransform;
import com.example.edive.design.RoundOrCircleImage;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseApplication;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.MediaLoader;
import com.example.edive.local_utils.StringUtils;
import com.example.edive.model.ProductModel;
import com.google.gson.Gson;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;
import com.zyyoona7.wheel.WheelView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApplyRefundActivity extends BaseMvpActivity<ProductModel> {

    @BindView(R.id.tv_order_num)
    TextView mTvOrderNum;
    @BindView(R.id.rl2)
    RelativeLayout mRl2;
    @BindView(R.id.iv_show)
    ImageView mIvShow;
    @BindView(R.id.iv_co_show)
    RoundOrCircleImage mIvCoShow;
    @BindView(R.id.tv_co_name)
    TextView mTvCoName;
    @BindView(R.id.tv_co_dj)
    TextView mTvCoDj;
    @BindView(R.id.tv_picse)
    TextView mTvPicse;
    @BindView(R.id.tv_text)
    TextView mTvText;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.tv_pay_num)
    TextView mTvPayNum;
    @BindView(R.id.tv_tk_text)
    TextView mTvTkText;
    @BindView(R.id.tv_pay_other)
    EditText mTvPayOther;
    @BindView(R.id.tv_appayphoto)
    TextView mTvAppayphoto;
    @BindView(R.id.ll2)
    LinearLayout mLl2;
    @BindView(R.id.tv_tkje)
    TextView mTvTkje;
    @BindView(R.id.tv_pisce_blue)
    TextView mTvPisceBlue;
    @BindView(R.id.ll)
    LinearLayout mLl;
    @BindView(R.id.rl_ok)
    RelativeLayout mRlOk;
    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<String> list;
    private List<ApplyBean.ResultBean> result;
    private String orderid;
    private ArrayList<String> orderList;
    private QuestionFeedbackAdapter mAdapter;
    private ArrayList<String> photolist;
    private int timeless;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.app_setting));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_refund;
    }

    @Override
    public void initView() {
        orderid = getIntent().getStringExtra("orderid");
        list = new ArrayList<>();
        photolist = new ArrayList<>();
        orderList = new ArrayList<>();
        Album.initialize(AlbumConfig.newBuilder(ApplyRefundActivity.this).setAlbumLoader(new MediaLoader()).build());
        GridLayoutManager manager = new GridLayoutManager(this,4);
        mRec.setLayoutManager(manager);
        mAdapter = new QuestionFeedbackAdapter(this,photolist);
        mRec.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new QuestionFeedbackAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                initAlbum();
            }
        });
        mAdapter.setonclick(new QuestionFeedbackAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                photolist.remove(pos);
                orderList.remove(pos);
                mAdapter.notifyDataSetChanged();
            }
        });

    }
    private void initAlbum() {
        Album.image(this)
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(3)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        for (int i = 0; i < result.size(); i++) {
                            String path = result.get(i).getPath();
                            photolist.add(path);
                        }
                        mAdapter.notifyDataSetChanged();
                        initApply();
                    }
                }).onCancel(new Action<String>() {
            @Override
            public void onAction(@NonNull String result) {
//                finish();
            }
        }).start();
    }
    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ORDER_DETAILS,orderid);
        mPresenter.getData(ApiConfig.APPLYREFUND);
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
            case ApiConfig.APPLYREFUND:
                ApplyBean bean = (ApplyBean) t[0];
                result = bean.getResult();
                for (int i = 0; i < result.size(); i++) {
                    list.add(result.get(i).getName());
                }
                break;
            case ApiConfig.ORDER_DETAILS:
                OrderDetailsBean orderDetailsBean = (OrderDetailsBean) t[0];
                if (orderDetailsBean.getCode() == 200) {
                    OrderDetailsBean.ResultBean results = orderDetailsBean.getResult();
                    Glide.with(ApplyRefundActivity.this).load(results.getUserIcon()).placeholder(R.mipmap.bg).into(mIvCoShow);
                    mTvCoName.setText(results.getUserName());
                    mTvCoDj.setText("V"+results.getCoachGrade());
                    Glide.with(ApplyRefundActivity.this).load(results.getProductPic()).transform(new CenterCrop(),new GlideRoundTransform(ApplyRefundActivity.this,8)).placeholder(R.mipmap.bg).into(mIvShow);
                    mTvOrderNum.setText("订单编号"+results.getOrderSn());
                    mTvText.setText(results.getProductTitle()+"");
                    mTvPicse.setText("￥"+results.getProductPrice());
                    mTvPisceBlue.setText("￥"+results.getPayAmount());
                }
                break;
            case ApiConfig.APPLYTUIKUAN:
                ApplyTuiBean applyTuiBean = (ApplyTuiBean) t[0];
                if (applyTuiBean.getCode() == 200) {
                    showToast(applyTuiBean.getMessage());
                    Intent intent = new Intent(ApplyRefundActivity.this, MyOrder.class);
                    intent.putExtra("name","staysend");
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @OnClick({R.id.tv_pay_num, R.id.rl_ok})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_pay_num:
                //选择退款原因
                initPopo();
                break;
            case R.id.rl_ok:
                String join = StringUtils.join(orderList, ",");
                String toString = mTvPayOther.getText().toString();
                String string = mTvPayNum.getText().toString();
                if (string.isEmpty()||string.equals("请选择")) {
                    showToast("请选择退款原因");
                }else {
                    MediaType type = MediaType.parse("application/json;charset=UTF-8");
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("orderSn", orderid);
                        jsonObject.put("description", toString);
                        jsonObject.put("reason", string);
                        jsonObject.put("proofPics", join);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String strings = jsonObject.toString();
                    RequestBody body = RequestBody.create(type, strings);
                    mPresenter.getData(ApiConfig.APPLYTUIKUAN,body);
                }

                break;
        }
    }

    private void initApply() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        for (int i = 0; i < photolist.size(); i++) {

            /**
             * 封装文件上传的  请求体
             */
            File file = new File(photolist.get(i));
            //1.设置文件以及文件上传类型封装
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);

            //2.文件上传的请求体封装
            MultipartBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)//设置文件上传Type类型为multipart/form-data
                    .addFormDataPart("files", file.getName(), requestBody)//设置文件参数
                    .build();

            Request request = new Request.Builder()
                    .url("http://47.107.50.253:8080/DmdMall/uploadFile/saveFile")
                    .addHeader("Authorization", "Bearer " + BaseApplication.getInstance().Token)
                    .post(body)
                    .build();

            Call call = okHttpClient.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("onFailure", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String str = response.body().string();

                    Gson gson = new Gson();
                    final UpLoadBean upLoadBean = gson.fromJson(str, UpLoadBean.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (upLoadBean != null) {
                                if (upLoadBean.getCode() == 200) {
                                    Log.e("ben", upLoadBean.toString());
                                    //上传成功之后返回的图片路径
                                    String data = upLoadBean.getData();
                                    orderList.add(data);
                                    if (photolist.size() == orderList.size()) {
//                                        String join = StringUtils.join(orderList, ",");
//                                        String toString = mTvPayOther.getText().toString();
//                                        String string = mTvPayNum.getText().toString();
//                                        if (string.isEmpty()) {
//                                            showToast("请选择退款原因");
//                                        }
//                                        MediaType type = MediaType.parse("application/json;charset=UTF-8");
//                                        JSONObject jsonObject = new JSONObject();
//                                        try {
//                                            jsonObject.put("orderSn", orderid);
//                                            jsonObject.put("description", toString);
//                                            jsonObject.put("reason", string);
//                                            jsonObject.put("proofPics", join);
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                        String strings = jsonObject.toString();
//                                        RequestBody body = RequestBody.create(type, strings);
//                                        mPresenter.getData(ApiConfig.APPLYTUIKUAN,body);
                                    }else {

                                    }
                                } else {
//                                            Toast.makeText(PublishDynamicActivity.this,upLoadBean.getCode(),Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ApplyRefundActivity.this, "错误", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
            });
        }
        if (timeless == 0) {
//            String join = StringUtils.join(orderList, ",");
//            String toString = mTvPayOther.getText().toString();
//            String string = mTvPayNum.getText().toString();
//            if (string.isEmpty()) {
//                showToast("请选择退款原因");
//            }
//            MediaType type = MediaType.parse("application/json;charset=UTF-8");
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("orderSn", orderid);
//                jsonObject.put("description", toString);
//                jsonObject.put("reason", string);
//                jsonObject.put("proofPics", join);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String strings = jsonObject.toString();
//            RequestBody body = RequestBody.create(type, strings);
//            mPresenter.getData(ApiConfig.APPLYTUIKUAN,body);
        }

    }

    private void initPopo() {
        View inflate1 = LayoutInflater.from(ApplyRefundActivity.this).inflate(R.layout.layout_popo_certificate, null);
        final WheelView<String> mRec = inflate1.findViewById(R.id.rec);
        TextView tv_cancel = inflate1.findViewById(R.id.tv_cancel);
        TextView tv_confirm = inflate1.findViewById(R.id.tv_confirm);
        final PopupWindow popupWindow = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAtLocation(inflate1, Gravity.BOTTOM,0,0);
        mRec.setNormalItemTextColor(getResources().getColor(R.color.white));
        mRec.setSelectedItemTextColor(getResources().getColor(R.color.white));
        mRec.setData(list);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvPayNum.setText(mRec.getSelectedItemData());
//                addressId = mRec.getSelectedItemData().getId();
//                mPresenter.getData(ApiConfig.CERTIFICATE_MORE,id, addressId);
                popupWindow.dismiss();
            }
        });
    }
}
