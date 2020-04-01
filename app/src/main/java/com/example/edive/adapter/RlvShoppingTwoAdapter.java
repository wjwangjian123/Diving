package com.example.edive.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.bean.CertificcateBean;
import com.example.edive.design.GlideRoundTransform;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvShoppingTwoAdapter extends RecyclerView.Adapter<RlvShoppingTwoAdapter.ViewHolder> {
    private final Context activity;
    private final ArrayList<CertificcateBean.ResultBean> list;
    private setonclick time;

    public RlvShoppingTwoAdapter(Context activity, ArrayList<CertificcateBean.ResultBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_view, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.mTvEnName.setText(list.get(position).getEnglishName());
        viewHolder.mTvName.setText(list.get(position).getEnglishShorthand()+"潜水证书");
        viewHolder.mTvText.setText(list.get(position).getIntroduction());
        Glide.with(activity).load(list.get(position).getPic()).transform(new GlideRoundTransform(activity,8)).placeholder(R.mipmap.bg).into(viewHolder.mIvShow);
        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time != null) {
                    time.setonclick(position);
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
        @BindView(R.id.tv_en_name)
        TextView mTvEnName;
        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.rl)
        RelativeLayout item;
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
