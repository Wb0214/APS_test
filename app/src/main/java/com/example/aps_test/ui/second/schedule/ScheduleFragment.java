package com.example.aps_test.ui.second.schedule;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.APS_test.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleFragment extends Fragment implements ScheduleContract.view{
    private SchedulePresenter schedulePresenter;
    private RecyclerView schedulerecyclerView;
    ScheduleAdapter scheduleAdapter;
    private Context context;

    ArrayList<HashMap<String, String>> marrayList = new ArrayList<>();

    public ScheduleFragment(Context context) {
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

    @Override
    //onCreateView，這裡是用來創立Fragment的View並且回傳，
    // 物件的id也可以選擇在這裡綁定，但通常還是會再下一個要介紹的方法綁定，
    // 這裡就拿來創立View而已。
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //這裡通常是用於創建Fragment的View
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    //onViewCreated，這裡會寫跟物件有關的動作，
    // 比如:綁定物件id、物件的監聽器......。
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        schedulerecyclerView = view.findViewById(R.id.schedule_list_rv);

        schedulePresenter = new SchedulePresenter(this);
        schedulePresenter.Ans();

        schedulerecyclerView.setLayoutManager(new LinearLayoutManager(context));
        schedulerecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        scheduleAdapter = new ScheduleAdapter(marrayList,context);
        schedulerecyclerView.setAdapter(scheduleAdapter);
    }

    @Override
    public void getData(ArrayList arrayList){
        marrayList = arrayList;
    }
}