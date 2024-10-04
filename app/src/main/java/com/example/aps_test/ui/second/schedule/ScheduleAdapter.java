package com.example.aps_test.ui.second.schedule;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aps_test.R;
import com.example.aps_test.ui.scheduleResult.ScheduleResultActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private Context context;

    public ScheduleAdapter(ArrayList<HashMap<String, String>> marrayList, Context context) {
    this.arrayList = marrayList;
    this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView numTextView,searialNum1TextView,searialNum2TextView,company1TextView,
                company2TextView,quantity1TextView,quantity2TextView,dataTextView,jobTextView
                ,takeEffectTextView;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.schedulerv_ll_lt);
            numTextView = itemView.findViewById(R.id.schedulerv_Num_tv);
            searialNum1TextView = itemView.findViewById(R.id.schedulerv_searialNum1_tv);
            searialNum2TextView = itemView.findViewById(R.id.schedulerv_searialNum2_tv);
            company1TextView = itemView.findViewById(R.id.schedulerv_company1_tv);
            company2TextView = itemView.findViewById(R.id.schedulerv_company2_tv);
            quantity1TextView = itemView.findViewById(R.id.schedulerv_quantity1_tv);
            quantity2TextView = itemView.findViewById(R.id.schedulerv_quantity2_tv);
            dataTextView = itemView.findViewById(R.id.schedulerv_data_tv);
            jobTextView = itemView.findViewById(R.id.schedulerv_job_tv);
            mView = itemView;
        }
    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_recyclerview,parent,false);
        return new ScheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        holder.numTextView.setText(arrayList.get(position).get("Num"));
        holder.searialNum1TextView.setText(arrayList.get(position).get("SearialNum1"));
        holder.searialNum2TextView.setText(arrayList.get(position).get("SearialNum2"));
        holder.company1TextView.setText(arrayList.get(position).get("Company1"));
        holder.company2TextView.setText(arrayList.get(position).get("Company2"));
        holder.quantity1TextView.setText(arrayList.get(position).get("Quantity1"));
        holder.quantity2TextView.setText(arrayList.get(position).get("Quantity2"));
        holder.dataTextView.setText(arrayList.get(position).get("Data"));
        holder.jobTextView.setText(arrayList.get(position).get("Job"));

        holder.mView.setOnClickListener((v)->{
            Intent intent = new Intent(context, ScheduleResultActivity.class);
            intent.putExtra("THEME_EXTRA",0);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
