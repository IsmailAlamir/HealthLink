package com.example.healthlink.EngagingHome;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthlink.R;

import java.util.List;

public class TrendAdapter extends RecyclerView.Adapter<TrendAdapter.ViewHolder> {
    private List<Trend> trendItems;

    public TrendAdapter(List<Trend> trendItems) {
        this.trendItems = trendItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_trend,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setViewHolderData(trendItems.get(position));

    }

    @Override
    public int getItemCount() {
        return trendItems.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        private TextView title, subTitle;
        private ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_trend_title);
            subTitle=itemView.findViewById(R.id.tv_trend_subtitle);
            image=itemView.findViewById(R.id.iv_trend_pic);

        }

        void setViewHolderData(Trend trendItem){
            title.setText(trendItem.getTitle());
            subTitle.setText(trendItem.getSubTitle());
            image.setImageResource(trendItem.getImage());
        }


    }
}
