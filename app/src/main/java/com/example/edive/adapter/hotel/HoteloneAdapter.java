package com.example.edive.adapter.hotel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.edive.R;
import com.example.edive.activity.hotel.HotelReservationActivity;

import butterknife.ButterKnife;

public class HoteloneAdapter extends RecyclerView.Adapter<HoteloneAdapter.HoteloneViewHolder>{
    private Context mContext;
    public HoteloneAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HoteloneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_hotelone, viewGroup,false);
        return new HoteloneViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HoteloneViewHolder hoteloneViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class HoteloneViewHolder extends RecyclerView.ViewHolder{

        public HoteloneViewHolder(@NonNull View itemView) {
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
