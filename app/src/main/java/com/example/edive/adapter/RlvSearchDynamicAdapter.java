package com.example.edive.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edive.R;
import com.example.edive.activity.ConversationActivity;
import com.example.edive.bean.SearchDynamicBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvSearchDynamicAdapter extends RecyclerView.Adapter<RlvSearchDynamicAdapter.ViewHolder> {
    private final ConversationActivity activity;
    private final ArrayList<SearchDynamicBean.DataBean> list;
    private setonclcik time;

    public RlvSearchDynamicAdapter(ConversationActivity activity, ArrayList<SearchDynamicBean.DataBean> list) {

        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_search_data, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mTvName.setText(list.get(i).getTopicName());
        viewHolder.mLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time != null) {
                    time.setonclick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.ll)
        LinearLayout mLl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface setonclcik{
        void setonclick(int pos);
    }
    public void setonclick(setonclcik time){

        this.time = time;
    }
}
