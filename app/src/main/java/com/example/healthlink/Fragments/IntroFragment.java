package com.example.healthlink.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthlink.Domains.ScreenItem;
import com.example.healthlink.R;

import java.io.Serializable;

public class IntroFragment extends Fragment {

    private static final String ARG_SCREEN_ITEM = "screen_item";

    public static IntroFragment newInstance(ScreenItem screenItem) {
        IntroFragment fragment = new IntroFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCREEN_ITEM, screenItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        ImageView screenImg = view.findViewById(R.id.iv_intro);
        TextView title = view.findViewById(R.id.tv_intro_title);
        TextView description = view.findViewById(R.id.tv_intro_description);

        ScreenItem screenItem = (ScreenItem) getArguments().getSerializable(ARG_SCREEN_ITEM);
        if (screenItem != null) {
            title.setText(screenItem.getTitle());
            description.setText(screenItem.getDescription());
            screenImg.setImageResource(screenItem.getScreenImg());
        }

        return view;
    }
}