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
import com.example.edive.activity.CommentActivity;
import com.example.edive.bean.LikeListBean;
import com.example.edive.design.RoundOrCircleImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvCommentListAdapter extends RecyclerView.Adapter<RlvCommentListAdapter.ViewHolder> {
    private final CommentActivity activity;
    private final ArrayList<LikeListBean.ResultBean.ListBean> list;
    private setonclick time;

    public RlvCommentListAdapter(CommentActivity activity, ArrayList<LikeListBean.ResultBean.ListBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_like_comment, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if (list.get(i).getIsRead() == 0) {
            viewHolder.mTvyuan.setVisibility(View.VISIBLE);
        }else {
            viewHolder.mTvyuan.setVisibility(View.GONE);
        }
        Glide.with(activity).load(list.get(i).getIcon()).error(R.mipmap.morentouxiang).placeholder(R.mipmap.morentouxiang).into(viewHolder.mIvShow);
        viewHolder.mTvName.setText(list.get(i).getNickName());
        viewHolder.mTvTime.setText(list.get(i).getCreatedTime());
        viewHolder.mTvText.setText("“"+list.get(i).getContent()+"+”");
//        Glide.with(activity).load(list.get(i).)
        viewHolder.mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time != null) {
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
        @BindView(R.id.iv_show)
        RoundOrCircleImage mIvShow;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_yuan)
        TextView mTvyuan;
        @BindView(R.id.rl)
        RelativeLayout mRl;
//        @BindView(R.id.iv_shows)
//        ImageView mIvshows;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface setonclick{
        void setonclick(int pos);
    }
    public void setonclick(setonclick time){

        this.time = time;
    }
}
