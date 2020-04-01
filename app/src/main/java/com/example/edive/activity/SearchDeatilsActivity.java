package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.SearchDynamicAdapter;
import com.example.edive.bean.FollowBean;
import com.example.edive.bean.LikeBean;
import com.example.edive.bean.NotFollowBean;
import com.example.edive.bean.SearchBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;

import java.util.ArrayList;

import butterknife.BindView;

public class SearchDeatilsActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<SearchBean.DataBean.ListBean> list;
    private String name;
    private SearchDynamicAdapter adapter;
    private boolean like = false;

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
        return R.layout.activity_search_deatils;
    }

    @Override
    public void initView() {
        list = getIntent().getParcelableArrayListExtra("list");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchDeatilsActivity.this);
        mRec.setLayoutManager(linearLayoutManager);
//        adapter = new SearchDynamicAdapter(SearchDeatilsActivity.this, list);
//        mRec.setAdapter(adapter);
//        adapter.setonclick(new SearchDynamicAdapter.setonclick() {
//            @Override
//            public void setonclick(int pos) {
//                Intent intent = new Intent(SearchDeatilsActivity.this, DynamicDetailsActivity.class);
//                int id = list.get(pos).getId();
//                intent.putExtra("pos",id);
//                startActivity(intent);
//            }
//        });
//        adapter.setlikeonclick(new SearchDynamicAdapter.setlikeonclick() {
//            @Override
//            public void setlikeonclick(int pos, View view) {
//                mPresenter.getData(ApiConfig.NEWSLIKE,pos);
//            }
//        });
//        adapter.setnolikeonclick(new SearchDynamicAdapter.setnolikeonclick() {
//            @Override
//            public void setnolikeonclick(int pos, View view) {
//                mPresenter.getData(ApiConfig.NOTLIKE,pos);
//            }
//        });
//        adapter.setfollowonclick(new SearchDynamicAdapter.setfollowonclick() {
//            @Override
//            public void setfollowonclick(int targetid, int pos) {
//                int userId = list.get(pos).getUserId();
//                MediaType type = MediaType.parse("application/json;charset=UTF-8");
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("favoriteType", 1);
//                    jsonObject.put("status", 1);
//                    jsonObject.put("targetId", userId);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                String string = jsonObject.toString();
//                RequestBody body = RequestBody.create(type, string);
//                mPresenter.getData(ApiConfig.FOLLOWUSER,body);
//            }
//        });
//
//        adapter.setnotfollowonclick(new SearchDynamicAdapter.setnotfollowonclick() {
//            @Override
//            public void setnotfollowonclick(int targetid, int pos) {
//                int userId = list.get(pos).getUserId();
//                MediaType type = MediaType.parse("application/json;charset=UTF-8");
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("favoriteType", 1);
//                    jsonObject.put("status", 2);
//                    jsonObject.put("targetId", userId);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                String string = jsonObject.toString();
//                RequestBody body = RequestBody.create(type, string);
//                mPresenter.getData(ApiConfig.NOTFOLLOWS,body);
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
        switch (whichApi){
            case ApiConfig.NEWSLIKE:
                LikeBean likeBean = (LikeBean) t[0];
                if (likeBean.getCode() == 200) {
                    showToast(likeBean.getMessage());

                }
                break;
            case ApiConfig.NOTLIKE:
                LikeBean been = (LikeBean) t[0];
                if (been.getCode() == 200) {
                    showToast(been.getMessage());
                }
                break;
            case ApiConfig.FOLLOWUSER:
                FollowBean followBean = (FollowBean) t[0];
                if (followBean.getCode() == 200) {
                    showToast("关注成功");
                } else if (followBean.getCode() == 500) {
                    showToast(followBean.getMessage());
                }
                break;
            case ApiConfig.NOTFOLLOWS:
                NotFollowBean notFollowBean = (NotFollowBean) t[0];
                if (notFollowBean.getCode() == 200) {
                    showToast("取消关注");
                }
                break;
        }
    }
}
