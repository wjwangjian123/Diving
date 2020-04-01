package com.example.edive.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.bean.AllMessagerBean;
import com.example.edive.design.RoundOrCircleImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvAllraiseMessagerAdapter extends RecyclerView.Adapter<RlvAllraiseMessagerAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<AllMessagerBean.ResultBean.ListBean> list;
    private ArrayList<String> strList;
    private RlvMessagerPhotoAdapter photoAdapter;

    public RlvAllraiseMessagerAdapter(Context context, ArrayList<AllMessagerBean.ResultBean.ListBean> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_messager, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        strList = new ArrayList<>();
        String pic = list.get(i).getPic();
        String[] split = pic.split(",");
        for (int j = 0; j < split.length; j++) {
            strList.add(split[j]);
        }
        if (!TextUtils.isEmpty(pic)) {
            viewHolder.mRec.setVisibility(View.VISIBLE);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            viewHolder.mRec.setLayoutManager(gridLayoutManager);
            photoAdapter = new RlvMessagerPhotoAdapter(context, strList);
            viewHolder.mRec.setAdapter(photoAdapter);
            photoAdapter.setonclicksss(new RlvMessagerPhotoAdapter.setonclicksss() {
                @Override
                public void setonclicksss(int pos) {

                }
            });
        }else {
            viewHolder.mRec.setVisibility(View.GONE);
        }
        viewHolder.mTvName.setText(list.get(i).getNickName());
        viewHolder.mTvText.setText(list.get(i).getInfo());
        viewHolder.mTvTime.setText(list.get(i).getCreatedTime());
        Glide.with(context).load(list.get(i).getUserIcon()).placeholder(R.mipmap.morentouxiang).error(R.mipmap.morentouxiang).into(viewHolder.mIvShow);
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
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_text)
        TextView mTvText;
        @BindView(R.id.rec)
        RecyclerView mRec;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
