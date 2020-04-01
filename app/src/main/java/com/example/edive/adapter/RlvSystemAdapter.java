package com.example.edive.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.activity.SystemMessageActivity;
import com.example.edive.bean.LikeListBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvSystemAdapter extends RecyclerView.Adapter<RlvSystemAdapter.ViewHolder> {
    private final SystemMessageActivity activity;
    private final ArrayList<LikeListBean.ResultBean.ListBean> list;
    private setonclick time;

    public RlvSystemAdapter(SystemMessageActivity activity, ArrayList<LikeListBean.ResultBean.ListBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_system, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (list.get(i).getIsRead() == 0) {
            viewHolder.mTvyuan.setVisibility(View.VISIBLE);
        }else {
            viewHolder.mTvyuan.setVisibility(View.GONE);
        }
        viewHolder.mTvtitle.setText(list.get(i).getTitle());
        viewHolder.mTvText.setText(list.get(i).getContent());
        viewHolder.mTvTime.setText(list.get(i).getCreatedTime());
        viewHolder.mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time != null) {
//                    viewHolder.mTvyuan.setVisibility(View.GONE);
                    time.setonclick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView mIv;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.tv_title)
        TextView mTvtitle;
        @BindView(R.id.tv_yuan)
        TextView mTvyuan;
        @BindView(R.id.rl)
        RelativeLayout mRl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface setonclick{
        void setonclick(int pos);
    }
    public void setonclick(setonclick time){

        this.time = time;
    }
}
