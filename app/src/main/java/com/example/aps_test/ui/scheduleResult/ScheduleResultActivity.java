package com.example.aps_test.ui.scheduleResult;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.APS_test.R;
import com.example.aps_test.instance.GetPrevMfgData;
import com.example.aps_test.sharedPreferences.SP;
import com.example.aps_test.ui.scheduleResult.resultFragment.ResultViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleResultActivity extends AppCompatActivity implements ScheduleResultContract.view{
    private ImageView backImageView,leftImageView,rightImageView;
    private TextView themeTextView, MoIdTextView, SoIdTextView, ItemIdTextView,
            ItemNameTextView,onlineTimeTextView,quantityTextView,startTimeTextView,
            finishTimeTextView, TechRoutingNameTextView,stateTextView,NameTextView;
    private ViewPager2 viewViewPager;
    private TabLayout themeTabLayout;
    private ScheduleResultPresenter scheduleResultPresenter;

    private Context context= this;
    private SP sp;
    private GetPrevMfgData getPrevMfgData;

    private String[] title= {"前關製令","本階製令","後關製令","裝配製令","銷售訂單"};
    private String[] theme= {"當日進度表","進度表查詢"};

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
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
        NameTextView = findViewById(R.id.searchResult_name_tv);

        MoIdTextView = findViewById(R.id.searchResult_MoId_tv);
        SoIdTextView = findViewById(R.id.searchResult_SoId_tv);
        ItemIdTextView = findViewById(R.id.searchResult_ItemId_tv);
        ItemNameTextView = findViewById(R.id.searchResult_ItemName_tv);
        onlineTimeTextView = findViewById(R.id.searchResult_onlineTime_tv);
        quantityTextView = findViewById(R.id.searchResult_quantity_tv);
        startTimeTextView = findViewById(R.id.searchResult_startTime_tv);
        finishTimeTextView = findViewById(R.id.searchResult_finishTime_tv);
        TechRoutingNameTextView = findViewById(R.id.searchResult_TechRoutingName_tv);
        stateTextView = findViewById(R.id.searchResult_state_tv);

        leftImageView = findViewById(R.id.searchResult_left_iv);
        rightImageView = findViewById(R.id.searchResult_right_iv);

        themeTextView.setText(theme[i]);

        scheduleResultPresenter = new ScheduleResultPresenter(this);
        sp = new SP(context);
        NameTextView.setText(sp.loadName());

        viewViewPager.setAdapter(new ResultViewPagerAdapter(this, this));
        viewViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // 控制按鈕可見性
                leftImageView.setVisibility(position == 0 ? View.INVISIBLE : View.VISIBLE);
                rightImageView.setVisibility(position == 4 ? View.INVISIBLE : View.VISIBLE);

                getPrevMfgData = GetPrevMfgData.getInstance();
                arrayList = getPrevMfgData.getPrevMfgArrayList();
                Log.e("TAG", "onPageSelected: " + getPrevMfgData.getPrevMfgArrayList());
                Log.e("TAG", "onPageSelected: " + arrayList);

                if(position == 0){
                    String MoId = arrayList.get(0).get("MoId");
                    String SoId = arrayList.get(0).get("SoId");
                    String ItemId = arrayList.get(0).get("ItemId");
                    String ItemName = arrayList.get(0).get("ItemName");
                    String OnlineDate = arrayList.get(0).get("OnlineDate");
                    String Qty = arrayList.get(0).get("Qty");
                    String TechRoutingName = arrayList.get(0).get("TechRoutingName");

                    MoIdTextView.setText(MoId);
                    SoIdTextView.setText(SoId);
                    ItemIdTextView.setText(ItemId);
                    ItemNameTextView.setText(ItemName);
                    onlineTimeTextView.setText("預計上線："+OnlineDate);
                    quantityTextView.setText("生產數量："+Qty);
                    startTimeTextView.setText("計劃開始：15:30");
                    finishTimeTextView.setText("生產結束：15:45");
                    TechRoutingNameTextView.setText(TechRoutingName);
                    stateTextView.setText("結案");
                    stateTextView.setTextColor(Color.parseColor("#FF0101"));
                }

                else if(position == 1){
                    String MoId = arrayList.get(0).get("MoId");
                    String SoId = arrayList.get(0).get("SoId");
                    String ItemId = arrayList.get(0).get("ItemId");
                    String ItemName = arrayList.get(0).get("ItemName");
                    String OnlineDate = arrayList.get(0).get("OnlineDate");
                    String Qty = arrayList.get(0).get("Qty");
                    String TechRoutingName = arrayList.get(0).get("TechRoutingName");

                    MoIdTextView.setText(MoId);
                    SoIdTextView.setText(SoId);
                    ItemIdTextView.setText(ItemId);
                    ItemNameTextView.setText(ItemName);
                    onlineTimeTextView.setText("預計上線："+OnlineDate);
                    quantityTextView.setText("生產數量："+Qty);
                    startTimeTextView.setText("計劃開始：15:30");
                    finishTimeTextView.setText("生產結束：15:45");
                    TechRoutingNameTextView.setText(TechRoutingName);
                    stateTextView.setText("生效");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else if(position == 2){
                    MoIdTextView.setText("1MO1812040025");
                    SoIdTextView.setText("1SO1811270009");
                    ItemIdTextView.setText("J1-EP340T-F260011ATN-2");
                    ItemNameTextView.setText("ATN260011  系統櫃(垃圾筒) -抽屜+垃圾筒固定片*4pcs");
                    onlineTimeTextView.setText("預計上線：2018-12-07");
                    quantityTextView.setText("生產數量：3");
                    startTimeTextView.setText("計劃開始：09:30");
                    finishTimeTextView.setText("生產結束：09:50");
                    TechRoutingNameTextView.setText("一群-點焊");
                    stateTextView.setText("塗裝");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else if(position == 3){
                    MoIdTextView.setText("1MO1812040005");
                    SoIdTextView.setText("1SO1811270009");
                    ItemIdTextView.setText("ATN260011-06");
                    ItemNameTextView.setText("EP338T砂漆淺灰/EP340T砂漆灰  系統櫃組合--26”下箱垃圾桶櫃");
                    onlineTimeTextView.setText("預計上線：2018-12-08");
                    quantityTextView.setText("生產數量：3");
                    startTimeTextView.setText("計劃開始：08:00");
                    finishTimeTextView.setText("生產結束：08:05");
                    TechRoutingNameTextView.setText("一群-裝配");
                    stateTextView.setText("生效");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else{
                    MoIdTextView.setText("1SO1811270009");
                    SoIdTextView.setText(" ");
                    ItemIdTextView.setText("客戶名稱：(M1315) MATADOR  GmbH");
                    ItemNameTextView.setText("客戶訂單：6003028");
                    onlineTimeTextView.setText("業務人員：(M3049) 嚴卉婷");
                    quantityTextView.setText(" ");
                    startTimeTextView.setText(" ");
                    finishTimeTextView.setText(" ");
                    TechRoutingNameTextView.setText(" ");
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
