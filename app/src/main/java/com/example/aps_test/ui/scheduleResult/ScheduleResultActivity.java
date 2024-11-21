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
import com.example.aps_test.instance.GetAfterData;
import com.example.aps_test.instance.GetCurrentStageData;
import com.example.aps_test.instance.GetPrevMfgData;
import com.example.aps_test.instance.GetROMData;
import com.example.aps_test.instance.GetSaleOrder;
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
    private GetROMData getROMData;
    private GetAfterData getAfterData;
    private GetCurrentStageData getCurrentStageData;
    private GetSaleOrder getSaleOrder;

    private String[] title= {"前關製令","本階製令","後關製令","裝配製令","銷售訂單"};
    private String[] theme= {"當日進度表","進度表查詢"};

    ArrayList<HashMap<String,String>> PrevMfgarrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> ROMarrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> AfterarrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> CurrentStagearrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> SaleOrderarrayList = new ArrayList<>();

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

                //實例取資料
                getPrevMfgData = GetPrevMfgData.getInstance();
                PrevMfgarrayList = getPrevMfgData.getPrevMfgArrayList();
                Log.e("ScheduleResultActivity", "getPrevMfgArrayList: " + PrevMfgarrayList);

                getROMData = GetROMData.getInstance();
                ROMarrayList = getROMData.getROMArrayList();
                Log.e("ScheduleResultActivity", "getROMArrayList: " + ROMarrayList);

                getAfterData = GetAfterData.getInstance();
                AfterarrayList = getAfterData.getAfterArrayList();
                Log.e("ScheduleResultActivity", "getAfterArrayList: " + AfterarrayList);

                getCurrentStageData = GetCurrentStageData.getInstance();
                CurrentStagearrayList = getCurrentStageData.getCurrentStageArrayList();
                Log.e("ScheduleResultActivity", "getCurrentStageArrayList: " + CurrentStagearrayList);

                getSaleOrder = GetSaleOrder.getInstance();
                SaleOrderarrayList = getSaleOrder.getSaleOrderArrayList();
                Log.e("ScheduleResultActivity", "getSaleOrderArrayList: " + SaleOrderarrayList);

                ///////////////////////////////////////////////////////////
                if(position == 0){
                    String MoId = PrevMfgarrayList.get(0).get("MoId");
                    String SoId = PrevMfgarrayList.get(0).get("SoId");
                    String ItemId = PrevMfgarrayList.get(0).get("ItemId");
                    String ItemName = PrevMfgarrayList.get(0).get("ItemName");
                    String OnlineDate = PrevMfgarrayList.get(0).get("OnlineDate");
                    String Qty = PrevMfgarrayList.get(0).get("Qty");
                    String TechRoutingName = PrevMfgarrayList.get(0).get("TechRoutingName");
                    String CreatedAt = PrevMfgarrayList.get(0).get("CreatedAt");
                    String UpdatedAt = PrevMfgarrayList.get(0).get("UpdatedAt");

                    MoIdTextView.setText(MoId);
                    SoIdTextView.setText(SoId);
                    ItemIdTextView.setText(ItemId);
                    ItemNameTextView.setText(ItemName);
                    onlineTimeTextView.setText("預計上線："+OnlineDate);
                    quantityTextView.setText("生產數量："+Qty);
                    startTimeTextView.setText("計劃開始："+CreatedAt.substring(11,CreatedAt.length()-3));
                    finishTimeTextView.setText("生產結束：" + UpdatedAt.substring(11,UpdatedAt.length()-3));
                    TechRoutingNameTextView.setText(TechRoutingName);
                    stateTextView.setText("結案");
                    stateTextView.setTextColor(Color.parseColor("#FF0101"));
                }

                else if(position == 1){
                    String ROMMoId = PrevMfgarrayList.get(0).get("MoId");
                    String ROMSoId = PrevMfgarrayList.get(0).get("SoId");
                    String ROMItemId = PrevMfgarrayList.get(0).get("ItemId");
                    String ROMBomkeyName = ROMarrayList.get(0).get("BomkeyName");
                    String ROMOnlineDate = PrevMfgarrayList.get(0).get("OnlineDate");
                    String ROMBaseQty = ROMarrayList.get(0).get("BaseQty");
                    String ROMTechRoutingName = PrevMfgarrayList.get(0).get("TechRoutingName");
                    String ROMCreatedAt = ROMarrayList.get(0).get("CreatedAt");
                    String ROMUpdatedAt = ROMarrayList.get(0).get("UpdatedAt");

                    MoIdTextView.setText(ROMMoId);
                    SoIdTextView.setText(ROMSoId);
                    ItemIdTextView.setText(ROMItemId);
                    ItemNameTextView.setText(ROMBomkeyName);
                    onlineTimeTextView.setText("預計上線："+ROMOnlineDate);
                    quantityTextView.setText("生產數量："+ROMBaseQty);
                    startTimeTextView.setText("計劃開始："+ROMCreatedAt.substring(11,ROMCreatedAt.length()-3));
                    finishTimeTextView.setText("生產結束：" + ROMUpdatedAt.substring(11,ROMUpdatedAt.length()-3));
                    TechRoutingNameTextView.setText(ROMTechRoutingName);
                    stateTextView.setText("生效");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else if(position == 2){
                    String AfterMoId = AfterarrayList.get(0).get("MoId");
                    String AfterSoId = PrevMfgarrayList.get(0).get("SoId");
                    String AfterItemId = AfterarrayList.get(0).get("ItemId");
                    String AfterCreatedAt = AfterarrayList.get(0).get("CreatedAt");
                    String AfterUpdatedAt = AfterarrayList.get(0).get("UpdatedAt");
                    String AfterNuseQty = AfterarrayList.get(0).get("NuseQty");
                    String AfterItemName = AfterarrayList.get(0).get("ItemName");
                    String AfterOnlineDate = AfterarrayList.get(0).get("OnlineDate");
                    String AfterCompleteDate = AfterarrayList.get(0).get("CompleteDate");
                    String AfterQty = AfterarrayList.get(0).get("Qty");
                    String AfterBomkeyName = AfterarrayList.get(0).get("BomkeyName");
                    String AfterUnitId = AfterarrayList.get(0).get("UnitId");


                    MoIdTextView.setText(AfterMoId);
                    SoIdTextView.setText(AfterSoId);
                    ItemIdTextView.setText(AfterItemId);
                    ItemNameTextView.setText(AfterItemName);
                    onlineTimeTextView.setText("預計上線："+AfterOnlineDate);
                    quantityTextView.setText("生產數量："+AfterQty);
                    startTimeTextView.setText("計劃開始："+AfterCreatedAt.substring(11,AfterCreatedAt.length()-3));
                    finishTimeTextView.setText("生產結束：" + AfterUpdatedAt.substring(11,AfterUpdatedAt.length()-3));
                    TechRoutingNameTextView.setText("一群-點焊");
                    stateTextView.setText("塗裝");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else if(position == 3){
                    String MoId = CurrentStagearrayList.get(0).get("MoId");
                    String SoId = CurrentStagearrayList.get(0).get("SoId");
                    String ItemId = CurrentStagearrayList.get(0).get("ItemId");
                    String ItemName = CurrentStagearrayList.get(0).get("ItemName");
                    String OnlineDate = CurrentStagearrayList.get(0).get("OnlineDate");
                    String CompleteDate = CurrentStagearrayList.get(0).get("CompleteDate");
                    String Qty = CurrentStagearrayList.get(0).get("Qty");
                    String TechRoutingName = CurrentStagearrayList.get(0).get("TechRoutingName");
                    String CreatedAt = CurrentStagearrayList.get(0).get("CreatedAt");
                    String UpdatedAt = CurrentStagearrayList.get(0).get("UpdatedAt");

                    MoIdTextView.setText(MoId);
                    SoIdTextView.setText(SoId);
                    ItemIdTextView.setText(ItemId);
                    ItemNameTextView.setText(ItemName);
                    onlineTimeTextView.setText("預計上線："+OnlineDate);
                    quantityTextView.setText("生產數量："+Qty);
                    startTimeTextView.setText("計劃開始："+CreatedAt.substring(11,CreatedAt.length()-3));
                    finishTimeTextView.setText("生產結束：" + UpdatedAt.substring(11,UpdatedAt.length()-3));
                    TechRoutingNameTextView.setText(TechRoutingName);
                    stateTextView.setText("生效");
                    stateTextView.setTextColor(Color.parseColor("#36BC5C"));
                }

                else{
                    String sale_order = CurrentStagearrayList.get(0).get("SoId");
                    String item_id = SaleOrderarrayList.get(0).get("Item_id");
                    String customer_name = SaleOrderarrayList.get(0).get("Customer_name");
                    String qty = SaleOrderarrayList.get(0).get("Qty");
                    String person_id = SaleOrderarrayList.get(0).get("Person_id");
                    String created_at = SaleOrderarrayList.get(0).get("Created_at");
                    String updated_at = SaleOrderarrayList.get(0).get("Updated_at");


                    MoIdTextView.setText(sale_order);
                    SoIdTextView.setText(" ");
                    ItemIdTextView.setTextColor(Color.parseColor("#FF000000"));
                    ItemIdTextView.setText("客戶名稱：(M1315)" + customer_name);
                    ItemNameTextView.setText("客戶訂單：6003028");
                    onlineTimeTextView.setText("業務人員：("+ person_id +") 嚴卉婷");
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
