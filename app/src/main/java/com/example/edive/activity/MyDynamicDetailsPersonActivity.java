package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.edive.R;
import com.example.edive.adapter.RlvMyDynamicDetailsAdapter;
import com.example.edive.bean.DelectMydynamicBean;
import com.example.edive.bean.LikeBean;
import com.example.edive.bean.MyDynamicBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.SharedPrefrenceUtils;
import com.example.edive.model.ProductModel;
import com.example.edive.utils.NormalConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyDynamicDetailsPersonActivity extends BaseMvpActivity<ProductModel> {

    @BindView(R.id.rec)
    RecyclerView mRec;
    private ArrayList<MyDynamicBean.DataBean.ListBean> list;
    private RlvMyDynamicDetailsAdapter adapter;
    private int pageNum =1;
    private int pageSize =10;

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
        return R.layout.activity_my_dynamic_details_person;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyDynamicDetailsPersonActivity.this);
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvMyDynamicDetailsAdapter(MyDynamicDetailsPersonActivity.this, list);
        mRec.setAdapter(adapter);
        adapter.setonclickListent(new RlvMyDynamicDetailsAdapter.setonclickListent() {
            @Override
            public void setonclickListent(int pos) {
                int userId = list.get(pos).getUserId();
                int userType = list.get(pos).getUserType();
                Intent intent = new Intent(MyDynamicDetailsPersonActivity.this, UserPersonDestialsActivity.class);
                intent.putExtra("id",userId);
                intent.putExtra("userType",userType);
                startActivity(intent);
            }
        });
        adapter.setonclick(new RlvMyDynamicDetailsAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int id = list.get(pos).getId();
                int userType = list.get(pos).getUserType();
                Intent intent = new Intent(MyDynamicDetailsPersonActivity.this, DynamicDetailsActivity.class);
                intent.putExtra("pos",id);
                intent.putExtra("pl",0);
                intent.putExtra("userType",userType);
                startActivity(intent);
            }
        });
        adapter.setonclickzhuanfa(new RlvMyDynamicDetailsAdapter.setonclickzhuanfa() {
            @Override
            public void setonclickzhuanfa(int pos) {
                int id = list.get(pos).getId();
                int userType = list.get(pos).getUserType();
                Intent intent = new Intent(MyDynamicDetailsPersonActivity.this, DynamicDetailsActivity.class);
                intent.putExtra("pos",id);
                intent.putExtra("pl",1);
                intent.putExtra("userType",userType);
                startActivity(intent);
            }
        });
        adapter.setfollowonclick(new RlvMyDynamicDetailsAdapter.setfollowonclick() {
            @Override
            public void setfollowonclick(int pos) {
                int id = list.get(pos).getId();
                list.remove(pos);
                mPresenter.getData(ApiConfig.DELECTMYDYNAMIC,id);
            }
        });
        adapter.setlikeonclick(new RlvMyDynamicDetailsAdapter.setlikeonclick() {
            @Override
            public void setlikeonclick(int pos, View view) {
                int id = list.get(pos).getId();
                int userType = list.get(pos).getUserType();
                mPresenter.getData(ApiConfig.NEWSLIKE,id,userType);
            }
        });
        adapter.setnolikeonclick(new RlvMyDynamicDetailsAdapter.setnolikeonclick() {
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
        String userid = SharedPrefrenceUtils.getString(MyDynamicDetailsPersonActivity.this, NormalConfig.USER_PHOTO, "");
        mPresenter.getData(ApiConfig.MYDYNAMICDETAILS,userid,pageNum,pageSize);
    }

    @Override
    public ProductModel getModel() {
        return new ProductModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("erro",e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
            case ApiConfig.MYDYNAMICDETAILS:
                MyDynamicBean myDynamicBean = (MyDynamicBean) t[0];
                if (myDynamicBean.getCode() == 200) {
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
