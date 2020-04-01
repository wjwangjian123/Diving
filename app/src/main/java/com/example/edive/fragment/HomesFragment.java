package com.example.edive.fragment;


import android.app.Application;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.activity.CoachApplicationActivity;
import com.example.edive.activity.ConversationActivity;
import com.example.edive.activity.DiveLogActivity;
import com.example.edive.activity.IntegralActivity;
import com.example.edive.activity.MainActivity;
import com.example.edive.activity.RestorationActivity;
import com.example.edive.activity.TopicDetailsActivity;
import com.example.edive.adapter.RlvHomeChildAdapter;
import com.example.edive.adapter.RlvHomesAdapter;
import com.example.edive.adapter.VpHomeTabAdapter;
import com.example.edive.bean.BannerInfo;
import com.example.edive.bean.TopicBean;
import com.example.edive.fragment.homefragment.HotFragment;
import com.example.edive.fragment.homefragment.NewDynamicFragment;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.local_utils.SharedPrefrenceUtils;
import com.example.edive.local_utils.SpacesItemDecoration;
import com.example.edive.model.HomeModel;
import com.example.edive.utils.NormalConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomesFragment extends BaseMvpFragment<HomeModel> {


    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.rec_class)
    RecyclerView mRecClass;
    @BindView(R.id.home_tabs)
    TabLayout mHomeTabs;
    @BindView(R.id.homevp)
    ViewPager mHomevp;
    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.iv_reduction)
    ImageView mIvReduction;
    @BindView(R.id.iv_integral)
    ImageView mIvIntegral;
    @BindView(R.id.iv_apply)
    ImageView mIvApply;
    @BindView(R.id.iv_divinglog)
    ImageView mIvDivinglog;
    @BindView(R.id.tv_topic_all)
    TextView mTvTopicAll;
    private ArrayList<Fragment> fs;
    private ArrayList<BannerInfo.DataBean> list;
    private RlvHomesAdapter adapter;
    private VpHomeTabAdapter vpHomeTabAdapter;
    private RlvHomeChildAdapter adapters;
    private ArrayList<TopicBean.DataBean> dataBeans;

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homes;
    }

    @Override
    public void initView() {
        dataBeans = new ArrayList<>();
        list = new ArrayList<>();
        adapter = new RlvHomesAdapter(getActivity(), list);
        mRec.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRec.setAdapter(adapter);
        LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(getActivity());
        linearLayoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecClass.setLayoutManager(linearLayoutManagers);
        adapters = new RlvHomeChildAdapter(getActivity(), dataBeans);
        mRecClass.addItemDecoration(new SpacesItemDecoration(10,0));
        mRecClass.setAdapter(adapters);
        adapters.setonclick(new RlvHomeChildAdapter.setonclick() {
            @Override
            public void setonclick(View view, int pos) {
                Intent intent = new Intent(getActivity(), TopicDetailsActivity.class);
                intent.putExtra("id", pos);
                startActivity(intent);
            }
        });
        initViewPage();


    }

    private void initViewPage() {
        fs = new ArrayList<>();
        fs.add(new NewDynamicFragment());
        fs.add(new HotFragment());
        mHomeTabs.addTab(mHomeTabs.newTab().setText("最新动态"));
        mHomeTabs.addTab(mHomeTabs.newTab().setText("热门动态"));
        vpHomeTabAdapter = new VpHomeTabAdapter(getChildFragmentManager(), fs);
        mHomevp.setAdapter(vpHomeTabAdapter);
        mHomeTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mHomevp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mHomevp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mHomeTabs));
//        mHomeTabs.setTabIndicatorFullWidth(false);
        mHomeTabs.setSelectedTabIndicatorHeight(0);
    }


    @Override
    public void initData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String time = simpleDateFormat.format(date);
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("startTime", time);
            jsonObject.put("endTime", time);
            jsonObject.put("type", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jsonObject.toString();
        RequestBody body = RequestBody.create(type, string);
        mPresenter.getData(ApiConfig.HomeTest_DATA, body);
        mPresenter.getData(ApiConfig.HOME_TOPIC_DATA);
        Log.e("token", SharedPrefrenceUtils.getString(getActivity(), NormalConfig.TOKEN,""));
    }

    @Override
    public HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Log.e("homes___",e.getMessage());
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.HomeTest_DATA:
                BannerInfo bean = (BannerInfo) t[0];
                if (bean.getCode() == 200) {
                    List<BannerInfo.DataBean> results = bean.getData();
                    list.addAll(results);
                    adapter.notifyDataSetChanged();
                    Log.e("llllllll", bean.getMessage());
                }
                break;
            case ApiConfig.HOME_TOPIC_DATA:
                TopicBean beans = (TopicBean) t[0];
                String message = beans.getMessage();
                if (message.isEmpty()) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                List<TopicBean.DataBean> data = beans.getData();
                dataBeans.addAll(data);
                adapters.notifyDataSetChanged();
                break;
        }
    }

    @OnClick({R.id.iv_reduction, R.id.iv_integral, R.id.iv_apply, R.id.iv_divinglog,R.id.tv_topic_all})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_reduction:
                startActivity(new Intent(getActivity(), RestorationActivity.class));
                break;
            case R.id.iv_integral:
                startActivity(new Intent(getActivity(), IntegralActivity.class));
                break;
            case R.id.iv_apply:
                startActivity(new Intent(getActivity(), CoachApplicationActivity.class));
                break;
            case R.id.iv_divinglog:
                startActivity(new Intent(getActivity(), DiveLogActivity.class));
                break;
            case R.id.tv_topic_all:
                startActivity(new Intent(getActivity(), ConversationActivity.class));
                break;
        }
    }
}
