package com.example.aps_test.ui.scheduleResult.resultFragment.resultAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.APS_test.R;
import com.example.aps_test.instance.GetAfterData;

import java.util.ArrayList;
import java.util.HashMap;

public class AfterAdapter extends RecyclerView.Adapter<AfterAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private Context context;
    private GetAfterData getAfterData;

    public AfterAdapter(ArrayList<HashMap<String, String>> marrayList, Context context) {
        this.arrayList = marrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView numTextView, ItemIdTextView, ItemNameTextView,
                quantityTextView, usequantityTextView, nuitTextView, directionsTextView;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numTextView = itemView.findViewById(R.id.result_Num_tv);
            ItemIdTextView = itemView.findViewById(R.id.result_MaterialNum_tv);
            ItemNameTextView = itemView.findViewById(R.id.result_product_tv);
            quantityTextView = itemView.findViewById(R.id.result_quantity_tv);
            usequantityTextView = itemView.findViewById(R.id.result_useQuantity_tv);
            nuitTextView = itemView.findViewById(R.id.result_nuit_tv);
            directionsTextView = itemView.findViewById(R.id.result_directions_tv);
            mView = itemView;
        }
    }

    @NonNull
    @Override
    public AfterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_recyclerview,parent,false);
        return new AfterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AfterAdapter.ViewHolder holder, int position) {
        getAfterData = GetAfterData.getInstance();
        arrayList = getAfterData.getAfterArrayList();
        Log.d("AfterAdapter", "onBindViewHolder: "+arrayList);

        holder.numTextView.setText(arrayList.get(position).get("Num"));
        holder.ItemIdTextView.setText(arrayList.get(position).get("ItemId"));
        holder.ItemNameTextView.setText(arrayList.get(position).get("ItemName"));
        holder.quantityTextView.setText(arrayList.get(position).get("Qty"));
        holder.usequantityTextView.setText(arrayList.get(position).get("NuseQty"));
        holder.nuitTextView.setText(arrayList.get(position).get("UnitId"));
        holder.directionsTextView.setText("");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
