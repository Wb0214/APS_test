package com.example.aps_test.ui.second.production.firstsearch.search_schedule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.APS_test.R;
import com.example.aps_test.sharedPreferences.SP;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchScheduleActivity extends AppCompatActivity implements SearchScheduleContract.view{

    private RecyclerView searchSchedulerecyclerView;
    SearchScheduleAdapter searchScheduleAdapter;
    private TextView NameTextView,SumTextView;
    private ImageView backImageView;
    private SearchSchedulePresenter searchSchedulePresenter;
    private SP sp;

    ArrayList<HashMap<String, String>> marrayList = new ArrayList<>();
    int Sum;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_schedule);

        backImageView = findViewById(R.id.searchSchedule_back_iv);
        SumTextView = findViewById(R.id.searchSchedule_Sum_tv);
        searchSchedulerecyclerView = findViewById(R.id.searchSchedule_list_rv);
        NameTextView = findViewById(R.id.searchSchedule_name_tv);

        sp = new SP(this);
        NameTextView.setText(sp.loadName());

        Intent intent = getIntent();

        searchSchedulePresenter = new SearchSchedulePresenter(this,this);
        searchSchedulePresenter.getData(sp.loadCustomerName(),sp.loadSoId(),sp.loadToken());


        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void Data(ArrayList arrayList,Integer Sum){
        marrayList = arrayList;
        Log.d("Data", "Data: "+marrayList);
        SumTextView.setText("共"+Sum+"筆");

        searchSchedulerecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchSchedulerecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        searchScheduleAdapter = new SearchScheduleAdapter(marrayList,this);
        searchSchedulerecyclerView.setAdapter(searchScheduleAdapter);
    }
}