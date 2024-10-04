package com.example.aps_test.ui.scheduleResult;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aps_test.R;
import com.example.aps_test.ui.scheduleResult.resultFragment.ResultViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScheduleResultActivity extends AppCompatActivity implements ScheduleResultContract.view{
    private ImageView backImageView,leftImageView,rightImageView;
    private TextView themeTextView,searialNum1TextView,searialNum2TextView,companyNumTextView,
            thingTextView,onlineTimeTextView,quantityTextView,startTimeTextView,
            finishTimeTextView,jobTextView,stateTextView;
    private ViewPager2 viewViewPager;
    private TabLayout themeTabLayout;
    private ScheduleResultPresenter scheduleResultPresenter;

    private Context context= this;

    private String[] title= {"前關製令","本階製令","後關製令","裝配製令","銷售訂單"};
    private String[] theme= {"當日進度表","進度表查詢"};

    private int Index = 0;
    private int maxIndex = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_result);

        Intent intent = getIntent();
        int i = intent.getIntExtra("THEME_EXTRA",0);

        viewViewPager = findViewById(R.id.searchResult_view_vp);
        themeTabLayout = findViewById(R.id.searchResult_theme_tl);
        backImageView = findViewById(R.id.searchResult_back_iv);
        themeTextView = findViewById(R.id.searchResult_themename_tv);

        searialNum1TextView = findViewById(R.id.searchResult_searialNum1_tv);
        searialNum2TextView = findViewById(R.id.searchResult_searialNum2_tv);
        companyNumTextView = findViewById(R.id.searchResult_companyNum_tv);
        thingTextView = findViewById(R.id.searchResult_thing_tv);
        onlineTimeTextView = findViewById(R.id.searchResult_onlineTime_tv);
        quantityTextView = findViewById(R.id.searchResult_quantity_tv);
        startTimeTextView = findViewById(R.id.searchResult_startTime_tv);
        finishTimeTextView = findViewById(R.id.searchResult_finishTime_tv);
        jobTextView = findViewById(R.id.searchResult_job_tv);
        stateTextView = findViewById(R.id.searchResult_state_tv);

        leftImageView = findViewById(R.id.searchResult_left_iv);
        rightImageView = findViewById(R.id.searchResult_right_iv);

        themeTextView.setText(theme[i]);

        scheduleResultPresenter = new ScheduleResultPresenter(this);

        viewViewPager.setAdapter(new ResultViewPagerAdapter(this, this));
        viewViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // 控制按鈕可見性
                leftImageView.setVisibility(position == 0 ? View.INVISIBLE : View.VISIBLE);
                rightImageView.setVisibility(position == 4 ? View.INVISIBLE : View.VISIBLE);

                if(position == 0){
                    searialNum1TextView.setText("1MO1812040071");
                    searialNum2TextView.setText("1SO1811270009");
                    companyNumTextView.setText("M1-ATN260011-1");
                    thingTextView.setText("ATN260011 垃圾筒系統櫃門片 0.8*613.7*236.3mm-沖床組(6折)");
                    onlineTimeTextView.setText("預計上線：2018-12-05");
                    quantityTextView.setText("生產數量：3");
                    startTimeTextView.setText("計劃開始：15:30");
                    finishTimeTextView.setText("生產結束：15:45");
                    jobTextView.setText("一群-沖床");
                    stateTextView.setText("結案");
                    stateTextView.setTextColor(Color.parseColor("#FF0101"));
                }

                else if(position == 1){
                    searialNum1TextView.setText("1MO1812040031");
                    searialNum2TextView.setText("1SO1811270009");
                    companyNumTextView.setText("F260011ATN-2");
                    thingTextView.setText("ATN260011  系統櫃(垃圾筒) -抽屜+垃圾筒固定片*4pcs");
                    onlineTimeTextView.setText("預計上線：2018-12-06");
                    quantityTextView.setText("生產數量：3");
                    startTimeTextView.setText("計劃開始：08:00");
                    finishTimeTextView.setText("生產結束：08:05");
                    jobTextView.setText("一群-點焊");
                    stateTextView.setText("生效");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else if(position == 2){
                    searialNum1TextView.setText("1MO1812040025");
                    searialNum2TextView.setText("1SO1811270009");
                    companyNumTextView.setText("J1-EP340T-F260011ATN-2");
                    thingTextView.setText("ATN260011  系統櫃(垃圾筒) -抽屜+垃圾筒固定片*4pcs");
                    onlineTimeTextView.setText("預計上線：2018-12-07");
                    quantityTextView.setText("生產數量：3");
                    startTimeTextView.setText("計劃開始：09:30");
                    finishTimeTextView.setText("生產結束：09:50");
                    jobTextView.setText("一群-點焊");
                    stateTextView.setText("塗裝");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else if(position == 3){
                    searialNum1TextView.setText("1MO1812040005");
                    searialNum2TextView.setText("1SO1811270009");
                    companyNumTextView.setText("ATN260011-06");
                    thingTextView.setText("EP338T砂漆淺灰/EP340T砂漆灰  系統櫃組合--26”下箱垃圾桶櫃");
                    onlineTimeTextView.setText("預計上線：2018-12-08");
                    quantityTextView.setText("生產數量：3");
                    startTimeTextView.setText("計劃開始：08:00");
                    finishTimeTextView.setText("生產結束：08:05");
                    jobTextView.setText("一群-裝配");
                    stateTextView.setText("生效");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else{
                    searialNum1TextView.setText("1SO1811270009");
                    searialNum2TextView.setText(" ");
                    companyNumTextView.setText("客戶名稱：(M1315) MATADOR  GmbH");
                    thingTextView.setText("客戶訂單：6003028");
                    onlineTimeTextView.setText("業務人員：(M3049) 嚴卉婷");
                    quantityTextView.setText(" ");
                    startTimeTextView.setText(" ");
                    finishTimeTextView.setText(" ");
                    jobTextView.setText(" ");
                    stateTextView.setText("生效");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }
            }
        });

        // 設定按鈕點擊事件
        leftImageView.setOnClickListener(view -> viewViewPager.setCurrentItem(viewViewPager.getCurrentItem() - 1));
        rightImageView.setOnClickListener(view -> viewViewPager.setCurrentItem(viewViewPager.getCurrentItem() + 1));

        new TabLayoutMediator(themeTabLayout, viewViewPager, (tab, position) -> {
            tab.setText(title[position]);
        }).attach();


        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
