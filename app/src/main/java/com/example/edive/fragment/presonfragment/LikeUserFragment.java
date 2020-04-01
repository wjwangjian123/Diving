package com.example.edive.fragment.presonfragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edive.R;
import com.example.edive.activity.UserPersonDestialsActivity;
import com.example.edive.adapter.RlvFollowListAdapter;
import com.example.edive.bean.FollowBean;
import com.example.edive.bean.FollowListBean;
import com.example.edive.bean.NotFollowBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.model.PersonModel;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikeUserFragment extends BaseMvpFragment<PersonModel> {


    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.sm)
    SmartRefreshLayout mSm;
    private ArrayList<FollowListBean.ResultBean.ListBean> list;
    private RlvFollowListAdapter adapter;
    private int num = 1;
    private int size = 10;
    private RequestBody body;
    private RequestBody bodys;
    private int favoriteType = 1;
    private int total;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_like_user;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvFollowListAdapter(getActivity(), list);
        mRec.setAdapter(adapter);
        adapter.setonclick(new RlvFollowListAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int userId = list.get(pos).getTargetId();
                int favoriteTypes = list.get(pos).getFavoriteType();
                Intent intent = new Intent(getActivity(), UserPersonDestialsActivity.class);
                intent.putExtra("id",userId);
                intent.putExtra("userType",favoriteTypes);
                startActivity(intent);
            }
        });
        adapter.seonclckListener(new RlvFollowListAdapter.seonclckListener() {
            @Override
            public void seonclckListener(int pos) {
                int favoriteType = list.get(pos).getFavoriteType();
                int targetId = list.get(pos).getTargetId();
                MediaType type = MediaType.parse("application/json;charset=UTF-8");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("favoriteType", favoriteType);
                    jsonObject.put("status", 1);
                    jsonObject.put("targetId", targetId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String string = jsonObject.toString();
                RequestBody body = RequestBody.create(type, string);
                mPresenter.getData(ApiConfig.FOLLOWUSERS,body);
            }
        });
        mSm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (total < 5) {
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
                adapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
            }
        });
        adapter.setDelItemListener(new RlvFollowListAdapter.setDelItemListener() {
            @Override
            public void setDelItemListener(int pos) {
                int favoriteType = list.get(pos).getFavoriteType();
                MediaType types = MediaType.parse("application/json;charset=UTF-8");
                JSONObject jsonObjects = new JSONObject();
                try {
                    jsonObjects.put("favoriteType", favoriteType);
                    jsonObjects.put("status", 2);
                    jsonObjects.put("targetId", list.get(pos).getTargetId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String str = jsonObjects.toString();
                bodys = RequestBody.create(types, str);
//                list.remove(pos);
//                adapter.notifyDataSetChanged();
                mPresenter.getData(ApiConfig.NOTFOLLOW, bodys);
            }
        });

//        MediaType type = MediaType.parse("application/json;charset=UTF-8");
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("pageNum", num);
//            jsonObject.put("pageSize", size);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String string = jsonObject.toString();
//        body = RequestBody.create(type, string);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.FOLLOWLIST, num, size, favoriteType);
    }

    @Override
    public PersonModel getModel() {
        return new PersonModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        showToast(e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.FOLLOWLIST:
                FollowListBean followListBean = (FollowListBean) t[0];
                if (followListBean.getCode() == 200) {
                    total = followListBean.getResult().getTotal();
                    List<FollowListBean.ResultBean.ListBean> bean = followListBean.getResult().getList();
                    FollowListBean.ResultBean result = followListBean.getResult();
                    list.addAll(bean);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(followListBean.getMessage());
                }
                break;
            case ApiConfig.NOTFOLLOW:
                NotFollowBean notFollowBean = (NotFollowBean) t[0];
                if (notFollowBean.getCode() == 200) {
                    showToast("取消关注");
                }
                break;
            case ApiConfig.FOLLOWUSERS:
                FollowBean followBean = (FollowBean) t[0];
                if (followBean.getCode() == 200) {
                    showToast("关注成功");

                } else if (followBean.getCode() == 500) {
                    showToast(followBean.getMessage());
                }
                break;
        }
    }
}
