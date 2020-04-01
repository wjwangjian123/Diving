package com.example.edive.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edive.R;
import com.example.edive.adapter.QuestionFeedbackAdapter;
import com.example.edive.bean.AddDynamicBean;
import com.example.edive.bean.UpLoadBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseApplication;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.Bimp;
import com.example.edive.local_utils.FileUtils;
import com.example.edive.local_utils.MediaLoader;
import com.example.edive.local_utils.SharedPrefrenceUtils;
import com.example.edive.local_utils.StringUtils;
import com.example.edive.model.HomeModel;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PublishDynamicActivity extends BaseMvpActivity<HomeModel> implements TakePhoto.TakeResultListener {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.bt_ok)
    Button mBtOk;
    @BindView(R.id.et_text)
    EditText mEtText;
    @BindView(R.id.tv_conversation)
    TextView mTvConversation;
    @BindView(R.id.tv_location)
    TextView mTvLocation;

    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.tv_top)
    TextView mTop;
    private ArrayList<String> list;
    private TakePhotoImpl mTakephoto;
    private PopupWindow popupWindow;
    private QuestionFeedbackAdapter adapter;
    private String path = "";
    private static final int TAKE_PICTURE = 0x000000;
    private List<String> photoList;
    String name = "不显示位置";
    String topicname = "话题";
    private String data;
    private ArrayList<String> arrayList;
    private ArrayList<String> PicList;
    private int topicid;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.findpassword_bg));//设置颜色
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_publish_dynamic;
    }
    private void initGess() {
        if (ContextCompat.checkSelfPermission(PublishDynamicActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(PublishDynamicActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            //开始定位
            Toast.makeText(PublishDynamicActivity.this, "已开启定位权限", Toast.LENGTH_LONG).show();
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
    public void initView() {
        initGess();
        arrayList = new ArrayList<>();
        PicList = new ArrayList<>();
        Album.initialize(AlbumConfig.newBuilder(PublishDynamicActivity.this).setAlbumLoader(new MediaLoader()).build());
//        topicname = getIntent().getStringExtra("topicname");
//        name = getIntent().getStringExtra("name");

        mTakephoto = new TakePhotoImpl(this, this);
        getPermission();
        list = new ArrayList<>();
//        mRec.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        //初始化适配器
//        adapter = new GridAdapter(this);
//        //更新数据
//        adapter.update();
//        //绑定图片数据
//        mRec.setAdapter(adapter);
//        //绑定点击事件
//        mRec.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
//            {
//                //判断点击的是否是图片
//                if (arg2 == Bimp.bmp.size())
//                {
//                    //显示选择提示窗
//                    new PopupWindows(PublishDynamicActivity.this, mRec);
//                }
//                else
//                {
//                    //进入图片预览页面
//                    Intent intent = new Intent(PublishDynamicActivity.this, PhotoActivity.class);
//                    //传递图片标识
//                    intent.putExtra("ID", arg2);
//                    startActivity(intent);
//                }
//            }
//        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(PublishDynamicActivity.this, 3);
        mRec.setLayoutManager(gridLayoutManager);
        adapter = new QuestionFeedbackAdapter(PublishDynamicActivity.this, list);
        mRec.setAdapter(adapter);
        adapter.setonclick(new QuestionFeedbackAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                list.remove(pos);
                PicList.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setOnItemClickListener(new QuestionFeedbackAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                initAlbum();
            }
        });

    }

    private void initAlbum() {
        Album.image(PublishDynamicActivity.this)
                .multipleChoice()
                .camera(true)
                .columnCount(3)
                .selectCount(9)
                .onResult(new Action<ArrayList<AlbumFile>>() {
                    @Override
                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                        for (int i = 0; i < result.size(); i++) {
                            String path = result.get(i).getPath();
                            list.add(path);
                        }
                        adapter.notifyDataSetChanged();
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

    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("sadasdadasdsa",e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.ADDDYNAMIC:
                AddDynamicBean upLoadBean = (AddDynamicBean) t[0];
                if (upLoadBean.getCode() == 200) {
                    showToast(upLoadBean.getMessage());
//                    startActivity(new Intent(PublishDynamicActivity.this,HomeActivity.class));
//                    this.finish();
                    finish();
                }else {
                    showToast(upLoadBean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.iv_back, R.id.bt_ok, R.id.tv_conversation, R.id.tv_location})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                //返回
                finish();
                break;
            case R.id.bt_ok:
                //发布
                String text = mEtText.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    initupload();
                }else {
                    showToast("请输入内容");
                }
                break;
            case R.id.tv_conversation:
                //话题
                Intent intent = new Intent(PublishDynamicActivity.this, ChooesConversationActivity.class);
                startActivityForResult(intent,100);
//                finish();
                break;
            case R.id.tv_location:
                //定位
                Intent intent1 = new Intent(PublishDynamicActivity.this, LocationActivity.class);
                startActivityForResult(intent1,100);
//                finish();
                break;
        }
    }

    private void initupload() {
        String join = StringUtils.join(PicList, ",");
        String nickname = mApplication.nickname;
        String content = mEtText.getText().toString();
        String userid = mApplication.userid;
        location = mTvLocation.getText().toString();
        if (location.equals("添加地点")) {
            location = "";
        }
        String topicids = SharedPrefrenceUtils.getString(PublishDynamicActivity.this, "topicid");
        if (!TextUtils.isEmpty(topicids)) {
            topicid = Integer.valueOf(topicids);
        }
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("dynamicAddress", location);
            jsonObject.put("dynamicAuthor", nickname);
            jsonObject.put("dynamicContent", content);
            jsonObject.put("dynamicPicture", join);
            jsonObject.put("topicId", topicid);
            jsonObject.put("userId", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jsonObject.toString();
        RequestBody bodys = RequestBody.create(type, string);
        mPresenter.getData(ApiConfig.ADDDYNAMIC,bodys);
    }

    private void initApply() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        for (int i = 0; i < list.size(); i++) {

            /**
             * 封装文件上传的  请求体
             */
            File file = new File(list.get(i));
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
//                                            String[] split = data.split(",");
//                                            for (int j = 0; j < split.length; j++) {
//                                                arrayList.add(split[j]);
//                                            }
                                    PicList.add(data);
                                    if (PicList.size() == list.size()) {
//                                                String datas = ArrayListToString(PicList);
//                                        String join = StringUtils.join(PicList, ",");
//                                        String nickname = mApplication.nickname;
//                                        String content = mEtText.getText().toString();
//                                        String userid = mApplication.userid;
//                                        location = mTvLocation.getText().toString();
//                                        if (location.equals("添加点的")) {
//                                               location = "";
//                                        }
//                                        String topicids = SharedPrefrenceUtils.getString(PublishDynamicActivity.this, "topicid");
//                                        topicid = Integer.valueOf(topicids);
//                                        MediaType type = MediaType.parse("application/json;charset=UTF-8");
//                                        JSONObject jsonObject = new JSONObject();
//                                        try {
//                                            jsonObject.put("dynamicAddress", location);
//                                            jsonObject.put("dynamicAuthor", nickname);
//                                            jsonObject.put("dynamicContent", content);
//                                            jsonObject.put("dynamicPicture", join);
//                                            jsonObject.put("topicId", topicid);
//                                            jsonObject.put("userId", userid);
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                        String string = jsonObject.toString();
//                                        RequestBody bodys = RequestBody.create(type, string);
//                         mPresenter.getData(ApiConfig.ADDDYNAMIC,bodys);
//                                                finish();
//                                                FileUtils.deleteDir();//删除保存内容
                                    }
                                }else {
//                                            Toast.makeText(PublishDynamicActivity.this,upLoadBean.getCode(),Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(PublishDynamicActivity.this,"错误",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            String topicname = data.getStringExtra("topicname");
            topicid = data.getExtras().getInt("topicid");
            String lo = data.getStringExtra("lo");
            if (!TextUtils.isEmpty(topicname)) {
                mTop.setText("#"+topicname);
            }
            if (!TextUtils.isEmpty(lo)) {
                mTvLocation.setText(lo);
            }
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        String compressPath = result.getImage().getCompressPath();
        photoList.add(compressPath);
        Log.e("ijninijnjiijinj",compressPath.toString());
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }
    private Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/diving/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }

    private CropOptions getOption() {
        return new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPrefrenceUtils.getString(PublishDynamicActivity.this, "topicid","");
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

    /**
     * 质量压缩方法
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
    public static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imgBytes = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
