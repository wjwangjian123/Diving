package com.example.edive.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.edive.R;
import com.example.edive.activity.MycertificateActivity;
import com.example.edive.bean.CreaterBean;
import com.example.edive.design.GlideRoundTransform;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvCreaterAdapter extends RecyclerView.Adapter<RlvCreaterAdapter.ViewHolder> {
    private final MycertificateActivity activity;
    private final ArrayList<CreaterBean.DataBean> list;
    private setonclick time;
    private setonclicks times;

    public RlvCreaterAdapter(MycertificateActivity activity, ArrayList<CreaterBean.DataBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_creater, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//        Glide.with(activity).load(list.get(i).getPictures()).error(R.mipmap.shangchuan).transform(new CenterCrop()).into(viewHolder.mIvShow);
        viewHolder.mTvText.setText(list.get(i).getCertificateName() + "证书");
        String status = list.get(i).getStatus();
        if (TextUtils.isEmpty(status)) {
            viewHolder.mIvShow.setVisibility(View.VISIBLE);
            viewHolder.mIvAdd.setVisibility(View.GONE);
            Glide.with(activity).load(list.get(i).getPictures()).transform(new CenterCrop(), new GlideRoundTransform(activity, 6)).into(viewHolder.mIvShow);
        }else {


            if (Integer.valueOf(status) == 0) {
                viewHolder.mTvTexte.setTextColor(activity.getResources().getColor(R.color.blue));
                viewHolder.mTvTexte.setText("已上传，待审核");
            } else if (Integer.valueOf(status) == 1) {
                viewHolder.mTvTexte.setTextColor(activity.getResources().getColor(R.color.blue));
                viewHolder.mTvTexte.setText("审核通过");
            } else if (Integer.valueOf(status) == 2) {
                viewHolder.mTvTexte.setTextColor(activity.getResources().getColor(R.color.blue));
                viewHolder.mTvTexte.setText("审核被拒绝" + list.get(i).getReason());
            }
        }
        if (!TextUtils.isEmpty(list.get(i).getPictures())) {
            viewHolder.mIvShow.setVisibility(View.VISIBLE);
            viewHolder.mIvAdd.setVisibility(View.GONE);
            Glide.with(activity).load(list.get(i).getPictures()).transform(new CenterCrop(),new GlideRoundTransform(activity,6)).into(viewHolder.mIvShow);
        }else {
            viewHolder.mIvShow.setVisibility(View.GONE);
            viewHolder.mIvAdd.setVisibility(View.VISIBLE);
            Glide.with(activity).load(R.mipmap.shangchuan).transform(new CenterCrop(),new GlideRoundTransform(activity,6)).into(viewHolder.mIvAdd);
        }
        viewHolder.mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time != null) {
                    time.setonclick(i);
                }
            }
        });
        viewHolder.mIvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (times != null) {
                    times.setonclicks(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.iv_add)
        ImageView mIvAdd;
        @BindView(R.id.iv_show)
        ImageView mIvShow;
        @BindView(R.id.tv_texte)
        TextView mTvTexte;

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
    public interface setonclicks{
        void setonclicks(int pos);
    }
    public void setonclicks(setonclicks times){


        this.times = times;
    }
}
