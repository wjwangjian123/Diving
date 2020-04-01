package com.example.edive.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.fragment.ClubFragment;
import com.example.edive.fragment.HomesFragment;
import com.example.edive.fragment.PersonalFragment;
import com.example.edive.fragment.ShoppingFragment;
import com.example.edive.frame.BaseApplication;
import com.example.edive.utils.AppConstants;
import com.example.edive.utils.StatusBarUtil;
import com.example.edive.utils.ToastUtil;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar mTb;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.shop_add)
    ImageView add;
    @BindView(R.id.toolebar_title)
    TextView titletv;
    @BindView(R.id.home_fb_iv)//发布
            ImageView mHomeFbIv;
    @BindView(R.id.home_fragment)
    FrameLayout mHomeFragment;
    @BindView(R.id.home)
    RadioButton home;
    @BindView(R.id.circle)
    RadioButton circle;
    @BindView(R.id.shop_car)
    RadioButton shopCar;
    @BindView(R.id.order)
    RadioButton order;
    @BindView(R.id.mine)
    RadioButton mine;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.rl_nav)
    RelativeLayout rlNav;
    @BindView(R.id.iv_show)
    ImageView mIvShow;
    @BindView(R.id.iv_bg)
    ImageView mIvBg;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.iv_xiaoxi)
    ImageView mIvXiaoxi;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private MenuItem item;
    private long exittime;
    private PopupWindow popupWindow;
    private HomesFragment homesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        initFragment();
        //初始化页面管理类
        manager = getSupportFragmentManager();
        addHomeFragment();
        initListener();
    }

    protected void setStatusBar() {
        //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近。这样就可以实现开头说的第二种情况的沉浸式状态栏了
        StatusBarUtil.setTranslucentStatus(this);
    }

    private void addHomeFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.home_fragment, fragments.get(0));
        transaction.commit();
    }

    private void initFragment() {
        homesFragment = new HomesFragment();
        ClubFragment clubFragment = new ClubFragment();
        ShoppingFragment shoppingFragment = new ShoppingFragment();
        PersonalFragment personalFragment = new PersonalFragment();

        fragments = new ArrayList<>();
        fragments.add(homesFragment);
        fragments.add(clubFragment);
        fragments.add(shoppingFragment);
        fragments.add(personalFragment);
    }

    private void initView() {

        mIvShow.setVisibility(View.GONE);
        mIvBg.setVisibility(View.GONE);
        mTb.setTitle("");
        titletv.setText(R.string.homepage);
        setSupportActionBar(mTb);//支持Toolbar
        mSearch.setVisibility(View.VISIBLE);


        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("俱乐部");
        titles.add("产品");
        titles.add("我的");
    }

    private int mLastType = 0;

    private void switchFragment(int type) {
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = fragments.get(type);
        //显示
        if (!fragment.isAdded()) {
            transaction.add(R.id.home_fragment, fragment);
        }
        //隐藏
        Fragment lastFragment = fragments.get(mLastType);
        transaction.hide(lastFragment).show(fragment).commit();
        //记录当前选中位置
        mLastType = type;
        //设置标题
        titletv.setText(titles.get(type));

        if (type == AppConstants.TYPE_CULB) {
            mSearch.setVisibility(View.GONE);
            add.setVisibility(View.VISIBLE);
        } else {
            add.setVisibility(View.GONE);
        }
        //设置搜索框是否显示
        if (type == AppConstants.TYPE_HOME ) {
            mSearch.setVisibility(View.VISIBLE);
        } else if (type == AppConstants.TYPE_PERSONAL||type == AppConstants.TYPE_SHOPPING) {
//            item.setVisible(false);
            mSearch.setVisibility(View.GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @OnClick({R.id.home, R.id.circle, R.id.shop_car, R.id.order, R.id.mine, R.id.rg, R.id.home_fb_iv, R.id.search, R.id.iv_setting, R.id.iv_xiaoxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home:
                home.setChecked(true);
                switchFragment(AppConstants.TYPE_HOME);
                mTb.setBackgroundColor(getResources().getColor(R.color.Setting_bg));
                mIvShow.setVisibility(View.GONE);
                mIvBg.setVisibility(View.GONE);
                mIvSetting.setVisibility(View.GONE);
                mIvXiaoxi.setVisibility(View.GONE);
                home.setText("首 页");
                circle.setText("");
                order.setText("");
                mine.setText("");
                break;
            case R.id.circle:
                switchFragment(AppConstants.TYPE_CULB);
                mTb.setBackgroundColor(getResources().getColor(R.color.Setting_bg));
                mIvShow.setVisibility(View.GONE);
                mIvBg.setVisibility(View.GONE);
                mIvSetting.setVisibility(View.GONE);
                mIvXiaoxi.setVisibility(View.GONE);
                home.setText("");
                circle.setText("俱乐部");
                order.setText("");
                mine.setText("");
                break;
            case R.id.order:
                switchFragment(AppConstants.TYPE_SHOPPING);
                mTb.setBackgroundColor(getResources().getColor(R.color.Setting_bg));
                mIvShow.setVisibility(View.GONE);
                mIvBg.setVisibility(View.GONE);
                mIvSetting.setVisibility(View.GONE);
                mIvXiaoxi.setVisibility(View.GONE);
                home.setText("");
                circle.setText("");
                order.setText("产 品");
                mine.setText("");
                break;
            case R.id.mine:
                switchFragment(AppConstants.TYPE_PERSONAL);
                mIvShow.setVisibility(View.VISIBLE);
                mIvBg.setVisibility(View.VISIBLE);
                mIvSetting.setVisibility(View.GONE);
                mIvXiaoxi.setVisibility(View.VISIBLE);
                mTb.setBackgroundColor(getResources().getColor(R.color.mycolor));
                home.setText("");
                circle.setText("");
                order.setText("");
                mine.setText("我 的");
                break;
            case R.id.home_fb_iv:
                initpopo();
                break;
            case R.id.search:
                Intent intent = new Intent(this, SearchActivity.class);
                if ( mLastType == AppConstants.TYPE_SHOPPING) {
                intent.putExtra("id",0);

                } else if (mLastType == AppConstants.TYPE_HOME ) {
                    intent.putExtra("id",1);
                }
                startActivity(intent);
                break;
            case R.id.shop_car:
                break;
            case R.id.rg:
                break;
            case R.id.iv_setting:
                startActivity(new Intent(HomeActivity.this,SettingActivity.class));
                break;
            case R.id.iv_xiaoxi:
                startActivity(new Intent(HomeActivity.this,MessageActivity.class));
                break;
        }
    }

    private void initpopo() {
        View inflate1 = LayoutInflater.from(this).inflate(R.layout.layout_popo, null);
        ImageView iv_back = inflate1.findViewById(R.id.iv_back);
        TextView tv_photo = inflate1.findViewById(R.id.tv_photo);
        TextView tv_video = inflate1.findViewById(R.id.tv_video);
        TextView tv_log = inflate1.findViewById(R.id.tv_log);
        popupWindow = new PopupWindow(inflate1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        //手动设置 PopupWindow 响应返回键并关闭的问题
//        popupWindow.setFocusable(true);
//        popupWindow.setFocusableInTouchMode(true);  //为了保险起见加上这句
        popupWindow.setBackgroundDrawable(new BitmapDrawable()); // www.linuxidc.com响应返回键必须的语句

        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAtLocation(inflate1, Gravity.CENTER, 0, 0);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.7f;
        this.getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = HomeActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                HomeActivity.this.getWindow().setAttributes(lp);
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        tv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PublishDynamicActivity.class);
//                intent.putExtra("name","选择位置");
//                intent.putExtra("topicname","话题");
                startActivity(intent);
                popupWindow.dismiss();
            }
        });
        tv_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,PublishVideoActivity.class));
                popupWindow.dismiss();
            }
        });
    }

    private void initListener() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            //如果点击的是后退键  首先判断popupWindow是否能够后退   返回值是boolean类型的
            if (popupWindow != null) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
            if ((System.currentTimeMillis() - exittime) > 2000) {  //如果两次连续点击的时间>2s，就不执行操作
                ToastUtil.showShort("再按一次退出潜水伴侣");
                exittime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
