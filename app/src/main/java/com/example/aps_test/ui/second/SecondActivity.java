package com.example.aps_test.ui.second;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aps_test.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SecondActivity extends AppCompatActivity implements SecondContract.view{
    private SecondPresenter secondPresenter;
    private ViewPager2 viewViewPager;
    private TabLayout themeTabLayout;
    private String[] title= {"生產排程","當日進度表","訊息通知"};
    private Context context = this;

    private Button setupButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        secondPresenter = new SecondPresenter(this);

        viewViewPager = findViewById(R.id.second_view_vp);
        themeTabLayout = findViewById(R.id.second_theme_tl);

        setupButton = findViewById(R.id.prodcution_SetUp_btn);

        viewViewPager.setAdapter(new ViewPagerAdapter(this, this));
        new TabLayoutMediator(themeTabLayout, viewViewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                viewViewPager.setCurrentItem(tab.getPosition());
                tab.setText(title[position]);
            }
        }).attach();

    }

//    @Override
//    public void sample() {
//        TableLayout tableLayout = new TableLayout(this);
//        tableLayout.setColumnCollapsed(1, false);
//        tableLayout.setColumnCollapsed(2, false);
//    }
}