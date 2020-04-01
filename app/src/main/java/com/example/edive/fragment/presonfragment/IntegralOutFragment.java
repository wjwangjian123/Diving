package com.example.edive.fragment.presonfragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edive.R;
import com.example.edive.activity.OrderDetailsActivity;
import com.example.edive.adapter.RlvMyOrderAdapter;
import com.example.edive.bean.OrderBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.model.ProductModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntegralOutFragment extends BaseMvpFragment<ProductModel> {


    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.sm)
    SmartRefreshLayout mSm;
    private ArrayList<OrderBean.ResultBean.ListBean> list;
    private RlvMyOrderAdapter adapter;
    private int status = 0;
    private int num = 1;
    private int size = 10;
    private int lastPage;
    private boolean isLastPage;

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.getData(ApiConfig.ORDER_FRAGMENT,status,num,size);
        }
    }

    //待付款
    @Override
    public int getLayoutId() {
        return R.layout.fragment_integral_out;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvMyOrderAdapter(getActivity(), list);
        mRec.setAdapter(adapter);

        adapter.setonclick(new RlvMyOrderAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                String orderSn = list.get(pos).getOrderSn();
                int statuss = list.get(pos).getStatus();
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                intent.putExtra("order",orderSn);
                intent.putExtra("status",statuss);
                startActivity(intent);
            }
        });
        mSm.setDisableContentWhenRefresh(true);
        mSm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                list.clear();

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
                num = 1;
                initData();
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ORDER_FRAGMENT,status,num,size);
    }

    @Override
    public ProductModel getModel() {
        return new ProductModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.ORDER_FRAGMENT:
//                list.clear();
                OrderBean bean = (OrderBean) t[0];
                OrderBean.ResultBean result = bean.getResult();
                lastPage = result.getLastPage();
                isLastPage = result.isIsLastPage();
                List<OrderBean.ResultBean.ListBean> rsts = bean.getResult().getList();
                list.addAll(rsts);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.getData(ApiConfig.ORDER_FRAGMENT,status,num,size);
    }
}
