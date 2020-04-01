package com.example.edive.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.activity.DivingCertificateActivity;
import com.example.edive.bean.ContentBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvDivingCetificateXcapAdapter extends RecyclerView.Adapter<RlvDivingCetificateXcapAdapter.ViewHolder> {
    private final Context activity;
    private final ArrayList<ContentBean> list;

    public RlvDivingCetificateXcapAdapter(Context activity, ArrayList<ContentBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_xcap, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (i == 0) {
            viewHolder.mViewvvv.setVisibility(View.GONE);
        }
        viewHolder.mTvDate.setText(list.get(i).getDate());
        viewHolder.mTvMessage.setText(list.get(i).getMessage());
        if (list.size() == i+1) {
            viewHolder.mView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_yuan)
        TextView mTvYuan;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.tv_message)
        TextView mTvMessage;
        @BindView(R.id.view)
        View mView;
        @BindView(R.id.vvvvv)
        View mViewvvv;
        @BindView(R.id.rl)
        RelativeLayout mRl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
