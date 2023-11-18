package com.example.healthlink.EngagingHome;

import static com.example.healthlink.profileSetting.ProfileSettingActivity.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.healthlink.AppDatabase;
import com.example.healthlink.OnBoardingItem;
import com.example.healthlink.R;

import java.util.ArrayList;
import java.util.List;

public class EngagingHomeActivity extends AppCompatActivity {

    RecyclerView trendRecyclerView;
    TrendAdapter trendAdapter;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engaging_home);

        initRecycleView();
        setUserName();
    }


    private void setUserName() {
        name= findViewById(R.id.tv_name);
        name.setText(user.firstName);
    }

    private void initRecycleView() {
        List<Trend> trends=new ArrayList<>();
        trends.add(new Trend("Healthy Eating Habits","Discover the secrets to maintaining a balanced diet and making healthier food choices.",R.drawable.trend_1));
        trends.add(new Trend("Fitness Workouts","Stay active and fit with home-based workout routines. Expert advice to keep your body in shape.",R.drawable.trend_2));
        trends.add(new Trend("Mindfulness Meditation","Find inner peace and reduce stress through mindfulness meditation. Learn meditation techniques, and stories of personal transformation.",R.drawable.trend_3));
        trends.add(new Trend("Hiking Adventures","Embark on breathtaking hiking journeys around the world. Get inspired by stunning landscapes, travel guides, and hiking enthusiasts' experiences.",R.drawable.trend_4));
        trends.add(new Trend("AI in Healthcare","Explore how artificial intelligence is revolutionizing the medical field.",R.drawable.trend_5));
        trendRecyclerView=findViewById(R.id.rv_trends);
        trendRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        trendAdapter=new TrendAdapter(trends);
        trendRecyclerView.setAdapter(trendAdapter);

    }
}