package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.RlvChooesConversationAdapter;
import com.example.edive.bean.ConversationBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.SharedPrefrenceUtils;
import com.example.edive.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChooesConversationActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<ConversationBean.DataBean.ListBean> list;
    private RlvChooesConversationAdapter adapter;
    int num = 1 ;
    int size = 10 ;

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
        return R.layout.activity_chooes_conversation;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChooesConversationActivity.this);
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvChooesConversationAdapter(ChooesConversationActivity.this, list);
        mRec.setAdapter(adapter);
        adapter.setonclick(new RlvChooesConversationAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                String topicName = list.get(pos).getTopicName();
//                Intent intent = new Intent(ChooesConversationActivity.this, PublishDynamicActivity.class);
//                intent.putExtra("topicname",topicName);
//                startActivity(intent);
                int id = list.get(pos).getId();
                Intent intent = new Intent();
                intent.putExtra("topicname",list.get(pos).getTopicName());
                intent.putExtra("topicid",id);
                setResult(200,intent);
//                SharedPrefrenceUtils.saveString(ChooesConversationActivity.this,"topicname",topicName);
                SharedPrefrenceUtils.saveString(ChooesConversationActivity.this,"topicid",String.valueOf(id));
                finish();
            }
        });
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.CONVERSATION_DATA,num,size);
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
            case ApiConfig.CONVERSATION_DATA:
                ConversationBean bean = (ConversationBean) t[0];
                List<ConversationBean.DataBean.ListBean> beans = bean.getData().getList();
                list.addAll(beans);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
