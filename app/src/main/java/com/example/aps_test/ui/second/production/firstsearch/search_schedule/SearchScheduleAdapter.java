package com.example.aps_test.ui.second.production.firstsearch.search_schedule;

import android.app.Activity;
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

public class SearchScheduleAdapter extends RecyclerView.Adapter<SearchScheduleAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private Activity activity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView numTextView,searialTextView,searialNumTextView,company1TextView,
                company2TextView,quantity1TextView,quantity2TextView,dataTextView,jobTextView
                ,takeEffectTextView;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.recyclerview_ll_lt);
            numTextView = itemView.findViewById(R.id.recyclerview_Num_tv);
            searialTextView = itemView.findViewById(R.id.recyclerview_searial_tv);
            searialNumTextView = itemView.findViewById(R.id.recyclerview_searialN_tv);
            company1TextView = itemView.findViewById(R.id.recyclerview_company1_tv);
            company2TextView = itemView.findViewById(R.id.recyclerview_company2_tv);
            quantity1TextView = itemView.findViewById(R.id.recyclerview_quantity1_tv);
            quantity2TextView = itemView.findViewById(R.id.recyclerview_quantity2_tv);
            dataTextView = itemView.findViewById(R.id.recyclerview_data_tv);
            jobTextView = itemView.findViewById(R.id.recyclerview_job_tv);
            takeEffectTextView = itemView.findViewById(R.id.recyclerview_takeEffect_tv);
            mView = itemView;
        }
    }

    public SearchScheduleAdapter(ArrayList<HashMap<String,String>> arrayList, Activity activity) {
        this.arrayList = arrayList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public SearchScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searchschedule_recyclerview,parent,false);
        return new SearchScheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchScheduleAdapter.ViewHolder holder, int position) {
        holder.numTextView.setText(arrayList.get(position).get("Num"));
        holder.searialTextView.setText(arrayList.get(position).get("Searial"));
        holder.searialNumTextView.setText(arrayList.get(position).get("SearialNum"));
        holder.company1TextView.setText(arrayList.get(position).get("Company1"));
        holder.company2TextView.setText(arrayList.get(position).get("Company2"));
        holder.quantity1TextView.setText(arrayList.get(position).get("Quantity1"));
        holder.quantity2TextView.setText(arrayList.get(position).get("Quantity2"));
        holder.dataTextView.setText(arrayList.get(position).get("Data"));
        holder.jobTextView.setText(arrayList.get(position).get("Job"));
        holder.takeEffectTextView.setText(arrayList.get(position).get("takeEffect"));

        holder.mView.setOnClickListener((v)->{
            Intent intent = new Intent(activity, ScheduleResultActivity.class);
            intent.putExtra("THEME_EXTRA",1);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
