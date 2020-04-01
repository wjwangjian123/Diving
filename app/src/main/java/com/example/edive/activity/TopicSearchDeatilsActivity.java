package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.RlvTopicSearchDeatilsAdapter;
import com.example.edive.bean.SearchDynamicBean;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;

import java.util.ArrayList;

import butterknife.BindView;

public class TopicSearchDeatilsActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<SearchDynamicBean.DataBean> list;
    private RlvTopicSearchDeatilsAdapter adapter;

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
        return R.layout.activity_topic_search_deatils;
    }

    @Override
    public void initView() {
//        list = (ArrayList<SearchDynamicBean.DataBean>) getIntent().getSerializableExtra("list");
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TopicSearchDeatilsActivity.this);
//        mRec.setLayoutManager(linearLayoutManager);
//        adapter = new RlvTopicSearchDeatilsAdapter(TopicSearchDeatilsActivity.this, list);
//        mRec.setAdapter(adapter);
//        adapter.setonclick(new RlvTopicSearchDeatilsAdapter.setonclick() {
//            @Override
//            public void setonclick(int pos) {
//                int id = list.get(pos).getId();
//                Intent intent = new Intent(TopicSearchDeatilsActivity.this, TopicDetailsActivity.class);
//                intent.putExtra("id",id);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void initData() {

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

    }
}
