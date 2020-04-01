package com.example.edive.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.bean.FollowListBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvTopicLikeListAdapter extends RecyclerView.Adapter<RlvTopicLikeListAdapter.ViewHolder> {
    private final FragmentActivity activity;
    private final ArrayList<FollowListBean.ResultBean.ListBean> list;
    private setDelItemListener time;
    private setonclick times;

    public RlvTopicLikeListAdapter(FragmentActivity activity, ArrayList<FollowListBean.ResultBean.ListBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_topic_list, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(activity).load(list.get(i).getTopicPicture()).placeholder(R.mipmap.bg).into(viewHolder.mIvShow);
        viewHolder.mTvName.setText(list.get(i).getTopicName());
        viewHolder.mTvDatas.setText(list.get(i).getTopicNum()+"动态");

        viewHolder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setDelItemListener(i);
            }
        });
        viewHolder.mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (times != null) {
                    times.setonclick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_show)
        ImageView mIvShow;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_datas)
        TextView mTvDatas;
        @BindView(R.id.btnDelete)
        Button mBtnDelete;
        @BindView(R.id.rl)
        RelativeLayout mRl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface setDelItemListener{
        void setDelItemListener(int pos);
    }
    public void setDelItemListener(setDelItemListener time){

        this.time = time;
    }
    public interface setonclick{
        void setonclick(int pos);
    }
    public void setonclick(setonclick times){

        this.times = times;
    }
}
