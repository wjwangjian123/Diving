package com.example.edive.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.activity.DivingCertificateActivity;
import com.example.edive.bean.CertificateProductBean;
import com.example.edive.design.RoundImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvCertificateMoreAdapter extends RecyclerView.Adapter<RlvCertificateMoreAdapter.ViewHolder> {
    private final DivingCertificateActivity activity;
    private final ArrayList<CertificateProductBean.ResultBean.UmsCoachVosBean> list;
    private setonclcik time;
    private int thisPosition;

    public RlvCertificateMoreAdapter(DivingCertificateActivity activity, ArrayList<CertificateProductBean.ResultBean.UmsCoachVosBean> list) {

        this.activity = activity;
        this.list = list;
    }

    public int getthisPosition() {
        return thisPosition;
    }

    //其次定义一个方法用来绑定当前参数值的方法
    //此方法是在调用此适配器的地方调用的，此适配器内不会被调用到
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_certificate, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        if (i == getthisPosition()) {
            //選中的顔色就設成了  黃色
            viewHolder.itemView.setBackgroundColor(activity.getResources().getColor(R.color.allbg));
        } else {
            //未選中的顔色 就設成了 白色
            viewHolder.itemView.setBackgroundColor(activity.getResources().getColor(R.color.findpassword_bg));
        }
        viewHolder.mTvCoDj.setText("V"+list.get(i).getCoachGrade());
        Glide.with(activity).load(list.get(i).getIcon()).into(viewHolder.mIvShow);
        viewHolder.mTvName.setText(list.get(i).getNickName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time != null) {
                    time.setonclick(i);
                }
            }
        });
//        if (i == postion) {
//            viewHolder.itemView.setBackgroundColor(Color.BLUE);
//        }else{
////            否则的话就全白色初始化背景
//            viewHolder.itemView.setBackgroundColor(Color.WHITE);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_show)
        RoundImage mIvShow;
        @BindView(R.id.tv_co_dj)
        TextView mTvCoDj;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.rl)
        RelativeLayout item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface setonclcik{
        void setonclick(int pos);
    }
    public void setonclick(setonclcik time){
        this.time = time;
    }
}
