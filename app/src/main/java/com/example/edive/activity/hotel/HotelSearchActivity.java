package com.example.edive.activity.hotel;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.hotel.HotelSeacherAdapter;
import com.example.edive.design.CommonTitle;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;

import butterknife.BindView;

public class HotelSearchActivity extends BaseMvpActivity<HomeModel> {
    @BindView(R.id.title)
    CommonTitle  mCommon_title;
    @BindView(R.id.recycler_seacher)
    RecyclerView mRecycler_Seacher;
    private RecyclerView.LayoutManager layoutManager;
    private HotelSeacherAdapter hotelSeacherAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_hotel_search;
    }

    @Override
    public void initView() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        mCommon_title.setBackgroundColor(getResources().getColor(R.color.white));
        layoutManager = new GridLayoutManager(this,2);
        hotelSeacherAdapter = new HotelSeacherAdapter(this);
        mRecycler_Seacher.setLayoutManager(layoutManager);
        mRecycler_Seacher.setAdapter(hotelSeacherAdapter);
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
