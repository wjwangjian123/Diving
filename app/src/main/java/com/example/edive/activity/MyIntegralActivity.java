package com.example.edive.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.edive.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyIntegralActivity extends AppCompatActivity {

    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    @BindView(R.id.tv_integral2)
    TextView mTvIntegral2;
    @BindView(R.id.bt_bill)
    Button mBtBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//因为不是所有的系统都可以设置颜色的，在4.4以下就不可以。。有的说4.1，所以在设置的时候要检查一下系统版本是否是4.1以上
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.app_setting));
        }
        setContentView(R.layout.activity_my_integral);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_bill)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_bill:
                startActivity(new Intent(MyIntegralActivity.this,IntegralBillActivity.class));
                break;
        }
    }
}
