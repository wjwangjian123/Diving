package com.example.edive.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.example.edive.R;
import com.example.edive.activity.DynamicDetailsActivity;
import com.example.edive.adapter.RlvUserPersonDynamicDetailsAdapter;
import com.example.edive.bean.DelectMydynamicBean;
import com.example.edive.bean.LikeBean;
import com.example.edive.bean.MyDynamicBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class LikeUserPersonFragment extends BaseMvpFragment<ProductModel> {


    private final int id;
    private final int userType;
    private final int favoriteType;
    @BindView(R.id.rec)
    RecyclerView mRec;

    private ArrayList<MyDynamicBean.DataBean.ListBean> list;
    private RlvUserPersonDynamicDetailsAdapter adapter;

    private int num =1;
    private int size = 10;
    private ScrollView mSv;
    private boolean isFirstPage;
    private boolean isLastPage;

    public LikeUserPersonFragment(int id, int userType, int favoriteType) {

        this.id = id;
        this.userType = userType;
        this.favoriteType = favoriteType;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_like_user_person;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        mSv = getActivity().findViewById(R.id.sc);
        list = new ArrayList<MyDynamicBean.DataBean.ListBean>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvUserPersonDynamicDetailsAdapter(getActivity(), list);
        mRec.setAdapter(adapter);
        mSv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (mSv.getChildAt(0).getHeight() - mSv.getHeight() == mSv.getScrollY()){
                        if (isLastPage == false) {
                            num++;
                            mPresenter.getData(ApiConfig.MYDYNAMICDETAILSS,String.valueOf(id),num,size,userType);
                        }
                    }
                    return false;
                }
            });
        adapter.setonclick(new RlvUserPersonDynamicDetailsAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int id = list.get(pos).getId();
                int userType = list.get(pos).getUserType();
                Intent intent = new Intent(getActivity(), DynamicDetailsActivity.class);
                intent.putExtra("pos",id);
                intent.putExtra("pl",0);
                intent.putExtra("userType",userType);
                startActivity(intent);
            }
        });
        adapter.setonclickzhuanfa(new RlvUserPersonDynamicDetailsAdapter.setonclickzhuanfa() {
            @Override
            public void setonclickzhuanfa(int pos) {
                int id = list.get(pos).getId();
                int userType = list.get(pos).getUserType();
                Intent intent = new Intent(getActivity(), DynamicDetailsActivity.class);
                intent.putExtra("pos",id);
                intent.putExtra("pl",1);
                intent.putExtra("userType",userType);
                startActivity(intent);
            }
        });
        adapter.setlikeonclick(new RlvUserPersonDynamicDetailsAdapter.setlikeonclick() {
            @Override
            public void setlikeonclick(int pos, View view) {
                int id = list.get(pos).getId();
                int userType = list.get(pos).getUserType();
                mPresenter.getData(ApiConfig.NEWSLIKE,id,userType);
            }
        });
        adapter.setnolikeonclick(new RlvUserPersonDynamicDetailsAdapter.setnolikeonclick() {
            @Override
            public void setnolikeonclick(int pos, View view) {
                int id = list.get(pos).getId();
                int userType = list.get(pos).getUserType();
                mPresenter.getData(ApiConfig.NOTLIKE,id,userType);
            }
        });
    }

    @Override
    public void initData() {
//        String userid = SharedPrefrenceUtils.getString(getActivity(), NormalConfig.USER_PHOTO, "");
        mPresenter.getData(ApiConfig.MYDYNAMICDETAILSS,String.valueOf(id),num,size,userType);
    }

    @Override
    public ProductModel getModel() {
        return new ProductModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.MYDYNAMICDETAILSS:
                MyDynamicBean myDynamicBean = (MyDynamicBean) t[0];
                if (myDynamicBean.getCode() == 200) {
                    isLastPage = myDynamicBean.getData().isIsLastPage();
                    List<MyDynamicBean.DataBean.ListBean> data = myDynamicBean.getData().getList();
                    list.addAll(data);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(myDynamicBean.getMessage());
                }
                break;
            case ApiConfig.DELECTMYDYNAMIC:
                DelectMydynamicBean delectMydynamicBean = (DelectMydynamicBean) t[0];
                if (delectMydynamicBean.getCode() == 200) {
                    showToast(delectMydynamicBean.getMessage());
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(delectMydynamicBean.getMessage());
                }
                break;
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

//                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

}
