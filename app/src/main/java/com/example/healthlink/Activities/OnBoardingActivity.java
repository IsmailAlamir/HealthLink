package com.example.healthlink.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.healthlink.Adapter.IntroViewPagerAdapter;
import com.example.healthlink.Domains.ScreenItem;
import com.example.healthlink.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager2 screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bording);
        tabIndicator = findViewById(R.id.tab_indicator);


        List<ScreenItem> screenItems = new ArrayList<>();
        screenItems.add(new ScreenItem("Welcome to HealthLink !","Managing your health has never been easier.",R.drawable.docs_1));
        screenItems.add(new ScreenItem("Discover HealthLink's Features","stay connected with your healthcare providers, schedule appointments, and access your medical records—all in one place",R.drawable.docs_2));
        screenItems.add(new ScreenItem("Experience the Benefits","Ready to experience the transformation? Create an account or log in to start your journey with HealthLink.",R.drawable.doc_office));

        screenPager =findViewById(R.id.vp_screen);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, screenItems);
        screenPager.setAdapter(introViewPagerAdapter);
        tabIndicator.setupWithViewPager(screenPager);



    }
}


