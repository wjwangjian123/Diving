package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.RlvShoppingTripAdapter;
import com.example.edive.bean.DivingBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DivingTripActivity extends BaseMvpActivity<HomeModel> {
    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.smrefresh)
    SmartRefreshLayout mSmrefresh;

    private ArrayList<DivingBean.ResultBean.ListBean> list;
    private int num = 1;
    private int size = 10;
    private RlvShoppingTripAdapter adapters;
    private boolean isLastPage;

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
        return R.layout.activity_diving_trip;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(DivingTripActivity.this);
        mRec.setLayoutManager(linearLayoutManagers);
        adapters = new RlvShoppingTripAdapter(DivingTripActivity.this, list);
        mRec.setAdapter(adapters);
        mSmrefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isLastPage == true) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else {
                    num++;
                    initData();
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    list.clear();
                    initData();
                    refreshLayout.finishRefresh();
            }
        });
        adapters.setonclcik(new RlvShoppingTripAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int id = list.get(pos).getId();
                Intent intent = new Intent(DivingTripActivity.this, DivingDestiActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        MediaType types = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObjects = new JSONObject();
        try {
            jsonObjects.put("pageNum", num);
            jsonObjects.put("pageSize", size);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String strings = jsonObjects.toString();
        RequestBody bodys = RequestBody.create(types, strings);
        mPresenter.getData(ApiConfig.SHOPPING_DIVING, bodys);
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
        switch (whichApi) {
            case ApiConfig.SHOPPING_DIVING:
                DivingBean ha = (DivingBean) t[0];
                if (ha.getCode() == 200) {
                    isLastPage = ha.getResult().isIsLastPage();
                    List<DivingBean.ResultBean.ListBean> hahben = ha.getResult().getList();
                    list.addAll(hahben);
                    adapters.notifyDataSetChanged();
                }else {
                    showToast(ha.getMessage());
                }
                break;
        }
    }
}
