package com.example.healthlink.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healthlink.Adapter.OnBoardingAdapter;
import com.example.healthlink.Domains.ScreenItem;
import com.example.healthlink.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager2 onBoardingViewPager;
    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout onBoardingIndicators;
    private AppCompatButton startBtn;
    private TextView skipBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bording);


        setOnBoardingItems();

        onBoardingIndicators= findViewById(R.id.la_onboarding_indicators);
        onBoardingViewPager =findViewById(R.id.vp_screen);
        startBtn= findViewById(R.id.btn_onboarding_action);
        skipBtn= findViewById(R.id.skipBtn);
        onBoardingViewPager.setAdapter(onBoardingAdapter);


        setOnBoardingIndicators();
        setCurrentOnBoardingIndicators(0);

        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicators(position);
            }
        });
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onBoardingViewPager.getCurrentItem()+1<onBoardingAdapter.getItemCount()){
                    onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem()+1);
                } else {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }
        });

    }



    private void setOnBoardingItems(){

        List<ScreenItem> screenItems = new ArrayList<>();
        screenItems.add(new ScreenItem("Welcome to HealthLink !","Managing your health has never been easier.",R.drawable.docs_1));
        screenItems.add(new ScreenItem("Discover HealthLink's Features","stay connected with your healthcare providers, schedule appointments, and access your medical records—all in one place",R.drawable.docs_2));
        screenItems.add(new ScreenItem("Experience the Benefits","Ready to experience the transformation? Create an account or log in to start your journey with HealthLink.",R.drawable.doc_office));
        onBoardingAdapter = new OnBoardingAdapter(screenItems);
    }

    private void setOnBoardingIndicators(){
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i=0; i<indicators.length; i++){
            indicators[i]= new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            onBoardingIndicators.addView(indicators[i]);
        }

    }

    private void setCurrentOnBoardingIndicators(int index){
        int childCount = onBoardingIndicators.getChildCount();
        for (int i = 0; i<childCount; i++){
            ImageView imageView= (ImageView) onBoardingIndicators.getChildAt(i);
            if(i== index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_inactive));
            }

        }
        // n
        if (index == onBoardingAdapter.getItemCount()-1){
            startBtn.setText("Start");
        }else {
            startBtn.setText("Next");
        }
    }
}


