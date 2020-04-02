package com.example.edive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.edive.R;
import com.example.edive.activity.DivingCertificateActivity;
import com.example.edive.activity.DivingDestiActivity;
import com.example.edive.activity.TopicDetailsActivity;
import com.example.edive.bean.BannerInfo;
import com.example.edive.design.GlideRoundTransform;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RlvHomesAdapter extends RecyclerView.Adapter<RlvHomesAdapter.ViewHolder>{


    private final FragmentActivity activity;
    private final ArrayList<BannerInfo.DataBean> list;

    public RlvHomesAdapter(FragmentActivity activity, ArrayList<BannerInfo.DataBean> list) {

        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public RlvHomesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View inflate = LayoutInflater.from(activity).inflate(R.layout.item_ban, null);
            return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RlvHomesAdapter.ViewHolder viewHolder, int i) {
            viewHolder.bann.setImages(list).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerInfo.DataBean bean = (BannerInfo.DataBean) path;
                    Glide.with(context).load(bean.getPic()).transform(new CenterCrop(),new GlideRoundTransform(activity,10)).into(imageView);
                }
            }).start();
            viewHolder.bann.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    int linkType = list.get(position).getLinkType();
                    String url = list.get(position).getUrl();
                    int isJump = list.get(position).getIsJump();
                    Integer integer = Integer.valueOf(url);
                    if (isJump == 0) {

                    }else if(isJump==1){
                        if (linkType == 1) {
                            Intent intent = new Intent(activity, DivingCertificateActivity.class);
                            intent.putExtra("id", integer);
                            activity.startActivity(intent);
                        } else if (linkType == 2) {
                            Intent intent = new Intent(activity, TopicDetailsActivity.class);
                            intent.putExtra("id", integer);
                            activity.startActivity(intent);
                        } else if (linkType == 3) {

                        } else if (linkType == 4) {
                            Intent intent = new Intent(activity, DivingDestiActivity.class);
                            intent.putExtra("id", integer);
                            activity.startActivity(intent);
                        }
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private final Banner bann;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bann = itemView.findViewById(R.id.bann);
        }
    }
}
