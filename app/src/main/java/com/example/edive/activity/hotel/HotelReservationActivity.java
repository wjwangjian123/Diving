package com.example.edive.activity.hotel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.adapter.hotel.HoteloneAdapter;
import com.example.edive.adapter.hotel.HoteltowAdapter;
import com.example.edive.frame.BaseMvpActivity;
import com.lljjcoder.widget.RecycleViewDividerForList;

import butterknife.BindView;

/**
 * 酒店预订
 */
public class HotelReservationActivity extends BaseMvpActivity {
    @BindView(R.id.edit_search)
    EditText mEdit_Search;
    @BindView(R.id.lin_home)
    LinearLayout mLin_Home;
    @BindView(R.id.tv_home)
    TextView mTv_Home;
    @BindView(R.id.lin_select_data)
    LinearLayout mLin_Selectdata;
    @BindView(R.id.tv_data)
    TextView mTv_Data;
    @BindView(R.id.button_search)
    Button mButton_Search;
    @BindView(R.id.recycler_list1)
    RecyclerView mRecycler_List1;
    @BindView(R.id.recycler_list2)
    RecyclerView mRecycler_List2;
    private LinearLayoutManager linearLayoutManager1;
    private LinearLayoutManager linearLayoutManager2;
    private HoteloneAdapter hoteloneAdapter;
    private HoteltowAdapter hoteltowAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hotel_reservation;
    }

    @Override
    public void initView() {
        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager2 = new LinearLayoutManager(this);
        mRecycler_List1.setLayoutManager(linearLayoutManager1);
        mRecycler_List1.addItemDecoration(new RecycleViewDividerForList(
                this, LinearLayoutManager.HORIZONTAL, 10, getResources().getColor(R.color.white)));
        hoteloneAdapter = new HoteloneAdapter(this);
        mRecycler_List1.setAdapter(hoteloneAdapter);
        mRecycler_List2.setLayoutManager(linearLayoutManager2);
        hoteltowAdapter = new HoteltowAdapter(this);
        mRecycler_List2.setAdapter(hoteltowAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public Object getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
