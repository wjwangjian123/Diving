package com.example.edive.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.activity.PublishDynamicActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvPublishAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final PublishDynamicActivity activity;
    private final ArrayList<String> list;
    private static final int ITEM_TYPE_ONE = 0x00001;
    private static final int ITEM_TYPE_TWO = 0x00002;
    private setonclick time;

    public RlvPublishAdapter(PublishDynamicActivity activity, ArrayList<String> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        viewGroup.setPadding(20, 0, 20, 0);
        switch (i) {
            case ITEM_TYPE_ONE:
                return new MyHolder(LayoutInflater.from(activity).inflate(R.layout.item_publishphoto, null));
            case ITEM_TYPE_TWO:
                return new MyTWOHolder(LayoutInflater.from(activity).inflate(R.layout.item_publishphoto_two, null));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof MyHolder) {
            bindItemMyHolder((MyHolder) viewHolder, position);
        } else if (viewHolder instanceof MyTWOHolder) {
            bindItemTWOMyHolder((MyTWOHolder) viewHolder, position);
        }
    }

    private void bindItemTWOMyHolder(final MyTWOHolder holder, final int position) {
        if (listSize() >= 9) {//集合长度大于等于9张时，隐藏 图片
            holder.mIvadd.setVisibility(View.GONE);
        }
        holder.mIvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择图片
                time.setonclick(position);
            }
        });
    }

    private void bindItemMyHolder(MyHolder holder, int position) {

        Glide.with(activity).load(list.get(position)).into(holder.mIvShow);

    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return ITEM_TYPE_TWO;
        } else {
            return ITEM_TYPE_ONE;
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_show)
        ImageView mIvShow;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyTWOHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_add)
        ImageView mIvadd;
        public MyTWOHolder(View itemView) {
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
    //得到集合长度
    public int listSize() {
        int size = list.size();
        return size;
    }
}
