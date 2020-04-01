package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.edive.R;
import com.example.edive.adapter.RlvAddressListAdapter;
import com.example.edive.bean.AddBean;
import com.example.edive.bean.AddressListBean;
import com.example.edive.bean.DelectAddressBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.local_utils.SharedPrefrenceUtils;
import com.example.edive.model.PersonModel;
import com.example.edive.utils.NormalConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddressActivity extends BaseMvpActivity<PersonModel> {

    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.bt_addaddress)
    Button mBtAddaddress;
    @BindView(R.id.smrefresh)
    SmartRefreshLayout mSmrefresh;
    private String id;
    private int num = 1;
    private int size = 10;
    private ArrayList<AddressListBean.ResultBean.ListBean> list;
    private RlvAddressListAdapter adapter;
    private boolean isLastPage;

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
        return R.layout.activity_address;
    }

    @Override
    public void initView() {
        id = SharedPrefrenceUtils.getString(AddressActivity.this, NormalConfig.USER_PHOTO, "");
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddressActivity.this);
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvAddressListAdapter(AddressActivity.this, list);
        mRec.setAdapter(adapter);
        adapter.setonDelect(new RlvAddressListAdapter.setonDelect() {
            @Override
            public void setonDelect(int pos) {
                int id = list.get(pos).getId();
                list.remove(pos);
                mPresenter.getData(ApiConfig.DELECTADDRESS, id);
            }
        });
        adapter.setonclickmoren(new RlvAddressListAdapter.setonclickmoren() {
            @Override
            public void setonclickmoren(int pos) {
                int id = list.get(pos).getId();
                mPresenter.getData(ApiConfig.ADDARESSMOREN, id);
            }
        });
        adapter.setonbjclick(new RlvAddressListAdapter.setonbjclick() {
            @Override
            public void setonbjclick(int pos) {
                int id = list.get(pos).getId();
                Intent intent = new Intent(AddressActivity.this, EditActivity.class);
                intent.putExtra("name", list.get(pos).getReceiverName());
                intent.putExtra("phone", list.get(pos).getReceiverMobileNo());
                intent.putExtra("addaress", list.get(pos).getDetailAddress());
                intent.putExtra("cityname", list.get(pos).getCityName());
                intent.putExtra("provinceName", list.get(pos).getProvinceName());
                intent.putExtra("districtName", list.get(pos).getDistrictName());
                intent.putExtra("id", id);
                intent.putExtra("dount", list.get(pos).getDefaultAddress());
                startActivity(intent);
            }
        });
        mSmrefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isLastPage == true) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }   else {
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
    }

    @Override
    public void initData() {
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("adminId", id);
            jsonObject.put("pageNum", num);
            jsonObject.put("pageSize", size);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String strings = jsonObject.toString();
        RequestBody body = RequestBody.create(type, strings);
        mPresenter.getData(ApiConfig.ADDRESSLIST, body);
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
        switch (whichApi) {
            case ApiConfig.ADDRESSLIST:
                list.clear();
                AddressListBean addressListBean = (AddressListBean) t[0];
                if (addressListBean.getCode() == 200) {
                    isLastPage = addressListBean.getResult().isIsLastPage();
                    List<AddressListBean.ResultBean.ListBean> result = addressListBean.getResult().getList();
                    this.list.addAll(result);
                    adapter.notifyDataSetChanged();
                }
                break;
            case ApiConfig.DELECTADDRESS:
                DelectAddressBean addressBean = (DelectAddressBean) t[0];
                if (addressBean.getCode() == 200) {
                    showToast("删除成功");
                    adapter.notifyDataSetChanged();
                }
                break;
            case ApiConfig.ADDARESSMOREN:
                AddBean addBean = (AddBean) t[0];
                if (addBean.getCode() == 200) {
                    showToast("设置成功");
                    MediaType type = MediaType.parse("application/json;charset=UTF-8");
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("adminId", id);
                        jsonObject.put("pageNum", num);
                        jsonObject.put("pageSize", size);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String strings = jsonObject.toString();
                    RequestBody body = RequestBody.create(type, strings);
                    mPresenter.getData(ApiConfig.ADDRESSLIST, body);
                } else {
                    showToast(addBean.getMessage());
                }
                break;
        }
    }

    @OnClick(R.id.bt_addaddress)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_addaddress:
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("adminId", id);
            jsonObject.put("pageNum", num);
            jsonObject.put("pageSize", size);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String strings = jsonObject.toString();
        RequestBody body = RequestBody.create(type, strings);
        mPresenter.getData(ApiConfig.ADDRESSLIST, body);
    }
}
