package com.example.edive.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.bean.Addressbean;
import com.example.edive.bean.DelectAddressBean;
import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.BaseMvpActivity;
import com.example.edive.model.PersonModel;
import com.example.edive.utils.AppValidationMgr;
import com.example.edive.utils.CloseSearchUtils;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditActivity extends BaseMvpActivity<PersonModel> {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.et_area)
    EditText mEtArea;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.bt_commit)
    Button mBtCommit;
    private String name;
    private String phone;
    private String addaress;
    private String cityname;
    private String provinceName;
    private String districtName;
    private CityPickerView mPicker;
    private String provinces;
    private String citys;
    private String districts;
    private int id;
    private int dount;

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
        return R.layout.activity_edit;
    }

    @Override
    public void initView() {
        mPicker = new CityPickerView();
        mPicker.init(this);
        id = getIntent().getExtras().getInt("id");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        addaress = getIntent().getStringExtra("addaress");
        cityname = getIntent().getStringExtra("cityname");
        provinceName = getIntent().getStringExtra("provinceName");
        districtName = getIntent().getStringExtra("districtName");
        dount = getIntent().getExtras().getInt("dount");
        mEtName.setText(name);
        mEtPhone.setText(phone);
        mEtAddress.setText(addaress);
        mEtArea.setText(cityname + provinceName + districtName);
    }

    @Override
    public void initData() {

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
        switch (whichApi){
            case ApiConfig.DELECTADDRESS:
                DelectAddressBean addressBean = (DelectAddressBean) t[0];
                if (addressBean.getCode() == 200) {
//                    showToast("删除成功");
//                    adapter.notifyDataSetChanged();
                }
                break;
            case ApiConfig.EDITADDARESS:
                Addressbean addressbean = (Addressbean) t[0];
                if (addressbean.getCode() == 200) {
                    showToast("保存成功");
                    mPresenter.getData(ApiConfig.DELECTADDRESS,id);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 300);
                }else {
                    showToast(addressbean.getMessage());
                }
                break;
        }
    }

    @OnClick({R.id.bt_commit, R.id.iv_more})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_commit:

                String name = mEtName.getText().toString();
                String phone = mEtPhone.getText().toString();
                String location = mEtArea.getText().toString();
                String address = mEtAddress.getText().toString();
//                mSwich.get
                if (!name.isEmpty() && !phone.isEmpty() && !location.isEmpty() && !address.isEmpty()) {
                    if (AppValidationMgr.isPhone(phone)) {
                        MediaType type = MediaType.parse("application/json;charset=UTF-8");
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("cityName", provinceName);
                            jsonObject.put("provinceName", cityname);
                            jsonObject.put("districtName", districtName);
                            //是否默认
                            jsonObject.put("defaultAddress", dount);
                            //详细地址
                            jsonObject.put("detailAddress", address);
                            jsonObject.put("receiverMobileNo", phone);
                            jsonObject.put("receiverName", name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String strings = jsonObject.toString();
                        RequestBody body = RequestBody.create(type, strings);
//                        mPresenter.getData(ApiConfig.ADDADDRESS, body);
                        mPresenter.getData(ApiConfig.EDITADDARESS,body);

                    } else {
                        showToast("请填写正确的手机号");
                    }
                } else {
                    showToast("请填写完整信息");
                }
                break;
            case R.id.iv_more:
                CloseSearchUtils.hideKeyboard(mEtAddress);
                CloseSearchUtils.hideKeyboard(mEtArea);
                CloseSearchUtils.hideKeyboard(mEtName);
                CloseSearchUtils.hideKeyboard(mEtPhone);


                CityConfig cityConfig = new CityConfig.Builder()
                        .title("选择城市")//标题
                        .titleTextSize(18)//标题文字大小
                        .titleTextColor("#FFFFFF")//标题文字颜  色
                        .titleBackgroundColor("#001838")//标题栏背景色
                        .confirTextColor("#1571FA")//确认按钮文字颜色
                        .confirmText("确认")//确认按钮文字
                        .confirmTextSize(16)//确认按钮文字大小
                        .cancelTextColor("#1571FA")//取消按钮文字颜色
                        .cancelText("取消")//取消按钮文字
                        .cancelTextSize(16)//取消按钮文字大小
                        .setCityWheelType(CityConfig.WheelType.PRO_CITY_DIS)//显示类，只显示省份一级，显示省市两级还是显示省市区三级
                        .showBackground(true)//是否显示半透明背景
                        .visibleItemsCount(5)//显示item的数量
                        .province("北京市")//默认显示的省份
                        .city("")//默认显示省份下面的城市
                        .district("海淀区")//默认显示省市下面的区县数据
                        .provinceCyclic(false)//省份滚轮是否可以循环滚动
                        .cityCyclic(false)//城市滚轮是否可以循环滚动
                        .districtCyclic(false)//区县滚轮是否循环滚动
                        .setCustomItemLayout(R.layout.item_city)//自定义item的布局
                        .setCustomItemTextViewId(R.id.item_city_name_tv)//自定义item布局里面的textViewid
                        .drawShadows(false)//滚轮不显示模糊效果
                        .setLineColor("#03a9f4")//中间横线的颜色
                        .setLineHeigh(5)//中间横线的高度
                        .setShowGAT(true)//是否显示港澳台数据，默认不显示
                        .build();
                //添加配置
                mPicker.setConfig(cityConfig);
                //显示
                mPicker.showCityPicker();
                //监听选择点击事件及返回结果
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        super.onSelected(province, city, district);
                        provinceName = province.getName();
                        cityname = city.getName();
                        districtName = district.getName();
                        //为TextView赋值
                        mEtArea.setText(province.getName() + " " + city.getName() + " " + district.getName());

                    }

                    @Override
                    public void onCancel() {
                        super.onCancel();
                    }
                });
                break;
        }
    }
}
