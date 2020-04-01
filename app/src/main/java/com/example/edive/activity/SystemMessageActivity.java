package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.RlvSystemAdapter;
import com.example.edive.bean.LikeListBean;
import com.example.edive.bean.YiduBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.PersonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SystemMessageActivity extends BaseMvpActivity<PersonModel> {

    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.smrefresh)
    SmartRefreshLayout mSmart;
    private ArrayList<LikeListBean.ResultBean.ListBean> list;
    private RlvSystemAdapter adapter;
    private int type = 1;
    private int num = 1;
    private int size = 10;
    private boolean isLastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.findpassword_bg));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_system_message;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SystemMessageActivity.this);
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvSystemAdapter(SystemMessageActivity.this, list);
        mRec.setAdapter(adapter);
        mSmart.setDisableContentWhenRefresh(true);
        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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
                    num = 1;
                    initData();
                    refreshLayout.finishRefresh();
            }
        });
        adapter.setonclick(new RlvSystemAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int noticeId = list.get(pos).getNoticeId();
                mPresenter.getData(ApiConfig.BIAOJIYIDU,noticeId);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.LIKELIST,num,size,type);
    }

    @Override
    public PersonModel getModel() {
        return new PersonModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.LIKELIST:
//                list.clear();
                LikeListBean likeListBean = (LikeListBean) t[0];
                if (likeListBean.getCode() == 200) {
                    List<LikeListBean.ResultBean.ListBean> listBeanList = likeListBean.getResult().getList();
                    isLastPage = likeListBean.getResult().isIsLastPage();
                    list.addAll(listBeanList);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(likeListBean.getMessage());
                }
                break;
            case ApiConfig.BIAOJIYIDU:
                YiduBean yiduBean = (YiduBean) t[0];
                if (yiduBean.getCode() == 200) {
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(yiduBean.getMessage());
                }
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
//        list.clear();
//        mPresenter.getData(ApiConfig.LIKELIST,num,size,type);
        mSmart.autoRefresh();
    }
}
