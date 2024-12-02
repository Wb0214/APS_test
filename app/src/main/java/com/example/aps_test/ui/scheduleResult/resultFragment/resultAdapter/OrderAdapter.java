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
import com.example.aps_test.instance.GetSaleOrder;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private Context context;
    private GetSaleOrder getSaleOrder;

    public OrderAdapter(ArrayList<HashMap<String, String>> marrayList, Context context) {
        this.arrayList = marrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView numTextView, ItemIdTextView, ItemNameTextView,
                quantityTextView, usequantityTextView, nuitTextView, dataTextView,NoticeTextView;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numTextView = itemView.findViewById(R.id.lastResultRe_Num_tv);
            ItemIdTextView = itemView.findViewById(R.id.lastResultRe_ProductNum_tv);
            ItemNameTextView = itemView.findViewById(R.id.lastResultRe_Product_tv);
            quantityTextView = itemView.findViewById(R.id.lastResultRe_quantity_tv);
            nuitTextView = itemView.findViewById(R.id.lastResultRe_unit_tv);
            usequantityTextView = itemView.findViewById(R.id.lastResultRe_useQuantity_tv);
            dataTextView = itemView.findViewById(R.id.lastResultRe_data_tv);
            NoticeTextView = itemView.findViewById(R.id.lastResultRe_Notice_tv);
            mView = itemView;
        }
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.last_result_recyclerview,parent,false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        getSaleOrder = GetSaleOrder.getInstance();
        arrayList = getSaleOrder.getSaleOrderArrayList();
        Log.d("OrderAdapter", "onBindViewHolder: "+arrayList);

        holder.numTextView.setText(arrayList.get(position).get("Num"));
        holder.ItemIdTextView.setText(arrayList.get(position).get("MaterialId"));
        holder.ItemNameTextView.setText(arrayList.get(position).get("BomkeyName"));
        holder.quantityTextView.setText(arrayList.get(position).get("UnitQty"));
        holder.usequantityTextView.setText(arrayList.get(position).get("NuseQty"));
        holder.nuitTextView.setText("SET");
        holder.dataTextView.setText(arrayList.get(position).get("Date"));
        holder.NoticeTextView.setText(arrayList.get(position).get("Remark"));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
