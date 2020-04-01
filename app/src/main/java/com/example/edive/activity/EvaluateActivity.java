package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edive.R;
import com.example.edive.adapter.QuestionFeedbackAdapter;
import com.example.edive.bean.EvaluateBean;
import com.example.edive.bean.UpLoadBean;
import com.example.edive.design.CommonTitle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EvaluateActivity extends BaseMvpActivity<ProductModel> {

    @BindView(R.id.title)
    CommonTitle mTitle;
    @BindView(R.id.et_evaluate)
    EditText mEtEvaluate;
    @BindView(R.id.rec)
    RecyclerView mRec;
    private String string;
    private int orderId;
    private int productId;
    private ArrayList<String> photolist;
    private QuestionFeedbackAdapter mAdapter;
    private ArrayList<String> orderList;

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
        return R.layout.activity_evaluate;
    }

    @Override
    public void initView() {
        orderId = getIntent().getExtras().getInt("orderId");
        productId = getIntent().getExtras().getInt("productId");
        Album.initialize(AlbumConfig.newBuilder(EvaluateActivity.this).setAlbumLoader(new MediaLoader()).build());
        photolist = new ArrayList<>();
        orderList = new ArrayList<>();
        Album.initialize(AlbumConfig.newBuilder(EvaluateActivity.this).setAlbumLoader(new MediaLoader()).build());
        GridLayoutManager manager = new GridLayoutManager(this,3);
        mRec.setLayoutManager(manager);
        mAdapter = new QuestionFeedbackAdapter(this,photolist);
        mRec.setAdapter(mAdapter);
        mAdapter.setonclick(new QuestionFeedbackAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                photolist.remove(pos);
                orderList.remove(pos);
                mAdapter.notifyDataSetChanged();
            }
        });
        mAdapter.setOnItemClickListener(new QuestionFeedbackAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                initAlbum();
            }
        });
        mEtEvaluate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length == 200) {
                    showToast("最多可输入200字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTitle.mMoreText1.getResources().getColor(R.color.blue);
        mTitle.mMoreText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String join = StringUtils.join(orderList, ",");
                string = mEtEvaluate.getText().toString();
                if (string.isEmpty()) {
                    showToast("评价内容不能为空");
                }else {
                    MediaType types = MediaType.parse("application/json;charset=UTF-8");
                    JSONObject jsonObjects = new JSONObject();
                    try {
                        jsonObjects.put("info", string);
                        jsonObjects.put("orderId", orderId);
                        jsonObjects.put("productId", productId);
                        jsonObjects.put("pic", join);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String strings = jsonObjects.toString();
                    RequestBody body = RequestBody.create(types, strings);

                    mPresenter.getData(ApiConfig.EVALUATE,body);
                }
            }
        });
    }

    private void initapp() {
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

                                    }
                                } else {
//                                            Toast.makeText(PublishDynamicActivity.this,upLoadBean.getCode(),Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(EvaluateActivity.this, "错误", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
            });
        }
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
                        initapp();
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
            case ApiConfig.EVALUATE:
                EvaluateBean evaluateBean = (EvaluateBean) t[0];
                if (evaluateBean.getCode() == 200) {
                    showToast("提交成功");
                    finish();
                }else {
                    String message = evaluateBean.getMessage();
                    showToast(message);
                }
                break;
        }
    }
}
