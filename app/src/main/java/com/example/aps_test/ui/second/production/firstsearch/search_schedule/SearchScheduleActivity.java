package com.example.aps_test.ui.second.production.firstsearch.search_schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.APS_test.R;
import com.example.aps_test.ui.second.schedule.ScheduleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchScheduleActivity extends AppCompatActivity implements SearchScheduleContract.view{

    private RecyclerView searchSchedulerecyclerView;
    SearchScheduleAdapter searchScheduleAdapter;
    private TextView topthemeTextView;
    private ImageView backImageView;
    private SearchSchedulePresenter searchSchedulePresenter;

    ArrayList<HashMap<String, String>> marrayList = new ArrayList<>();
    int Sum;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_schedule);

        topthemeTextView = findViewById(R.id.searchSchedule_Toptheme2_tv);
        backImageView = findViewById(R.id.searchSchedule_back_iv);
        searchSchedulerecyclerView = findViewById(R.id.searchSchedule_list_rv);

        Intent intent = getIntent();

        searchSchedulePresenter = new SearchSchedulePresenter(this);
        searchSchedulePresenter.Ans();

        searchSchedulerecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchSchedulerecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        searchScheduleAdapter = new SearchScheduleAdapter(marrayList,this);
        searchSchedulerecyclerView.setAdapter(searchScheduleAdapter);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void getData(ArrayList arrayList){
        marrayList = arrayList;
    }
}