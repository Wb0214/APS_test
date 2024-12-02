package com.example.aps_test.ui.scheduleResult.resultFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.APS_test.R;
import com.example.aps_test.instance.GetPrevMfgData;
import com.example.aps_test.instance.GetROMData;
import com.example.aps_test.ui.scheduleResult.resultFragment.resultAdapter.NowAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class NowFragment extends Fragment {
    private Context context;
    private RecyclerView nowRecyclerView;
    NowAdapter nowAdapter;
    private GetROMData getROMData;
    private GetPrevMfgData getPrevMfgData;
    private TextView MoIdTextView, SoIdTextView, ItemIdTextView,
            ItemNameTextView,quantityTextView,startTimeTextView, finishTimeTextView;

    ArrayList<HashMap<String, String>> marrayList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> PrevMfgarrayList = new ArrayList<>();

    public NowFragment(Context context) {
        this.context = context;
    }

    @Override
    //onCreate，這裡是在Fragment建立時最先執行程式的地方，
    // 雖然這裡可以使用物件的監聽器，但會因為剛建立的關係，
    // 物件或傳入的資料還沒傳到應該要傳的地方就被調用而導致報錯，
    // 所以這裡通常只會用來初始化，以及接收從別處傳來的資料。
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    //onCreateView，這裡是用來創立Fragment的View並且回傳，
    // 物件的id也可以選擇在這裡綁定，但通常還是會再下一個方法綁定，
    // 這裡就拿來創立View而已。
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //這裡通常是用於創建Fragment的View
        return inflater.inflate(R.layout.fragment_before, container, false);
    }

    @Override
    //onViewCreated，這裡會寫跟物件有關的動作，
    // 比如:綁定物件id、物件的監聽器......。
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nowRecyclerView = view.findViewById(R.id.before_list_rv);
        MoIdTextView = view.findViewById(R.id.before_Moid_tv);
        SoIdTextView = view.findViewById(R.id.before_Soid_tv);
        ItemIdTextView = view.findViewById(R.id.before_itemId_tv);
        ItemNameTextView = view.findViewById(R.id.before_ItemName_tv);
        quantityTextView = view.findViewById(R.id.before_Qty_tv);
        startTimeTextView = view.findViewById(R.id.before_StartTime_tv);
        finishTimeTextView = view.findViewById(R.id.before_EndTime_tv);

        getROMData = GetROMData.getInstance();
        marrayList = getROMData.getROMArrayList();
        Log.e("NowFragment", "getROMArrayList: " + marrayList);
        getPrevMfgData = GetPrevMfgData.getInstance();
        PrevMfgarrayList = getPrevMfgData.getPrevMfgArrayList();

        nowRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        nowRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        nowAdapter = new NowAdapter(marrayList, context);
        nowRecyclerView.setAdapter(nowAdapter);

        MoIdTextView.setText("製令單號: "+PrevMfgarrayList.get(0).get("MoId"));
        SoIdTextView.setText("來源訂單:"+PrevMfgarrayList.get(0).get("SoId"));
        ItemIdTextView.setText("母件編號: "+PrevMfgarrayList.get(0).get("ItemId"));
        ItemNameTextView.setText("母件單品:"+PrevMfgarrayList.get(0).get("ItemName"));
        quantityTextView.setText("生產數量: "+PrevMfgarrayList.get(0).get("Qty"));
        startTimeTextView.setText("預開工日: "+PrevMfgarrayList.get(0).get("OnlineDate"));
        finishTimeTextView.setText("預完工日: "+PrevMfgarrayList.get(0).get("CompleteDate"));


    }

}