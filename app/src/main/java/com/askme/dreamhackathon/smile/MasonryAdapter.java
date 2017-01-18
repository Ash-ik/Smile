package com.askme.dreamhackathon.smile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView> {
    private Context context;
    OnItemClickListener mItemClickListener;
    int[] imgList = {R.drawable.home1, R.drawable.home2, R.drawable.home3, R.drawable.home4,
            R.drawable.home5};

    String[] nameList = {"Mother and Children Health", "Health care in time of Pregnancy", "Baby Health", "Newborn Baby Vaccine", "Child Health care"};

    public MasonryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        MasonryView masonryView = new MasonryView(layoutView);
        return masonryView;
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
        holder.imageView.setImageResource(imgList[position%5]);
        holder.textView.setText(nameList[position%5]);


    }

    @Override
    public int getItemCount() {
        return nameList.length;
    }

    public class MasonryView extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView textView;

        public MasonryView(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.itemTitle);
            imageView = (ImageView) itemView.findViewById(R.id.ivFeedCenter);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

