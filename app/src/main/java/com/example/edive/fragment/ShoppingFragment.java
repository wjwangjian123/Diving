package com.example.edive.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.activity.DivingCertificateActivity;
import com.example.edive.activity.DivingDestiActivity;
import com.example.edive.activity.DivingTripActivity;
import com.example.edive.adapter.RlvHomesAdapter;
import com.example.edive.adapter.RlvShoppingOneAdapter;
import com.example.edive.adapter.RlvShoppingTwoAdapter;
import com.example.edive.bean.BannerInfo;
import com.example.edive.bean.CertificcateBean;
import com.example.edive.bean.DivingBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.model.HomeModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cc.ibooker.zviewpagerlib.Holder;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseMvpFragment<HomeModel> {


    @BindView(R.id.recbanner)
    RecyclerView mRecbanner;
    @BindView(R.id.tv_more_diving)
    TextView mTvMoreDiving;
    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.tv_more_certificate)
    TextView mTvMoreCertificate;
    @BindView(R.id.rectwo)
    RecyclerView mRectwo;
    private ArrayList<BannerInfo.DataBean> bannList;
    private RlvHomesAdapter adapter;
    private RlvShoppingOneAdapter adapters;
    private ArrayList<CertificcateBean.ResultBean> str;
    private int num = 1;
    private int size = 10;
    private ArrayList<DivingBean.ResultBean.ListBean> list;
    private RlvShoppingTwoAdapter twoAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        str = new ArrayList<>();
        bannList = new ArrayList<>();
        adapter = new RlvHomesAdapter(getActivity(), bannList);
        mRecbanner.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecbanner.setAdapter(adapter);


        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRec.setLayoutManager(linearLayoutManagers);
        adapters = new RlvShoppingOneAdapter(getActivity(), list);
        mRec.setAdapter(adapters);
        adapters.setonclcik(new RlvShoppingOneAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int id = list.get(pos).getId();
                Intent intent = new Intent(getActivity(), DivingDestiActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(mRectwo);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRectwo.setLayoutManager(linearLayoutManager);
        twoAdapter = new RlvShoppingTwoAdapter(getActivity(), str);
        mRectwo.setAdapter(twoAdapter);

        twoAdapter.setonclick(new RlvShoppingTwoAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int id = str.get(pos).getId();
                int productNum = str.get(pos).getProductNum();
                if (productNum == 0) {
                    showToast("此产品暂无信息");
                }else {
                    Intent intent = new Intent(getActivity(), DivingCertificateActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void initData() {
        MediaType types = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObjects = new JSONObject();
        try {
            jsonObjects.put("pageNum", num);
            jsonObjects.put("pageSize", size);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String strings = jsonObjects.toString();
        RequestBody bodys = RequestBody.create(types, strings);
        mPresenter.getData(ApiConfig.SHOPPING_DIVING, bodys);
        mPresenter.getData(ApiConfig.CERTIFICATE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("startTime", time);
            jsonObject.put("endTime", time);
            jsonObject.put("type", "2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jsonObject.toString();
        RequestBody body = RequestBody.create(type, string);
        mPresenter.getData(ApiConfig.HomeTest_DATA, body);




    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("ssssssssss",e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.HomeTest_DATA:
                BannerInfo bean = (BannerInfo) t[0];
                List<BannerInfo.DataBean> results = bean.getData();
                bannList.addAll(results);
                adapter.notifyDataSetChanged();
                break;
            case ApiConfig.SHOPPING_DIVING:
                DivingBean divingBean = (DivingBean) t[0];
                if (divingBean.getCode() == 200) {
                    List<DivingBean.ResultBean.ListBean> hahben = divingBean.getResult().getList();
                    list.addAll(hahben);
                    adapters.notifyDataSetChanged();
                }else {
                    showToast(divingBean.getMessage());
                }

                break;
                case ApiConfig.CERTIFICATE:
                    CertificcateBean certificcateBean = (CertificcateBean) t[0];
                    if (certificcateBean.getCode() == 200) {
                        List<CertificcateBean.ResultBean> result = certificcateBean.getResult();
                        str.addAll(result);
                        twoAdapter.notifyDataSetChanged();
                    }else {
                        showToast(certificcateBean.getCode()+":"+certificcateBean.getMessage());
                    }
                    break;
        }
    }

    @OnClick({R.id.tv_more_diving, R.id.tv_more_certificate})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_more_diving:
                startActivity(new Intent(getActivity(), DivingTripActivity.class));
                break;
            case R.id.tv_more_certificate:
//                startActivity(new Intent(getActivity(), DivingCertificateActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        bannList.clear();
//        list.clear();
//        str.clear();
//        initData();
    }
}
