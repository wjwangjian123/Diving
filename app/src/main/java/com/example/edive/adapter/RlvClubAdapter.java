package com.example.edive.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.bean.FuBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvClubAdapter extends RecyclerView.Adapter<RlvClubAdapter.ViewHolder> {
    private final FragmentActivity activity;
    private final ArrayList<FuBean.ResultsBean> list;

    public RlvClubAdapter(FragmentActivity activity, ArrayList<FuBean.ResultsBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_club, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(activity).load(list.get(i).getUrl()).into(viewHolder.mIvClubPhoto);
        viewHolder.mTvClubName.setText("俱乐部名称");
        viewHolder.mTvClubNameUser.setText("用户昵称");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_club_photo)
        ImageView mIvClubPhoto;
        @BindView(R.id.tv_club_name)
        TextView mTvClubName;
        @BindView(R.id.tv_club_name_user)
        TextView mTvClubNameUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
