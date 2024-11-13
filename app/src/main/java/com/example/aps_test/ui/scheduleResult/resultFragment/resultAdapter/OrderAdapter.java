package com.example.aps_test.ui.scheduleResult.resultFragment.resultAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private Context context;

    public OrderAdapter(ArrayList<HashMap<String, String>> marrayList, Context context) {
        this.arrayList = marrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
