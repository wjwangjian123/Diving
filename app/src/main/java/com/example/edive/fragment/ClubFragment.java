package com.example.edive.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.edive.R;
import com.example.edive.adapter.RlvClubAdapter;
import com.example.edive.bean.FuBean;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.model.ClubModel;



import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClubFragment extends BaseMvpFragment<ClubModel> {

    @BindView(R.id.rec_club)
    RecyclerView mRecClub;
    private ArrayList<FuBean.ResultsBean> list;
    private RlvClubAdapter adapter;
//    private String url = "https://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_club;
    }

    @Override
    public void initView() {
//        list = new ArrayList<>();
//        adapter = new RlvClubAdapter(getActivity(), list);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        mRecClub.setLayoutManager(linearLayoutManager);
//        mRecClub.setAdapter(adapter);
    }

    @Override
    public void initData() {
//        mPresenter.getData(ApiConfig.CLUB_DATA,url);
    }

    @Override
    public ClubModel getModel() {
        return new ClubModel();
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi){
//            case ApiConfig.CLUB_DATA:
//                FuBean bean = (FuBean) t[0];
//                List<FuBean.ResultsBean> results = bean.getResults();
//                list.addAll(results);
//                adapter.notifyDataSetChanged();
//                break;
        }
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }
}
