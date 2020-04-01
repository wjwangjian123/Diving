package com.example.edive.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.edive.R;
import com.example.edive.bean.Integralfishbean;
import com.example.edive.design.GlideRoundTransform;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvIntregralFishAdapter extends RecyclerView.Adapter<RlvIntregralFishAdapter.ViewHolder> {
    private final FragmentActivity activity;
    private final ArrayList<Integralfishbean.ResultBean.ListBean> list;
    private setonclick time;

    public RlvIntregralFishAdapter(FragmentActivity activity, ArrayList<Integralfishbean.ResultBean.ListBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_finshintegral, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mTvTextName.setText(list.get(i).getProductName());
        viewHolder.mTvOrderNum.setText("兑换时间："+list.get(i).getPaymentTime());
        viewHolder.mTvNum.setText("数量："+list.get(i).getProductQuantity());
        viewHolder.mTvGuige.setText("规格:"+list.get(i).getSizeSpec());
        Glide.with(activity).load(list.get(i).getProductPic()).transform(new CenterCrop(),new GlideRoundTransform(activity,6)).placeholder(R.mipmap.bg).into(viewHolder.mIvShow);
        double productPrice = list.get(i).getTotalAmount();
        String s = String.valueOf(productPrice);
        String s1 = subZeroAndDot(s);
        viewHolder.mTvJif.setText("-"+s1+"积分");
        viewHolder.mRlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time != null) {
                    time.setonclick(i);
                }
            }
        });
    }
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_item)
        RelativeLayout mRlItem;
        @BindView(R.id.iv_show)
        ImageView mIvShow;
        @BindView(R.id.tv_text_name)
        TextView mTvTextName;
        @BindView(R.id.tv_num)
        TextView mTvNum;
        @BindView(R.id.tv_guige)
        TextView mTvGuige;
        @BindView(R.id.tv_jif)
        TextView mTvJif;
        @BindView(R.id.rl)
        RelativeLayout mRl;
        @BindView(R.id.tv_order_num)
        TextView mTvOrderNum;
        @BindView(R.id.rl_one)
        RelativeLayout mRlOne;
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
