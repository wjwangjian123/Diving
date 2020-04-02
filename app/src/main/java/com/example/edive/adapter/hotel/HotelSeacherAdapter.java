package com.example.edive.adapter.hotel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edive.R;

import butterknife.ButterKnife;

public class HotelSeacherAdapter extends RecyclerView.Adapter<HotelSeacherAdapter.HotelSeacherViewHolder>{
    private Context mContext;
    public HotelSeacherAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HotelSeacherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_hotel_seacher, viewGroup,false);
        return new HotelSeacherViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelSeacherViewHolder hotelSeacherViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class HotelSeacherViewHolder extends RecyclerView.ViewHolder {


        public HotelSeacherViewHolder(@NonNull View itemView) {
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
