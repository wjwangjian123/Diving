package com.example.edive.fragment.presonfragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.edive.R;
import com.example.edive.activity.TopicDetailsActivity;
import com.example.edive.adapter.RlvTopicLikeListAdapter;
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
public class LikeTopicFragment extends BaseMvpFragment<PersonModel> {


    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.smrefresh)
    SmartRefreshLayout mSmrefresh;
    private ArrayList<FollowListBean.ResultBean.ListBean> list;
    private RlvTopicLikeListAdapter adapter;
    private int num = 1;
    private int size = 10;
    private RequestBody bodys;
    private int favoriteType = 2;
    private int total;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_like_topic;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvTopicLikeListAdapter(getActivity(), list);
        mRec.setAdapter(adapter);
        mSmrefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
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
                initData();
                refreshLayout.finishRefresh();
            }
        });
//        mSmrefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//
//                refreshLayout.finishLoadMore();
//            }
//
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.finishRefresh();
//            }
//        });
        adapter.setonclick(new RlvTopicLikeListAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int targetId = list.get(pos).getTargetId();
                Intent intent = new Intent(getActivity(), TopicDetailsActivity.class);
                intent.putExtra("id", targetId);
                startActivity(intent);

            }
        });

        adapter.setDelItemListener(new RlvTopicLikeListAdapter.setDelItemListener() {
            @Override
            public void setDelItemListener(int pos) {
                MediaType types = MediaType.parse("application/json;charset=UTF-8");
                JSONObject jsonObjects = new JSONObject();
                try {
                    jsonObjects.put("favoriteType", 6);
                    jsonObjects.put("status", 2);
                    jsonObjects.put("targetId", list.get(pos).getTargetId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String str = jsonObjects.toString();
                bodys = RequestBody.create(types, str);
                list.remove(pos);
                adapter.notifyDataSetChanged();
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
                    list.addAll(bean);
                    adapter.notifyDataSetChanged();

                }else {
                    showToast(followListBean.getMessage());
                }
                break;
            case ApiConfig.NOTFOLLOW:
                NotFollowBean notFollowBean = (NotFollowBean) t[0];
                if (notFollowBean.getCode() == 200) {
                    showToast(notFollowBean.getMessage());
                }
                break;
        }
    }

}
