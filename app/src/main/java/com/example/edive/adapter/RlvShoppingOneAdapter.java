package com.example.edive.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.edive.R;
import com.example.edive.bean.DivingBean;
import com.example.edive.design.GlideRoundTransform;
import com.example.edive.design.RoundImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvShoppingOneAdapter extends RecyclerView.Adapter<RlvShoppingOneAdapter.ViewHolder> {
    private Activity activity;
    private final ArrayList<DivingBean.ResultBean.ListBean> list;
    private setonclick times;

    public RlvShoppingOneAdapter(FragmentActivity activity, ArrayList<DivingBean.ResultBean.ListBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_shopping_top, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(activity).load(list.get(i).getCoachIcon()).into(viewHolder.mIvCoShow);
        Glide.with(activity).load(list.get(i).getImage()).transform(new CenterCrop(),new GlideRoundTransform(activity,5)).into(viewHolder.mIvShow);
        viewHolder.mTvCoName.setText(list.get(i).getCoachName());
        viewHolder.mTvCoNum.setText("￥"+list.get(i).getPrice());
        viewHolder.mTvText.setText(list.get(i).getTitle());
        viewHolder.mTvCoDj.setText("V"+list.get(i).getCoachGrade());
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.iv_co_show)
        RoundImage mIvCoShow;
        @BindView(R.id.tv_co_name)
        TextView mTvCoName;
        @BindView(R.id.tv_co_dj)
        TextView mTvCoDj;
        @BindView(R.id.tv_co_num)
        TextView mTvCoNum;
        @BindView(R.id.ll)
        LinearLayout item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface setonclick{
        void setonclick(int pos);
    }
    public void setonclcik(setonclick times){
        this.times = times;
    }
}
