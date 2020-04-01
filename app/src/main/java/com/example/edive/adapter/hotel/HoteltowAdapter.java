package com.example.edive.adapter.hotel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edive.R;

import butterknife.ButterKnife;

public class HoteltowAdapter extends RecyclerView.Adapter<HoteltowAdapter.HoteltowViewHolder>{
    private Context mContext;
    public HoteltowAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public HoteltowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_hoteltow, viewGroup,false);
        return new HoteltowViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HoteltowViewHolder hoteltowViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class HoteltowViewHolder extends RecyclerView.ViewHolder{

        public HoteltowViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    private ItemOnclikListener itemOnclikListener;

    public interface ItemOnclikListener {
        void setonclik(int postion);
    }
    public void setItemOnclikListener(ItemOnclikListener itemOnclikListener) {
        this.itemOnclikListener = itemOnclikListener;
    }
}
