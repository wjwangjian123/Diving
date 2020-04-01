package com.example.edive.activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

public class CoachApplicationActivity extends BaseMvpActivity<HomeModel> {
//    @BindView(R.id.et_name)
//    EditText mEtName;
//    @BindView(R.id.et_phone)
//    EditText mEtPhone;
//    @BindView(R.id.bt_sub)
//    Button mBtSub;
//    @BindView(R.id.iv_userid_front)
//    ImageView mIvUseridFront;
//    @BindView(R.id.iv_userid_black)
//    ImageView mIvUseridBlack;
//    @BindView(R.id.iv_Qualification_front)
//    ImageView mIvQualificationFront;
//    @BindView(R.id.iv_Qualification_black)
//    ImageView mIvQualificationBlack;
//    private TakePhotoImpl mTakephoto;
//    private PopupWindow popupWindow;
//    private ImageView imv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        return R.layout.activity_coach_application;
    }

    @Override
    public void initView() {
//        mTakephoto = new TakePhotoImpl(this, this);
//        getPermission();
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

//    @OnClick({R.id.bt_sub, R.id.iv_userid_front, R.id.iv_userid_black, R.id.iv_Qualification_front, R.id.iv_Qualification_black})
//    public void onClick(View v) {
//        switch (v.getId()) {
//            default:
//                break;
//            case R.id.bt_sub:
//                break;
//            case R.id.iv_userid_front:
//                imv = findViewById(R.id.iv_userid_front);
//                initpopo();
//                break;
//            case R.id.iv_userid_black:
//                imv = findViewById(R.id.iv_userid_black);
//                initpopo();
//                break;
//            case R.id.iv_Qualification_front:
//                imv = findViewById(R.id.iv_Qualification_front);
//                initpopo();
//                break;
//            case R.id.iv_Qualification_black:
//                imv = findViewById(R.id.iv_Qualification_black);
//                initpopo();
//                break;
//        }
//    }

//    private void initpopo() {
//        View inflate1 = LayoutInflater.from(this).inflate(R.layout.layout_popo_coach, null);
//        TextView tv_photo = inflate1.findViewById(R.id.tv_photo);
//        TextView tv_camer = inflate1.findViewById(R.id.tv_camer);
//        popupWindow = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWindow.setBackgroundDrawable(new ColorDrawable());
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.showAtLocation(inflate1, Gravity.BOTTOM, 0, 0);
//        //进去popupWindow时背景
//        final WindowManager.LayoutParams lp = CoachApplicationActivity.this.getWindow().getAttributes();
//        lp.alpha = 0.7f;//代表透明程度，范围为0 - 1.0f
//        CoachApplicationActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        CoachApplicationActivity.this.getWindow().setAttributes(lp);
//        /**
//         * 退出popupWindow时取消暗背景
//         */
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                lp.alpha = 1.0f;
//                CoachApplicationActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                CoachApplicationActivity.this.getWindow().setAttributes(lp);
//            }
//        });
//        tv_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initPhoto();
//            }
//        });
//        tv_camer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initCamer();
//            }
//        });
//    }
//
//    private void initCamer() {
//        //压缩图片
//        mTakephoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(1080).create(), true);
//        //从相册获取并裁剪
//        mTakephoto.onPickFromCaptureWithCrop(getUri(), getOption());
//        popupWindow.dismiss();
//    }
//
//    private void initPhoto() {
//        mTakephoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(50 * 1024).setMaxPixel(1080).create(), true);
//        mTakephoto.onPickFromGalleryWithCrop(getUri(), getOption());
//        popupWindow.dismiss();
//    }
//
//    @Override
//    public void takeSuccess(TResult result) {
//        String path = result.getImage().getCompressPath() != null ? result.getImage().getCompressPath() : result.getImage().getOriginalPath();//先设置到列表中
//        if (!TextUtils.isEmpty(path)) {
//            Glide.with(this).load(path).transform(new CenterCrop(),new GlideRoundTransform(this,4)).into(imv);
//        }else {
//            showToast("no Path");
//        }
//    }
//
//    @Override
//    public void takeFail(TResult result, String msg) {
//
//    }
//
//    @Override
//    public void takeCancel() {
//
//    }
//    private Uri getUri() {
//        File file = new File(Environment.getExternalStorageDirectory(), "/diving/" + System.currentTimeMillis() + ".jpg");
//        if (!file.getParentFile().exists()) {
//            file.getParentFile().mkdirs();
//        }
//        Uri imageUri = Uri.fromFile(file);
//        return imageUri;
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        mTakephoto.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//    private CropOptions getOption() {
//        return new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
//    }
}
