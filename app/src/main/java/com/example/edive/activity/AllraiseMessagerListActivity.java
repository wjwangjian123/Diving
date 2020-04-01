package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.RlvAllraiseMessagerAdapter;
import com.example.edive.bean.AllMessagerBean;
import com.example.edive.design.CommonTitle;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AllraiseMessagerListActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.title)
    CommonTitle mTitle;
    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<AllMessagerBean.ResultBean.ListBean> list;
    private RlvAllraiseMessagerAdapter adapter;
    private int pageNum = 1;
    private int pageSize = 10;
    private int total;
    private int id;

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
        return R.layout.activity_allraise_messager_list;
    }

    @Override
    public void initView() {
        id = getIntent().getExtras().getInt("id");
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllraiseMessagerListActivity.this);
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvAllraiseMessagerAdapter(AllraiseMessagerListActivity.this, list);
        mRec.setAdapter(adapter);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.ALLRAISEMESSAGER,id,pageNum,pageSize);
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
        switch (whichApi){
            case ApiConfig.ALLRAISEMESSAGER:
                AllMessagerBean allMessagerBean = (AllMessagerBean) t[0];
                if (allMessagerBean.getCode() == 200) {
                    AllMessagerBean.ResultBean result = allMessagerBean.getResult();
                    total = result.getTotal();
                    mTitle.setTitle("全部评价"+"("+total+")");
                    List<AllMessagerBean.ResultBean.ListBean> listBeanList = allMessagerBean.getResult().getList();
                    list.addAll(listBeanList);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(allMessagerBean.getMessage());
                }
                break;
        }
    }
}
