package com.example.aps_test.ui.scheduleResult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.APS_test.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleResultAdapter extends RecyclerView.Adapter<ScheduleResultAdapter.ViewHolder>{
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private Context context;

    public ScheduleResultAdapter(ArrayList<HashMap<String, String>> marrayList, Context context) {
        this.arrayList = marrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView NumTextView;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            NumTextView = itemView.findViewById(R.id.result_Num_tv);
            mView = itemView;
        }
    }

    @NonNull
    @Override
    public ScheduleResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_recyclerview,parent,false);
        return new ScheduleResultAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleResultAdapter.ViewHolder holder, int position) {
        holder.NumTextView.setText(arrayList.get(position).get("Num"));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

