package com.example.edive.fragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edive.R;
import com.example.edive.activity.OrderDetailsActivity;
import com.example.edive.adapter.RlvIntegralListAdapter;
import com.example.edive.bean.CompletedBean;
import com.example.edive.bean.IntegralListBean;
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
public class FisherFragment extends BaseMvpFragment<ProductModel> {

    @BindView(R.id.sm)
    SmartRefreshLayout mSm;
    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<IntegralListBean.ResultBean.ListBean> list;
    private RlvIntegralListAdapter adapter;
    private int statues =2;
    private int num =1;
    private int size =10;
    private int lastPage;
    private boolean isLastPage;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_fisher;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        initRecycleView(mRec,mSm);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvIntegralListAdapter(getActivity(), list);
        mRec.setAdapter(adapter);
        adapter.setonclickss(new RlvIntegralListAdapter.setonclickss() {
            @Override
            public void setonclickss(int pos) {
                int statuss = list.get(pos).getStatus();
                String orderSn = list.get(pos).getOrderSn();
                int productType = list.get(pos).getProductType();
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                intent.putExtra("order",orderSn);
                intent.putExtra("status",statuss);
                intent.putExtra("productType",productType);
                startActivity(intent);
            }
        });
        adapter.setonclick(new RlvIntegralListAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                String orderSn = list.get(pos).getOrderSn();
                list.remove(pos);
                adapter.notifyDataSetChanged();
                mPresenter.getData(ApiConfig.COMPLETEDORDER,orderSn);
            }
        });
        mSm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                list.clear();

                refreshLayout.finishLoadMore();
                if (isLastPage == true) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else {
                    num++;
                    initData();
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                num = 1;
                list.clear();
                initData();
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();

            }
        });

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.INTEGRALLIST,statues,num,size);
    }

    @Override
    public ProductModel getModel() {
        return new ProductModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showToast(e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.INTEGRALLIST:
//                list.clear();
                IntegralListBean integralListBean = (IntegralListBean) t[0];
                if (integralListBean.getCode() == 200) {
                    List<IntegralListBean.ResultBean.ListBean> listBeanList = integralListBean.getResult().getList();
                    IntegralListBean.ResultBean result = integralListBean.getResult();
                    isLastPage = result.isIsLastPage();
                    lastPage = result.getLastPage();
                    list.addAll(listBeanList);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(integralListBean.getMessage());
                }
                break;
            case ApiConfig.COMPLETEDORDER:
                CompletedBean completedBean = (CompletedBean) t[0];
                if (completedBean.getCode() == 200) {
                    showToast(completedBean.getMessage());
//                    mPresenter.getData(ApiConfig.INTEGRALLIST,statues,num,size);
                }else {
                    showToast(completedBean.getMessage());
                }
                break;
        }
    }
}
