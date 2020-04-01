package com.example.edive.fragment.presonfragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edive.R;
import com.example.edive.activity.OrderDetailsActivity;
import com.example.edive.adapter.RlvIntregralFishAdapter;
import com.example.edive.bean.Integralfishbean;
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
public class CompletedFragment extends BaseMvpFragment<ProductModel> {


    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.sm)
    SmartRefreshLayout mSm;
    private ArrayList<Integralfishbean.ResultBean.ListBean> list = new ArrayList<>();
    private RlvIntregralFishAdapter adapter;
    private int stauas = 3;
    private int num = 1;
    private int size =10;
    private int lastPage;
    private boolean isLastPage;

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (getUserVisibleHint()) {
//
//        }else {
//            list.clear();
//        }
//    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_completed;
    }

    @Override
    public void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvIntregralFishAdapter(getActivity(), list);
        mRec.setAdapter(adapter);
        adapter.setonclick(new RlvIntregralFishAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
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
        mPresenter.getData(ApiConfig.INTEGRALFISHLIST,stauas,num,size);
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
            case ApiConfig.INTEGRALFISHLIST:
//                list.clear();
                Integralfishbean integralfishbean = (Integralfishbean) t[0];
                if (integralfishbean.getCode() == 200) {
                    Integralfishbean.ResultBean result = integralfishbean.getResult();
                    lastPage = result.getLastPage();
                    isLastPage = result.isIsLastPage();
                    List<Integralfishbean.ResultBean.ListBean> beanList = integralfishbean.getResult().getList();
                    list.addAll(beanList);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(integralfishbean.getMessage());
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.getData(ApiConfig.INTEGRALFISHLIST,stauas,num,size);
    }
}
