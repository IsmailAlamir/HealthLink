package com.example.healthlink.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.healthlink.Domains.ScreenItem;
import com.example.healthlink.Fragments.IntroFragment;

import java.util.List;

public class IntroViewPagerAdapter extends FragmentStateAdapter {

    private List<ScreenItem> screenItems;

    public IntroViewPagerAdapter(FragmentActivity fragmentActivity, List<ScreenItem> screenItems) {
        super(fragmentActivity);
        this.screenItems = screenItems;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return IntroFragment.newInstance(screenItems.get(position));
    }

    @Override
    public int getItemCount() {
        return screenItems.size();
    }
}
