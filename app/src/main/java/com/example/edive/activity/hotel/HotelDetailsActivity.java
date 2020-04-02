package com.example.edive.activity.hotel;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;

public class HotelDetailsActivity extends BaseMvpActivity<HomeModel> {



    @Override
    public int getLayoutId() {
        return R.layout.activity_hotel_details;
    }

    @Override
    public void initView() {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
            {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            }
    }

    @Override
    public void initData() {

    }

    @Override
    public HomeModel getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
