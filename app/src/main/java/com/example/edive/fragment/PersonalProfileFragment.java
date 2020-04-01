package com.example.edive.fragment;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.bean.UserDatilasBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.model.PersonModel;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PersonalProfileFragment extends BaseMvpFragment<PersonModel> {


    @BindView(R.id.tv_text)
    TextView mTvText;
    private int i;
    private int id;
    private int userType;
    private final int favoriteType;

    @SuppressLint("ValidFragment")
    public PersonalProfileFragment(int id, int userType, int favoriteType) {

        this.id = id;
        this.userType = userType;
        this.favoriteType = favoriteType;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_profile;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.USERDATALIS,id,userType);
    }

    @Override
    public PersonModel getModel() {
        return new PersonModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showToast(e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.USERDATALIS:
                UserDatilasBean userDatilasBean = (UserDatilasBean) t[0];
                if (userDatilasBean.getCode() == 200) {
                    UserDatilasBean.DataBean data = userDatilasBean.getData();
                    String personalizedSignature = data.getPersonalizedSignature();
                    if (!TextUtils.isEmpty(personalizedSignature)) {
                        mTvText.setText(personalizedSignature);
                    }else {
                        mTvText.setText("当前用户暂无个人简介");

                    }
                }else {
                    showToast(userDatilasBean.getMessage());
                }
                break;
        }
    }
}
