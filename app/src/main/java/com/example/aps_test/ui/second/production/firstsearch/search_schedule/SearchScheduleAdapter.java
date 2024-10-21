package com.example.aps_test.ui.second.production.firstsearch.search_schedule;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.APS_test.R;
import com.example.aps_test.ui.scheduleResult.ScheduleResultActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchScheduleAdapter extends RecyclerView.Adapter<SearchScheduleAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private Activity activity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView numTextView, MoIdTextView, SoIdTextView, ItemIdTextView,
                CustomerTextView, quantityTextView, CompleteDateTextView, OnlineDateTextView, RelatedTechRouteTextView
                ,takeEffectTextView;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.recyclerview_ll_lt);
            numTextView = itemView.findViewById(R.id.recyclerview_Num_tv);
            MoIdTextView = itemView.findViewById(R.id.recyclerview_MoId_tv);
            SoIdTextView = itemView.findViewById(R.id.recyclerview_SoId_tv);
            ItemIdTextView = itemView.findViewById(R.id.recyclerview_ItemId_tv);
            CustomerTextView = itemView.findViewById(R.id.recyclerview_Customer_tv);
            quantityTextView = itemView.findViewById(R.id.recyclerview_quantity_tv);
            CompleteDateTextView = itemView.findViewById(R.id.recyclerview_CompleteDate_tv);
            OnlineDateTextView = itemView.findViewById(R.id.recyclerview_OnlineDate_tv);
            RelatedTechRouteTextView = itemView.findViewById(R.id.recyclerview_RelatedTechRoute_tv);
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

        Log.d("onBindViewHolder", "onBindViewHolder: "+arrayList);
        holder.numTextView.setText(arrayList.get(position).get("Num"));
        holder.MoIdTextView.setText(arrayList.get(position).get("mo_id"));
        holder.SoIdTextView.setText(arrayList.get(position).get("so_id"));
        holder.ItemIdTextView.setText(arrayList.get(position).get("item_id"));
        holder.CustomerTextView.setText(arrayList.get(position).get("customer"));
        holder.quantityTextView.setText(arrayList.get(position).get("qty"));
        holder.CompleteDateTextView.setText(arrayList.get(position).get("complete_date"));
        holder.OnlineDateTextView.setText(arrayList.get(position).get("online_date"));
        holder.RelatedTechRouteTextView.setText(arrayList.get(position).get("related_tech_route"));
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
