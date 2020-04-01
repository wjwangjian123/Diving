package com.example.edive.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.activity.AddressActivity;
import com.example.edive.activity.AttentionActivity;
import com.example.edive.activity.IntegralExchangeActivity;
import com.example.edive.activity.MyDynamicDetailsPersonActivity;
import com.example.edive.activity.MyOrder;
import com.example.edive.activity.MyWalletActivity;
import com.example.edive.activity.MycertificateActivity;
import com.example.edive.activity.PersonalDataActivity;
import com.example.edive.activity.SettingActivity;
import com.example.edive.bean.GuanzhuBean;
import com.example.edive.bean.IntegralAllBean;
import com.example.edive.bean.MyPersonBean;
import com.example.edive.bean.OrdernumBean;
import com.example.edive.bean.SelectDynaimcBean;
import com.example.edive.design.RoundImage;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpFragment;
import com.example.edive.model.PersonModel;
import com.example.edive.utils.NormalConfig;
import com.example.edive.utils.SharedPrefrenceUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends BaseMvpFragment<PersonModel> {

    @BindView(R.id.iv_head)
    RoundImage mIvHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_guanzhunum)
    TextView mTvGuanzhunum;
    @BindView(R.id.tv_conveernum)
    TextView mTvConveernum;
    @BindView(R.id.rl_dindan)
    RelativeLayout mRlDindan;
    @BindView(R.id.rl_qianbao)
    RelativeLayout mRlQianbao;
    @BindView(R.id.rl_zs)
    RelativeLayout mRlZs;
    @BindView(R.id.rl_setting)
    RelativeLayout mRlSetting;
    @BindView(R.id.rl_addres)
    RelativeLayout mRlAddres;
    @BindView(R.id.rl_jf)
    RelativeLayout mRljf;
    @BindView(R.id.tv_dfk)
    TextView mTvDfk;
    @BindView(R.id.tv_yfk)
    TextView mTvYfk;
    @BindView(R.id.tv_jxz)
    TextView mTvJxz;
    @BindView(R.id.tv_tk)
    TextView mTvTk;
    @BindView(R.id.tv_ywc)
    TextView mTvYwc;
    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.tv_text)
    TextView mTvtext;
    @BindView(R.id.viewo)
    View mViewo;
    @BindView(R.id.tv_yuan_dfk)
    TextView mTvYuanDfk;
    @BindView(R.id.tv_yuan_ykk)
    TextView mTvYuanYkk;
    @BindView(R.id.tv_yuan_jxz)
    TextView mTvYuanJxz;
    @BindView(R.id.tv_yuan_tk)
    TextView mTvYuanTk;
    @BindView(R.id.tv_dongtai)
    TextView MtvDongtai;
    @BindView(R.id.tv_gz)
    TextView Mtvgz;
    private int num = 1;
    private int size = 10;
    private RequestBody body;
    private String id;

    //    @BindView(R.id.iv_stay_send)
//    ImageView mIvStaySend;
//    @BindView(R.id.iv_stay_reap)
//    ImageView mIvStayReap;
//    @BindView(R.id.iv_stay_assess)
//    ImageView mIvStayAssess;
//    @BindView(R.id.iv_service)
//    ImageView mIvService;
//    @BindView(R.id.iv_wallet)
//    ImageView mIvWallet;
//    @BindView(R.id.iv_mores)
//    ImageView mIvMores;
//    @BindView(R.id.tv_wallet)
//    TextView mTvWallet;
//    @BindView(R.id.rl_wallet)
//    RelativeLayout mRlWallet;
//    @BindView(R.id.iv_gz)
//    ImageView mIvGz;
//    @BindView(R.id.rl_follow)
//    RelativeLayout mRlFollow;
//    @BindView(R.id.iv_wddt)
//    ImageView mIvWddt;
//    @BindView(R.id.rl_dynamic)
//    RelativeLayout mRlDynamic;
//    @BindView(R.id.iv_jlsq)
//    ImageView mIvJlsq;
//    @BindView(R.id.rl_apply)
//    RelativeLayout mRlApply;
//    @BindView(R.id.iv_jlbs)
//    ImageView mIvJlbs;
//    @BindView(R.id.rl_club)
//    RelativeLayout mRlClub;
//    @BindView(R.id.iv_fy)
//    ImageView mIvFy;
//    @BindView(R.id.rl_back)
//    RelativeLayout mRlBack;
//    @BindView(R.id.iv_dzgl)
//    ImageView mIvDzgl;
//    @BindView(R.id.rl_address)
//    RelativeLayout mRlAddress;

    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        if (!hidden) {
            id = SharedPrefrenceUtils.getString(getActivity(), NormalConfig.USER_PHOTO, "");
            mPresenter.getData(ApiConfig.GUANZHUZONGSHU, Integer.parseInt(id));
            mPresenter.getData(ApiConfig.MYSELECTDYNAMICCOUNT, id);
            mPresenter.getData(ApiConfig.INTEGRAL_ALL, Integer.parseInt(id));
            mPresenter.getData(ApiConfig.MYPERSONDESTIALS);
            mPresenter.getData(ApiConfig.ORDERNUM);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {
        String name = SharedPrefrenceUtils.getString(getActivity(), NormalConfig.NICKNAME, "");
        mTvName.setText(name);
        String text = SharedPrefrenceUtils.getString(getActivity(), NormalConfig.PERSONALIZED, "");
        mTvtext.setText(text);
        String iconhead = SharedPrefrenceUtils.getString(getActivity(), NormalConfig.ICON, "");
        if (iconhead.isEmpty()) {
            Glide.with(getActivity()).load(R.mipmap.morentouxiang).into(mIvHead);
        } else {
            Glide.with(getActivity()).load(iconhead).placeholder(R.mipmap.bg).into(mIvHead);
        }
        id = SharedPrefrenceUtils.getString(getActivity(), NormalConfig.USER_PHOTO, "");
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pageNum", num);
            jsonObject.put("pageSize", size);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jsonObject.toString();
        body = RequestBody.create(type, string);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.GUANZHUZONGSHU, Integer.parseInt(id));
        mPresenter.getData(ApiConfig.MYSELECTDYNAMICCOUNT, String.valueOf(id));
        id = SharedPrefrenceUtils.getString(getActivity(), NormalConfig.USER_PHOTO, "");
        mPresenter.getData(ApiConfig.INTEGRAL_ALL, Integer.parseInt(id));
        mPresenter.getData(ApiConfig.MYPERSONDESTIALS);
        mPresenter.getData(ApiConfig.ORDERNUM);
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
            case ApiConfig.ORDERNUM:
                OrdernumBean ordernumBean = (OrdernumBean) t[0];
                if (ordernumBean.getCode() == 200) {
                    List<OrdernumBean.ResultBean> beanList = ordernumBean.getResult();
                    for (int i = 0; i < beanList.size(); i++) {
                        int status = beanList.get(i).getStatus();
                        int count = beanList.get(i).getCount();
                        if (status == 0) {
                            if (count == 0) {
                                mTvYuanDfk.setVisibility(View.GONE);
                            }else {
                                mTvYuanDfk.setVisibility(View.VISIBLE);
                                mTvYuanDfk.setText(count+"");
                            }
                        } else if (status == 1) {
                            if (count == 0) {
                                mTvYuanYkk.setVisibility(View.GONE);

                            }else {
                                mTvYuanYkk.setVisibility(View.VISIBLE);
                                mTvYuanYkk.setText(count+"");
                            }
                        } else if (status == 2) {
                            if (count == 0) {
                                mTvYuanJxz.setVisibility(View.GONE);
                            }else {
                                mTvYuanJxz.setVisibility(View.VISIBLE);
                                mTvYuanJxz.setText(count+"");
                            }
                        } else if (status == 5) {
                            if (count == 0) {
                                mTvYuanTk.setVisibility(View.GONE);
                            }else {
                                mTvYuanTk.setVisibility(View.VISIBLE);
                                mTvYuanTk.setText(count+"");
                            }
                        }
                    }
                } else {
                    ordernumBean.getMessage();
                }
                break;
            case ApiConfig.GUANZHUZONGSHU:
                GuanzhuBean followListBean = (GuanzhuBean) t[0];
                if (followListBean.getCode() == 200) {
                    mTvGuanzhunum.setText(followListBean.getData() + "");
                }
                break;
            case ApiConfig.MYSELECTDYNAMICCOUNT:
                SelectDynaimcBean selectDynaimcBean = (SelectDynaimcBean) t[0];
                if (selectDynaimcBean.getCode() == 200) {
                    mTvConveernum.setText(selectDynaimcBean.getData() + "");
                } else {
                    showToast(selectDynaimcBean.getCode() + ":" + selectDynaimcBean.getMessage());
                }
                break;
            case ApiConfig.INTEGRAL_ALL:
                IntegralAllBean bean = (IntegralAllBean) t[0];
                if (bean.getCode() == 200) {
                    String data = bean.getData();
                    if (!TextUtils.isEmpty(data)) {

                        mTvIntegral.setText(data + "积分");
                    } else {
                        mTvIntegral.setText("0积分");

                    }

                } else {
                    String message = bean.getMessage();
                    showToast("查询积分：" + message);
                }
                break;
            case ApiConfig.MYPERSONDESTIALS:
                MyPersonBean myPersonBean = (MyPersonBean) t[0];
                if (myPersonBean.getCode() == 200) {
                    MyPersonBean.DataBean data = myPersonBean.getData();
                    mTvName.setText(data.getNickname());
                    mTvtext.setText(data.getPersonalizedSignature());
                    String icon = data.getIcon();
//                    if (icon.isEmpty()) {
//                        Glide.with(getActivity()).load(R.mipmap.morentouxiang).into(mIvHead);
//                    }else {
                    Glide.with(getActivity()).load(icon).placeholder(R.mipmap.morentouxiang).error(R.mipmap.morentouxiang).into(mIvHead);
//                    }
                } else {
                    showToast(myPersonBean.getMessage());
                }
                break;
        }
    }


    @OnClick({R.id.tv_gz,R.id.tv_dongtai,R.id.iv_head, R.id.rl_jf, R.id.rl_addres, R.id.tv_name, R.id.tv_dfk, R.id.tv_guanzhunum, R.id.tv_conveernum, R.id.rl_dindan, R.id.rl_qianbao, R.id.rl_zs, R.id.rl_setting, R.id.tv_yfk, R.id.tv_jxz, R.id.tv_tk, R.id.tv_ywc, R.id.tv_integral})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_gz:
                startActivity(new Intent(getActivity(),AttentionActivity.class));
                break;
            case R.id.iv_head:
                startActivity(new Intent(getActivity(), PersonalDataActivity.class));
                break;
            case R.id.tv_name:
                startActivity(new Intent(getActivity(), PersonalDataActivity.class));
                break;
//            case R.id.iv_more:
//                Intent intent = new Intent(getActivity(), MyOrder.class);
//                intent.putExtra("name", "more");
//                startActivity(intent);
//                break;
            case R.id.tv_guanzhunum:
                //关注
                startActivity(new Intent(getActivity(), AttentionActivity.class));
                break;
            case R.id.tv_conveernum:
                //动态
                startActivity(new Intent(getActivity(), MyDynamicDetailsPersonActivity.class));
                break;
            case R.id.rl_dindan:
                Intent intent = new Intent(getActivity(), MyOrder.class);
                intent.putExtra("name", "home");
                startActivity(intent);
                //订单
                break;
            case R.id.rl_qianbao:
                //钱包
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.rl_zs:
                //证书
                startActivity(new Intent(getActivity(), MycertificateActivity.class));
                break;
            case R.id.rl_setting:
                //设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_dfk:
                Intent intents = new Intent(getActivity(), MyOrder.class);
                intents.putExtra("name", "dfl");
                startActivity(intents);
                break;
            case R.id.tv_yfk:
                //已付款
                Intent intent1 = new Intent(getActivity(), MyOrder.class);
                intent1.putExtra("name", "home");
                startActivity(intent1);
                break;
            case R.id.tv_jxz:
                //进行中
                Intent intent3 = new Intent(getActivity(), MyOrder.class);
                intent3.putExtra("name", "staypay");
                startActivity(intent3);
                break;
            case R.id.tv_tk:
                Intent intent4 = new Intent(getActivity(), MyOrder.class);
                intent4.putExtra("name", "staysend");
                startActivity(intent4);
                break;
            case R.id.tv_ywc:
                Intent intent2 = new Intent(getActivity(), MyOrder.class);
                intent2.putExtra("name", "stayreap");
                startActivity(intent2);
                break;
            case R.id.tv_integral:
//                startActivity(new Intent(getActivity(), MyIntegralActivity.class));
                break;
            case R.id.rl_addres:
                //地址
                startActivity(new Intent(getActivity(), AddressActivity.class));
                break;
            case R.id.rl_jf:
                startActivity(new Intent(getActivity(), IntegralExchangeActivity.class));
                break;
            case R.id.tv_dongtai:
                startActivity(new Intent(getActivity(), MyDynamicDetailsPersonActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getData(ApiConfig.GUANZHUZONGSHU, Integer.parseInt(id));
        mPresenter.getData(ApiConfig.MYSELECTDYNAMICCOUNT, id);
        mPresenter.getData(ApiConfig.MYPERSONDESTIALS);
        mPresenter.getData(ApiConfig.INTEGRAL_ALL, Integer.parseInt(id));
        mPresenter.getData(ApiConfig.ORDERNUM);
    }

}
