package com.example.edive.fragment.homefragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edive.R;
import com.example.edive.activity.DynamicDetailsActivity;
import com.example.edive.adapter.RlvNewDynamicAdapter;
import com.example.edive.bean.NewDynamicBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.model.HomeModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.utils.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 最新动态
 */
public class NewDynamicFragment extends BaseMvpFragment<HomeModel> {
    @BindView(R.id.new_list)
    RecyclerView newList;
    @BindView(R.id.smrefresh)
    SmartRefreshLayout mSmrefresh;
    private ArrayList<NewDynamicBean.DataBean.ListBean> list = new ArrayList();
    private RlvNewDynamicAdapter adapter;
    int pagenum = 1;
    int pagesize = 15;
    private int lastPage;
    private boolean isLastPage;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_dynamic;
    }

    @Override
    public void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        newList.setLayoutManager(gridLayoutManager);
        adapter = new RlvNewDynamicAdapter(getActivity(), list);
        newList.setAdapter(adapter);
        mSmrefresh.setDisableContentWhenRefresh(true);
        mSmrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                pagenum = 1;
                initData();
                refreshLayout.finishRefresh();
            }
        });
        mSmrefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if (isLastPage == true) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else {
                    pagenum++;
                    initData();
                    refreshLayout.finishLoadMore();
                }
            }
        });
        adapter.setonclick(new RlvNewDynamicAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                Intent intent = new Intent(getActivity(), DynamicDetailsActivity.class);
                int id = list.get(pos).getId();
                int userType = list.get(pos).getUserType();
                intent.putExtra("pos", id);
                intent.putExtra("userType", userType);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.NEWDYNAMIC_DATA, pagenum, pagesize);

    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("new------", e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.NEWDYNAMIC_DATA:
//                list.clear();
                NewDynamicBean bean = (NewDynamicBean) t[0];
                NewDynamicBean.DataBean data = bean.getData();
                lastPage = data.getLastPage();
                isLastPage = data.isIsLastPage();
                List<NewDynamicBean.DataBean.ListBean> results = bean.getData().getList();
                list.addAll(results);
                adapter.setData(list);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mSmrefresh.autoRefresh();
    }
}