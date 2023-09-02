package com.example.healthlink.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthlink.Domains.ScreenItem;
import com.example.healthlink.R;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingAdapterViewHolder> {

    private List<ScreenItem> onBoardingItems;

    public OnBoardingAdapter(List<ScreenItem> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public OnBoardingAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingAdapterViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding,parent,false
                )
        );
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingAdapterViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItems.get(position));
    }

    class OnBoardingAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView title,description;
        private ImageView screenImg;
        public OnBoardingAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            screenImg = itemView.findViewById(R.id.iv_intro);
            title = itemView.findViewById(R.id.tv_intro_title);
            description = itemView.findViewById(R.id.tv_intro_description);
        }

        void setOnBoardingData(ScreenItem screenItem){
            title.setText(screenItem.getTitle());
            description.setText(screenItem.getDescription());
            screenImg.setImageResource(screenItem.getScreenImg());

            }

        }

}
