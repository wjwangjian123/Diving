package com.example.edive.fragment.presonfragment;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edive.R;
import com.example.edive.activity.OrderDetailsActivity;
import com.example.edive.adapter.RlvMyOrderAdapter;
import com.example.edive.bean.CompletedBean;
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
public class PendingPaymentFragment extends BaseMvpFragment<ProductModel> {


    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.sm)
    SmartRefreshLayout mSm;
    private ArrayList<OrderBean.ResultBean.ListBean> list;
    private RlvMyOrderAdapter adapter;
    private int status = 2;
    private int num = 1;
    private int size = 10;
    private int lastPage;
    private boolean isLastPage;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pending_payment;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvMyOrderAdapter(getActivity(), list);
        mRec.setAdapter(adapter);
        mSm.setDisableContentWhenRefresh(true);
        adapter.setonclick(new RlvMyOrderAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int statuss = list.get(pos).getStatus();
                String orderSn = list.get(pos).getOrderSn();
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                intent.putExtra("order",orderSn);
                intent.putExtra("status",statuss);
                startActivity(intent);
            }
        });
        adapter.setontextclick(new RlvMyOrderAdapter.setontextclick() {
            @Override
            public void setontextclick(int pos) {
                String orderSn = list.get(pos).getOrderSn();
                mPresenter.getData(ApiConfig.COMPLETEDORDER,orderSn);
            }
        });
        mSm.setDisableContentWhenRefresh(true);
        mSm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isLastPage == true) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else {
                    num++;
                    initData();
                    showLoadingDialog();
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                num = 1;
                initData();
                showLoadingDialog();
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ORDER_FRAGMENT,status,num,size);
        showLoadingDialog();
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
        hideLoadingDialog();
        switch (whichApi){
            case ApiConfig.ORDER_FRAGMENT:
                OrderBean bean = (OrderBean) t[0];
                if (bean.getCode() == 200) {
                    OrderBean.ResultBean result = bean.getResult();
                    isLastPage = result.isIsLastPage();
                    lastPage = result.getLastPage();
                    List<OrderBean.ResultBean.ListBean> rsts = bean.getResult().getList();
                    list.addAll(rsts);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(bean.getCode()+":"+bean.getMessage());
                }

                break;
            case ApiConfig.COMPLETEDORDER:
                CompletedBean completedBean = (CompletedBean) t[0];
                if (completedBean.getCode() == 200) {
                    showToast(completedBean.getMessage());
                    mPresenter.getData(ApiConfig.ORDER_FRAGMENT,status,num,size);
                }else {
                    showToast(completedBean.getMessage());
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mSm.autoRefresh();//自动刷新
    }
}
