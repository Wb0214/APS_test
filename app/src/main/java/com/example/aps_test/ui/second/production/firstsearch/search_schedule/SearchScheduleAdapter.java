package com.example.aps_test.ui.second.production.firstsearch.search_schedule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.APS_test.R;
import com.example.aps_test.api.ApiClient;
import com.example.aps_test.api.ApiService;
import com.example.aps_test.api.response.PrevMfgResponse;
import com.example.aps_test.instance.GetPrevMfgData;
import com.example.aps_test.sharedPreferences.SP;
import com.example.aps_test.ui.scheduleResult.ScheduleResultActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class SearchScheduleAdapter extends RecyclerView.Adapter<SearchScheduleAdapter.ViewHolder> {
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private Activity activity;
    private SP sp;

    private GetPrevMfgData getPrevMfgData;

    private ApiClient apiClient;
    private ApiService apiService;

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
        sp = new SP(activity);
        this.apiClient = new ApiClient();
        this.apiService = apiClient.LoginApi().create(ApiService.class);
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
        getPrevMfgData = GetPrevMfgData.getInstance();
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
            GetPrevMfg(arrayList.get(position).get("so_id"),arrayList.get(position).get("item_id"),sp.loadToken(),position);

            Intent intent = new Intent(activity, ScheduleResultActivity.class);
            intent.putExtra("THEME_EXTRA",1);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void GetPrevMfg(String so_id,String item_id,String token,int position){
        apiService.getPrevMfg(so_id,item_id,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<PrevMfgResponse>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {


                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull Response<List<PrevMfgResponse>> listResponse) {
                        getPrevMfgData = GetPrevMfgData.getInstance();
                        int size = listResponse.body().size();
                        arrayList.clear();

                        if(size != 0){
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("Num", String.valueOf(position));
                            hashMap.put("MoId",listResponse.body().get(0).MoId());
                            hashMap.put("SoId",listResponse.body().get(0).SoId());
                            hashMap.put("ItemId",listResponse.body().get(0).ItemId());
                            hashMap.put("ItemName",listResponse.body().get(0).ItemName());
                            hashMap.put("OnlineDate",listResponse.body().get(0).OnlineDate());
                            hashMap.put("Qty",listResponse.body().get(0).Qty());
                            hashMap.put("CompleteDate",listResponse.body().get(0).CompleteDate());
                            hashMap.put("TechRoutingName",listResponse.body().get(0).TechRoutingName());
                            arrayList.add(hashMap);
                            getPrevMfgData.setPrevMfgArrayList(arrayList);
                        }
                        else{
                            Toast.makeText(activity, "查無資料", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.d("GetPrevMfg", "onNext: "+arrayList);

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("getPrevMfg", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getPrevMfg", "onComplete");
                    }
                });
    }

}
