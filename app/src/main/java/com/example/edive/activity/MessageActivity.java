package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.bean.ReadBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.PersonModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseMvpActivity<PersonModel> {

    @BindView(R.id.tv_dianzan)
    TextView mTvDianzan;
    @BindView(R.id.tv_pinglun)
    TextView mTvPinglun;
    @BindView(R.id.tv_system)
    TextView mTvSystem;
    @BindView(R.id.yuan_pl)
    TextView mYuanPl;
    @BindView(R.id.yuan_like)
    TextView mYuanLike;
    @BindView(R.id.yuan_system)
    TextView mYuanSystem;
    //    @BindView(R.id.fl)
//    FlowLayout mFl;
    private ArrayList<Fragment> fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.findpassword_bg));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
//        fs = new ArrayList<>();
//        fs.add(new LikeFragment());
//        fs.add(new CommentFragment());
//        fs.add(new SystemFragment());

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.CHAXUNSHIF);
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
            case ApiConfig.CHAXUNSHIF:
                ReadBean readBean = (ReadBean) t[0];
                if (readBean.getCode() == 200) {
                    List<ReadBean.ResultBean> result = readBean.getResult();
                    int messageType = result.get(2).getMessageType();
                    int isRead = result.get(2).getIsRead();
                    if (result.get(0).getMessageType() == 1 && result.get(0).getIsRead() > 0) {
                        mYuanLike.setVisibility(View.VISIBLE);
                    }
                    if (result.get(1).getMessageType() == 2 && result.get(1).getIsRead() > 0) {
                        mYuanPl.setVisibility(View.VISIBLE);
                    }
                    if (messageType == 3 ) {
                        if (isRead > 0) {
                            mYuanSystem.setVisibility(View.VISIBLE);
                        }
                    }
                    if (result.get(0).getIsRead() == 0) {
                        mYuanLike.setVisibility(View.GONE);
                    } else if (result.get(1).getIsRead() == 0) {
                        mYuanPl.setVisibility(View.GONE);
                    } else if (result.get(2).getIsRead() == 0) {
                        mYuanSystem.setVisibility(View.GONE);
                    }
                }else {
                    showToast(readBean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.tv_dianzan, R.id.tv_pinglun, R.id.tv_system})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_dianzan:
                startActivity(new Intent(MessageActivity.this, LikeActivity.class));
                break;
            case R.id.tv_pinglun:
                startActivity(new Intent(MessageActivity.this, CommentActivity.class));
                break;
            case R.id.tv_system:
                startActivity(new Intent(MessageActivity.this, SystemMessageActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getData(ApiConfig.CHAXUNSHIF);
    }
}
