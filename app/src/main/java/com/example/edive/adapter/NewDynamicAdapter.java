package com.example.edive.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.edive.R;
import com.example.edive.bean.FuBean;

import java.util.ArrayList;

public class NewDynamicAdapter extends RecyclerView.Adapter<NewDynamicAdapter.ItemViewHolder> {
    private FragmentActivity activity;
    private final ArrayList<FuBean.ResultsBean> list;
    public static final int TYPE_ONE = 0x101;
    public static final int TYPE_TWO = 0x102;

    public NewDynamicAdapter(FragmentActivity activity, ArrayList<FuBean.ResultsBean> list) {
        this.activity = activity;
        this.list = list;
    }
    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? TYPE_ONE : TYPE_TWO;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = null;
        switch (i) {
            case TYPE_ONE:
                itemView = getItemView(viewGroup, R.layout.item_rc_one_type);
                break;
            case TYPE_TWO:
                itemView = getItemView(viewGroup, R.layout.item_rc_two_type);
                break;
        }
        return new ItemViewHolder(itemView);
    }

    private View getItemView(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        return LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        if (position > 0) {
            holder.itemView.setPadding(0,
                    (int) (holder.itemView.getContext().getResources().getDisplayMetrics().density * 4),
                    0, 0);
        }
        holder.setData(position);
    }


    @Override
    public int getItemCount() {
        return (list.size() + 8) / 9;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ArrayList<ImageView> mImageViews;
        private int spareItemNum;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViews = new ArrayList<>();
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView1));
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView2));
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView3));
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView4));
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView5));
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView6));
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView7));
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView8));
            mImageViews.add(itemView.<ImageView>findViewById(R.id.imageView9));
            for (ImageView imageView : mImageViews) {
                imageView.setOnClickListener(this);
            }
        }

        public void setData(int position) {
            position *= 9;
            for (ImageView imageView : mImageViews) {
                setImage(position++, imageView);
            }
        }

        private void setImage(int position, ImageView image) {
            if (position < list.size() && position != -1) {
                Glide.with(image.getContext()).load(list.get(position).getUrl()).into(image);
            } else {
                image.setImageResource(0);
                if (spareItemNum == 0) {
                    spareItemNum = getItemCount() * 9 - position;
                }
                if (--spareItemNum < 3) {
                    image.setVisibility(View.GONE);
                } else {
                    image.setVisibility(View.INVISIBLE);
                }
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition() * 9;//这里就是正常的position 可以拿来获取数据
            list.get(position);

            //下面这是判断是哪个view被点击了
            for (int i = 0 ; i < mImageViews.size() ; i++) {
                if (mImageViews.get(i) == v) {
                    if (onItemClickListener != null) {
                        list.get(position + i);//这里获取的就是图片的对象
                        onItemClickListener.onItemClick(
                                String.format("第%d个Item的第%d个image position = %d",
                                        getAdapterPosition() + 1, i + 1,
                                        position + i));
                    }
                }
            }
        }
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(String s);
    }
}
