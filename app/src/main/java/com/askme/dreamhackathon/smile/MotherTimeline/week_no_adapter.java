package com.askme.dreamhackathon.smile.MotherTimeline;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.askme.dreamhackathon.smile.R;

import java.util.List;

/**
 * Created by ASUS on 3/30/2016.
 */
public class week_no_adapter extends RecyclerView.Adapter<week_no_adapter.WeekViewHolder>{

    private List<Adapter_details> list;

    public week_no_adapter(List<Adapter_details> weeklist) {
        this.list = weeklist;
    }

    @Override
    public WeekViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new WeekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeekViewHolder holder, int position) {
        Adapter_details details = list.get(position);
        holder.week.setText(details.week_no);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WeekViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        protected TextView week;

        WeekViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            week = (TextView)itemView.findViewById(R.id.week);
        }
    }


}
