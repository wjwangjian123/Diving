package com.example.edive.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.adapter.QuestionFeedbackAdapter;
import com.example.edive.bean.MyPersonBean;
import com.example.edive.bean.PersonBean;
import com.example.edive.bean.UpLoadBean;
import com.example.edive.design.RoundImage;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseApplication;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.MediaLoader;
import com.example.edive.local_utils.SharedPrefrenceUtils;
import com.example.edive.local_utils.StringUtils;
import com.example.edive.model.PersonModel;
import com.example.edive.utils.NormalConfig;
import com.google.gson.Gson;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumConfig;
import com.yanzhenjie.album.AlbumFile;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.TResult;
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

public class PersonalDataActivity extends BaseMvpActivity<PersonModel> implements TakePhoto.TakeResultListener {


    @BindView(R.id.iv_photomore)
    ImageView mIvPhotomore;
    @BindView(R.id.iv_photos)
    RoundImage mIvPhotos;
    @BindView(R.id.rl_iv_photo)
    RelativeLayout mRlIvPhoto;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.rl_name)
    RelativeLayout mRlName;
    @BindView(R.id.tv_phone_more)
    TextView mTvPhoneMore;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.iv_wechat_more)
    ImageView mIvWechatMore;
    @BindView(R.id.tv_wechat)
    TextView mTvWechat;
    @BindView(R.id.rl_wechat)
    RelativeLayout mRlWechat;
    @BindView(R.id.tv_ph)
    TextView mTvPh;
    @BindView(R.id.rl_photo)
    RelativeLayout mRlPhoto;
    @BindView(R.id.et_text)
    TextView mEtText;
    @BindView(R.id.ll_text)
    LinearLayout mLl;
    @BindView(R.id.rec)
    RecyclerView mRec;
    private PopupWindow popupWindow;
    private TakePhotoImpl mTakephoto;
    private String name;
    private String string;
    private QuestionFeedbackAdapter adapters;
    private ArrayList<String> photoList;
    private ArrayList<String> strlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.app_setting));
        }
    }

    private void getPermission() {
        XXPermissions.with(this)
                .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求 6.0 悬浮窗权限 8.0 请求安装权限
                .permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE) //不指定权限则自动获取清单中的危险权限
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (denied.size() != 0) showToast("拒绝权限影响您正常使用");
                    }
                });
        //XXPermissions.gotoPermissionSettings(this);//跳转到权限设置页面
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_data;
    }

    @Override
    public void initView() {
        mTakephoto = new TakePhotoImpl(this, this);
        getPermission();
        strlist = new ArrayList<>();
        String phone = SharedPrefrenceUtils.getString(PersonalDataActivity.this, NormalConfig.PHONE, "");
        mTvPhone.setText(phone);
        String name = SharedPrefrenceUtils.getString(PersonalDataActivity.this, NormalConfig.NICKNAME, "");
        mTvName.setText(name);
        String icon = SharedPrefrenceUtils.getString(PersonalDataActivity.this, NormalConfig.ICON, "");
        Glide.with(PersonalDataActivity.this).load(icon).into(mIvPhotos);
        Album.initialize(AlbumConfig.newBuilder(PersonalDataActivity.this).setAlbumLoader(new MediaLoader()).build());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PersonalDataActivity.this, 3);
        photoList = new ArrayList<>();
        mRec.setLayoutManager(gridLayoutManager);
        adapters = new QuestionFeedbackAdapter(PersonalDataActivity.this, photoList);
        mRec.setAdapter(adapters);
        adapters.setonclick(new QuestionFeedbackAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                photoList.remove(pos);
                adapters.notifyDataSetChanged();
                String join = StringUtils.join(photoList, ",");

                MediaType types = MediaType.parse("application/json;charset=UTF-8");
                JSONObject jsonObjects = new JSONObject();
                try {
                    jsonObjects.put("pics", join);
                    jsonObjects.put("username", SharedPrefrenceUtils.getString(PersonalDataActivity.this, NormalConfig.PHONE));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String str = jsonObjects.toString();
                RequestBody body = RequestBody.create(types, str);
                mPresenter.getData(ApiConfig.XIUGAIGERENXX,body);

            }
        });
        adapters.setOnItemClickListener(new QuestionFeedbackAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                initAlbum();
            }
        });


        mTvName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //昵称
                    MediaType types = MediaType.parse("application/json;charset=UTF-8");
                    JSONObject jsonObjects = new JSONObject();
                    try {
                        jsonObjects.put("nickname", mTvName.getText().toString());
                        jsonObjects.put("username", SharedPrefrenceUtils.getString(PersonalDataActivity.this, NormalConfig.PHONE));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String str = jsonObjects.toString();
                    RequestBody body = RequestBody.create(types, str);
                    mPresenter.getData(ApiConfig.XIUGAIGERENXX, body);
                }
                return false;
            }
        });
        mEtText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //个人签名
                    MediaType types = MediaType.parse("application/json;charset=UTF-8");
                    JSONObject jsonObjects = new JSONObject();
                    try {
                        jsonObjects.put("personalizedSignature", mEtText.getText().toString());
                        jsonObjects.put("username", SharedPrefrenceUtils.getString(PersonalDataActivity.this, NormalConfig.PHONE));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String str = jsonObjects.toString();
                    RequestBody body = RequestBody.create(types, str);
                    mPresenter.getData(ApiConfig.XIUGAIGERENXX, body);
                }
                return false;
            }
        });
        mEtText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mEtText.setSingleLine(false);
        mEtText.setHorizontallyScrolling(false);

    }
    private void initAlbum() {
        Album.image(PersonalDataActivity.this)
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(5)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        for (int i = 0; i < result.size(); i++) {
                            String path = result.get(i).getPath();
                            photoList.add(path);
                            adapters.notifyDataSetChanged();
                        }
                        initupdate();

                    }
                }).onCancel(new Action<String>() {
            @Override
            public void onAction(@NonNull String result) {
//                finish();
            }
        }).start();
    }

    private void initupdate() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        for (int j = 0; j < photoList.size(); j++) {

            /**
             * 封装文件上传的  请求体
             */
            File file = new File(photoList.get(j));
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
                    Log.e("onFailure",e.getMessage());
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
                                    strlist.add(data);
                                    if (photoList.size() == strlist.size()) {
                                        String join = StringUtils.join(strlist, ",");
                                        MediaType types = MediaType.parse("application/json;charset=UTF-8");
                                        JSONObject jsonObjects = new JSONObject();
                                        try {
                                            jsonObjects.put("pics", join);
                                            jsonObjects.put("username", SharedPrefrenceUtils.getString(PersonalDataActivity.this, NormalConfig.PHONE));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        String str = jsonObjects.toString();
                                        RequestBody body = RequestBody.create(types, str);
                                        mPresenter.getData(ApiConfig.XIUGAIGERENXX,body);
//                                                finish();
//                                                FileUtils.deleteDir();//删除保存内容
                                    }
                                }else {
//                                            Toast.makeText(PublishDynamicActivity.this,upLoadBean.getCode(),Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(PersonalDataActivity.this,"错误",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
            });
        }
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.MYPERSONDESTIALS);
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
        switch (whichApi) {
            case ApiConfig.MYPERSONDESTIALS:
                MyPersonBean myPersonBean = (MyPersonBean) t[0];
                if (myPersonBean.getCode() == 200) {
                    photoList.clear();
                    MyPersonBean.DataBean data = myPersonBean.getData();
                    name = data.getNickname();
                    string = data.getPersonalizedSignature();
                    mTvName.setText(data.getNickname());
                    String phone = data.getPhone();
                    if (!TextUtils.isEmpty(phone)) {
                        mTvPhone.setText(phone);
                    }else {
                        mTvPhoneMore.setText("绑定");
                    }
                    mEtText.setText(data.getPersonalizedSignature());
                    Glide.with(PersonalDataActivity.this).load(data.getIcon()).error(R.mipmap.morentouxiang).into(mIvPhotos);
                    String dynamicPicture = data.getPics();
                    if (!TextUtils.isEmpty(dynamicPicture)) {
                        String[] split = dynamicPicture.split(",");
                        for (int j = 0; j < split.length; j++) {
                            photoList.add(split[j]);
                        }
                        adapters.notifyDataSetChanged();
                    }
                } else {
                    showToast(myPersonBean.getMessage());
                }
                break;
            case ApiConfig.XIUGAIGERENXX:
//                photoList.clear();
                PersonBean personBean = (PersonBean) t[0];
                if (personBean.getCode() == 200) {
                    showToast("操作成功");
                    mPresenter.getData(ApiConfig.MYPERSONDESTIALS);
                } else {
                    showToast(personBean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.rl_name, R.id.tv_phone_more, R.id.rl_iv_photo, R.id.ll_text})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rl_name:
                //昵称
                Intent intent = new Intent(PersonalDataActivity.this, UpdateNameActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
            case R.id.tv_phone_more:
                //手机号
                startActivity(new Intent(PersonalDataActivity.this, UpdatePhoneActivity.class));
                break;
            case R.id.rl_iv_photo:
                //头像
                initPops();
                break;
            case R.id.ll_text:
                Intent intent1 = new Intent(PersonalDataActivity.this, UpdateMyTextActivity.class);
                intent1.putExtra("text", string);
                startActivityForResult(intent1,100);
                break;
        }
    }

    private void initPops() {
        View inflate1 = LayoutInflater.from(this).inflate(R.layout.layout_popo_person_photo, null);
        TextView tv_photo = inflate1.findViewById(R.id.tv_photo);
        TextView tv_camer = inflate1.findViewById(R.id.tv_camer);
        TextView tv_colse = inflate1.findViewById(R.id.tv_colse);
        popupWindow = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAtLocation(inflate1, Gravity.BOTTOM, 0, 0);
        //进去popupWindow时背景
        final WindowManager.LayoutParams lp = PersonalDataActivity.this.getWindow().getAttributes();
        lp.alpha = 0.7f;//代表透明程度，范围为0 - 1.0f
        PersonalDataActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        PersonalDataActivity.this.getWindow().setAttributes(lp);
        /**
         * 退出popupWindow时取消暗背景
         */
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                PersonalDataActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                PersonalDataActivity.this.getWindow().setAttributes(lp);
            }
        });
        tv_camer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCamer();
            }
        });
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPhoto();
            }
        });
        tv_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }


    private void initCamer() {
        //压缩图片
        mTakephoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(1080).create(), true);
        //从相册获取并裁剪
        mTakephoto.onPickFromCaptureWithCrop(getUri(), getOption());
        popupWindow.dismiss();
    }

    private void initPhoto() {
        mTakephoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(1080).create(), true);
        mTakephoto.onPickFromGalleryWithCrop(getUri(), getOption());
        popupWindow.dismiss();
    }

    private Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/diving/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTakephoto.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            String name = data.getStringExtra("name");
            mEtText.setText(name);
        }
    }

    private CropOptions getOption() {
        return new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
    }

    @Override
    public void takeSuccess(TResult result) {
        final String path = result.getImage().getCompressPath() != null ? result.getImage().getCompressPath() : result.getImage().getOriginalPath();//先设置到列表中
        if (!TextUtils.isEmpty(path)) {
            Glide.with(PersonalDataActivity.this).load(path).into(mIvPhotos);
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
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
                                    MediaType types = MediaType.parse("application/json;charset=UTF-8");
                                    JSONObject jsonObjects = new JSONObject();
                                    try {
                                        jsonObjects.put("icon", data);
                                        jsonObjects.put("username", SharedPrefrenceUtils.getString(PersonalDataActivity.this, NormalConfig.PHONE));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    String str = jsonObjects.toString();
                                    RequestBody body = RequestBody.create(types, str);
                                    mPresenter.getData(ApiConfig.XIUGAIGERENXX, body);


                                } else {
//                                            Toast.makeText(PublishDynamicActivity.this,upLoadBean.getCode(),Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(PersonalDataActivity.this, "错误", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
            });

//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .build();
//            /**
//             * 封装文件上传的  请求体
//             */
//            File file = new File(path);
//            //1.设置文件以及文件上传类型封装
//            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
//
//            //2.文件上传的请求体封装
//            MultipartBody body = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM)//设置文件上传Type类型为multipart/form-data
//                    .addFormDataPart("file", file.getName(), requestBody)//设置文件参数
//                    .build();
//
//            Request request = new Request.Builder()
//                    .url("http://192.168.0.246:8000/authenticationed/member/updateIcon")
//                    .addHeader("Authorization","Bearer " + BaseApplication.getInstance().Token)
//                    .post(body)
//                    .build();
//
//            Call call = okHttpClient.newCall(request);
//
//            call.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    Log.e("onFailure",e.getMessage());
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    String str = response.body().string();
//
//                    Gson gson = new Gson();
//                    final UpLoadBean upLoadBean = gson.fromJson(str, UpLoadBean.class);
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (upLoadBean!=null){
//                                if (upLoadBean.getCode() == 200){
//                                    Glide.with(PersonalDataActivity.this).load(path).into(mIvPhotos);
//                                            showToast("上传成功");
//                                }else {
////                                            Toast.makeText(PublishDynamicActivity.this,upLoadBean.getCode(),Toast.LENGTH_SHORT).show();
//                                }
//                            }else{
//                                Toast.makeText(PersonalDataActivity.this,"错误",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//            });
//            mPresenter.getData(ApiConfig.UPDATEICON,);

        } else {
            showToast("no Path");
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    protected void onPause() {
        super.onPause();
//        mPresenter.getData(ApiConfig.MYPERSONDESTIALS);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        photoList.clear();
//        mPresenter.getData(ApiConfig.MYPERSONDESTIALS);
    }
}
