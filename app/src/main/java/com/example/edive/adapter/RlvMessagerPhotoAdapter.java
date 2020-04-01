package com.example.edive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.activity.ShowPhotoActivity;

import java.util.ArrayList;

public class RlvMessagerPhotoAdapter extends RecyclerView.Adapter<RlvMessagerPhotoAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<String> list;
    private setonclicksss timehh;

    public RlvMessagerPhotoAdapter(Context context, ArrayList<String> list) {

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(View.inflate(context, R.layout.more_image_adapter,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i)).into(viewHolder.mImageView);
        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timehh != null) {
                    timehh.setonclicksss(i);
                    Intent intent = new Intent(context, ShowPhotoActivity.class);
                    intent.putExtra("pos",i);
                    intent.putExtra("list",list);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
        }
    }
    public interface setonclicksss{
        void setonclicksss(int pos);
    }
    public void setonclicksss(setonclicksss timehh){

        this.timehh = timehh;
    }
}
