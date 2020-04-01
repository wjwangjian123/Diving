package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.adapter.RlvConversationAdapter;
import com.example.edive.adapter.RlvSearchDynamicAdapter;
import com.example.edive.adapter.RlvTopicSearchDeatilsAdapter;
import com.example.edive.bean.ConversationBean;
import com.example.edive.bean.SearchDynamicBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.HomeModel;
import com.example.edive.utils.CloseSearchUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ConversationActivity extends BaseMvpActivity<HomeModel> {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_close)
    TextView mTvClose;
    @BindView(R.id.rl_dynic)
    RelativeLayout mRlDynic;
    @BindView(R.id.rec)
    RecyclerView mRec;
    @BindView(R.id.rec_search)
    RecyclerView mRecSearch;
    @BindView(R.id.et_search_data)
    EditText mEtSearchData;
    @BindView(R.id.smrefres)
    SmartRefreshLayout mSmrefres;
    @BindView(R.id.itemrl)
    RelativeLayout mRl;
    private ArrayList<ConversationBean.DataBean.ListBean> list;
    private RlvConversationAdapter adapter;
    int num = 1 ;
    int size = 10 ;
    private ArrayList<SearchDynamicBean.DataBean> searchList;
    private View inflate1;
    private PopupWindow popupWindows;
    private RlvSearchDynamicAdapter dynamicAdapter;
    private RlvTopicSearchDeatilsAdapter adapterSearch;
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
        return R.layout.activity_conversation;
    }

    @Override
    public void initView() {
        list = new ArrayList<>();
        searchList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ConversationActivity.this);
        mRec.setLayoutManager(linearLayoutManager);
        adapter = new RlvConversationAdapter(ConversationActivity.this, list);
        mRec.setAdapter(adapter);
        adapter.setonclick(new RlvConversationAdapter.setonclick() {
            @Override
            public void setonclick(int pos) {
                int id = list.get(pos).getId();
                Intent intent = new Intent(ConversationActivity.this, TopicDetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        mSmrefres.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                list.clear();
                if (isLastPage == true) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else {
                    num++;
                    initData();
                    refreshLayout.finishLoadMore(true);

                }
            }
        });
        mSmrefres.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                num=1;
                initData();
                refreshLayout.finishRefresh();
            }
        });
//        mEtSearchData.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(final CharSequence s, int start, int before, int count) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        String string = s.toString();
//                        if (string.isEmpty()) {
////                            showToast("搜索信息不能为空");
//                        }else {
//                            mPresenter.getData(ApiConfig.CONVERSEARCH_DATA,string);
//                        }
//                    }
//                }, 300);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        mEtSearchData.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEtSearchData.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        mEtSearchData.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //do something;
                    String searchdata = mEtSearchData.getText().toString();
                    if (searchdata.isEmpty()) {
                        showToast("搜索信息不能为空");
                    }else {
                        mPresenter.getData(ApiConfig.CONVERSEARCH_DATA,searchdata);
                    }
                    return true;
                }
                return false;
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
                if (bean.getCode() == 200) {
                    isLastPage = bean.getData().isIsLastPage();
                    List<ConversationBean.DataBean.ListBean> beans = bean.getData().getList();
                    list.addAll(beans);
                    adapter.notifyDataSetChanged();
                }else {
                    showToast(bean.getMessage());
                }
                break;
            case ApiConfig.CONVERSEARCH_DATA:
                CloseSearchUtils.hideKeyboard(mEtSearchData);
                mRlDynic.setVisibility(View.GONE);
//                mSmrefres.setVisibility(View.GONE);
                mRec.setVisibility(View.GONE);
                mRecSearch.setVisibility(View.VISIBLE);
                searchList.clear();
                SearchDynamicBean searchDynamicBean = (SearchDynamicBean) t[0];
                if (searchDynamicBean.getCode() == 200) {
                    List<SearchDynamicBean.DataBean> data = searchDynamicBean.getData();
                    searchList.addAll(data);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ConversationActivity.this);
                    mRecSearch.setLayoutManager(linearLayoutManager);
                    adapterSearch = new RlvTopicSearchDeatilsAdapter(ConversationActivity.this, searchList);
                    mRecSearch.setAdapter(adapterSearch);
                    adapterSearch.setonclick(new RlvTopicSearchDeatilsAdapter.setonclick() {
                        @Override
                        public void setonclick(int pos) {
                            int id = searchList.get(pos).getId();
                            Intent intent = new Intent(ConversationActivity.this, TopicDetailsActivity.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    });
//                    Intent intent = new Intent(ConversationActivity.this, TopicSearchDeatilsActivity.class);
//                    intent.putExtra("list", (Serializable) searchList);
//                    startActivity(intent);
                }else {
                    showToast(searchDynamicBean.getMessage());
                }
//                searchList.clear();
//                inflate1 = LayoutInflater.from(ConversationActivity.this).inflate(R.layout.layout_popo_search_data, null);
//                RecyclerView mRecs = inflate1.findViewById(R.id.rec);
//                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(ConversationActivity.this);
//                mRecs.setLayoutManager(linearLayoutManager1);
//                dynamicAdapter = new RlvSearchDynamicAdapter(ConversationActivity.this, searchList);
//                mRecs.setAdapter(dynamicAdapter);
//                dynamicAdapter.setonclick(new RlvSearchDynamicAdapter.setonclcik() {
//                    @Override
//                    public void setonclick(int pos) {
//                        int id = list.get(pos).getId();
//                        Intent intent = new Intent(ConversationActivity.this, TopicDetailsActivity.class);
//                        intent.putExtra("id",id);
//                        startActivity(intent);
//                    }
//                });
//
//                popupWindows = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//                popupWindows.setBackgroundDrawable(new ColorDrawable());
//                popupWindows.setOutsideTouchable(true);
//                popupWindows.setFocusable(true);
//                popupWindows.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//                popupWindows.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//                popupWindows.setAnimationStyle(R.style.PopupAnimation);
//                popupWindows.update();
//
//
//                // 设置背景颜色变暗
//                WindowManager.LayoutParams lp = this.getWindow().getAttributes();
//                lp.alpha = 0.5f;
//                this.getWindow().setAttributes(lp);
//                popupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                    @Override
//                    public void onDismiss() {
//                        WindowManager.LayoutParams lp = ConversationActivity.this.getWindow().getAttributes();
//                        lp.alpha = 1f;
//                        ConversationActivity.this.getWindow().setAttributes(lp);
//                    }
//                });
//                List<SearchDynamicBean.DataBean> data = searchDynamicBean.getData();
//                searchList.addAll(data);
//                dynamicAdapter.notifyDataSetChanged();
//                if (searchList.isEmpty()) {
//                    showToast("搜索不到此信息");
//                }else {
//                    popupWindows.showAsDropDown(mRl, 0, 0);
//                }

                break;
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_close, R.id.rl_dynic})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_close:
                mEtSearchData.setText("");
                mRecSearch.setVisibility(View.GONE);
                mRec.setVisibility(View.VISIBLE);
                mRlDynic.setVisibility(View.VISIBLE);
                CloseSearchUtils.hideKeyboard(mEtSearchData);
                break;
            case R.id.rl_dynic:
                //热门话题

                break;
        }
    }

}
