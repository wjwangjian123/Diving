package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.edive.R;
import com.example.edive.adapter.RlvCreaterAdapter;
import com.example.edive.bean.AddCreaterBean;
import com.example.edive.bean.CreaterBean;
import com.example.edive.bean.UpLoadBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseApplication;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.MediaLoader;
import com.example.edive.local_utils.SpacesItemDecoration;
import com.example.edive.model.PersonModel;
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
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MycertificateActivity extends BaseMvpActivity<PersonModel> {

    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<CreaterBean.DataBean> list;
    private RlvCreaterAdapter adapter;
    private ArrayList<String> PicList;
    private String path;
    private int certificateId;

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
        return R.layout.activity_mycertificate;
    }

    @Override
    public void initView() {
        Album.initialize(AlbumConfig.newBuilder(MycertificateActivity.this).setAlbumLoader(new MediaLoader()).build());
        list = new ArrayList<>();
        PicList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MycertificateActivity.this, 2);
        mRec.setLayoutManager(gridLayoutManager);
        adapter = new RlvCreaterAdapter(MycertificateActivity.this, list);
        mRec.addItemDecoration(new SpacesItemDecoration(13,0));
        mRec.setAdapter(adapter);
        adapter.setonclick(new RlvCreaterAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                certificateId = list.get(pos).getCertificateId();
                initAlbum();
            }
        });
        adapter.setonclicks(new RlvCreaterAdapter.setonclicks() {
            @Override
            public void setonclicks(int pos) {
                certificateId = list.get(pos).getCertificateId();
                initAlbum();
            }
        });
    }
    private void initAlbum() {
        Album.image(MycertificateActivity.this)
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(1)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        for (int i = 0; i < result.size(); i++) {
                            path = result.get(i).getPath();
                            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                                    .build();
                            File file = new File(path);
                            //1.设置文件以及文件上传类型封装
                            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);

                            //2.文件上传的请求体封装
                            MultipartBody body = new MultipartBody.Builder()
                                    .setType(MultipartBody.FORM)//设置文件上传Type类型为multipart/form-data
                                    .addFormDataPart("files", file.getName(), requestBody)//设置文件参数
                                    .build();

                            Request request = new Request.Builder()
                                    .url("http://47.107.50.253:8080/DmdMall/uploadFile/saveFile")
                                    .addHeader("Authorization","Bearer " + BaseApplication.getInstance().Token)
                                    .post(body)
                                    .build();

                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {

                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String str = response.body().string();

                                    Gson gson = new Gson();
                                    final UpLoadBean upLoadBean = gson.fromJson(str, UpLoadBean.class);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (upLoadBean!=null){
                                                if (upLoadBean.getCode() == 200){
//                                Glide.with(PublishVideoActivity.this).load(upLoadBean.getData().getUrl()).into(img);
//                                            showToast("上传成功");
                                                    Log.e("ben",upLoadBean.toString());
                                                    //上传成功之后返回的图片路径
                                                    String data = upLoadBean.getData();
                                                    String userid = mApplication.userid;
                                                    MediaType type = MediaType.parse("application/json;charset=UTF-8");
                                                        JSONObject jsonObject = new JSONObject();
                                                        try {
                                                            jsonObject.put("certificateId", certificateId);
                                                            jsonObject.put("pictures", data);
                                                            jsonObject.put("userId", userid);
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        String string = jsonObject.toString();
                                                        RequestBody bodys = RequestBody.create(type, string);
                                                        mPresenter.getData(ApiConfig.ADDCREATER,bodys);
//                                                finish();
//                                                FileUtils.deleteDir();//删除保存内容
                                                }else {
//                                            Toast.makeText(PublishDynamicActivity.this,upLoadBean.getCode(),Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(MycertificateActivity.this,"错误",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            });
                        }
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
        mPresenter.getData(ApiConfig.CREATERIFICATE,Integer.parseInt(mApplication.userid));
    }

    @Override
    public PersonModel getModel() {
        return new PersonModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.CREATERIFICATE:
                list.clear();
                CreaterBean createrBean = (CreaterBean) t[0];
                if (createrBean.getCode() == 200) {
                    List<CreaterBean.DataBean> data = createrBean.getData();
                    list.addAll(data);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(createrBean.getMessage());
                }
                break;
            case ApiConfig.ADDCREATER:
                AddCreaterBean bean = (AddCreaterBean) t[0];
                Log.e("sssssssssssssssss",bean.toString());
                if (bean.getCode() == 200) {
                    mPresenter.getData(ApiConfig.CREATERIFICATE,Integer.parseInt(mApplication.userid));
                    showToast(bean.getMessage());

                }else {
                    showToast(bean.getMessage());
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
