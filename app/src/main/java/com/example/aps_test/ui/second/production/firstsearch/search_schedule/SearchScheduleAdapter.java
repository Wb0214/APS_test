package com.example.aps_test.ui.second.production.firstsearch.search_schedule;

import android.app.Activity;
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
import com.example.aps_test.api.response.AfterMfgResponse;
import com.example.aps_test.api.response.CurrentStageResponse;
import com.example.aps_test.api.response.PrevMfgResponse;
import com.example.aps_test.api.response.ROMResponse;
import com.example.aps_test.api.response.SaleOrderResponse;
import com.example.aps_test.instance.GetAfterData;
import com.example.aps_test.instance.GetCurrentStageData;
import com.example.aps_test.instance.GetPrevMfgData;
import com.example.aps_test.instance.GetROMData;
import com.example.aps_test.instance.GetSaleOrder;
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
    ArrayList<HashMap<String,String>> PrevMfgarrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> ROMarrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> AfterarrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> CurrentStagearrayList = new ArrayList<>();
    ArrayList<HashMap<String,String>> SaleOrderarrayList = new ArrayList<>();

    private Activity activity;
    private SP sp;

    private GetPrevMfgData getPrevMfgData;
    private GetROMData getROMData;
    private GetAfterData getAfterData;
    private GetCurrentStageData getCurrentStageData;
    private GetSaleOrder getSaleOrder;

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
            GetPrevMfg(arrayList.get(position).get("so_id"),arrayList.get(position).get("item_id"),sp.loadToken());

            Intent intent = new Intent(activity, ScheduleResultActivity.class);
            intent.putExtra("THEME_EXTRA",1);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void GetPrevMfg(String so_id,String item_id,String token){
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
                        PrevMfgarrayList.clear();

                        if(size != 0){
                            for(int i=0;i<size;i++){
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("Num", String.valueOf(i+1));
                                hashMap.put("MoId",listResponse.body().get(0).MoId());
                                hashMap.put("SoId",listResponse.body().get(0).SoId());
                                hashMap.put("ItemId",listResponse.body().get(0).ItemId());
                                hashMap.put("ItemName",listResponse.body().get(0).ItemName());
                                hashMap.put("OnlineDate",listResponse.body().get(0).OnlineDate());
                                hashMap.put("Qty",listResponse.body().get(0).Qty());
                                hashMap.put("CompleteDate",listResponse.body().get(0).CompleteDate());
                                hashMap.put("TechRoutingName",listResponse.body().get(0).TechRoutingName());
                                hashMap.put("CreatedAt",listResponse.body().get(0).CreatedAt());
                                hashMap.put("UpdatedAt",listResponse.body().get(0).UpdatedAt());

                                PrevMfgarrayList.add(hashMap);
                            }
                            getPrevMfgData.setPrevMfgArrayList(PrevMfgarrayList);
                            Log.d("GetPrevMfg", "onNext: "+PrevMfgarrayList);
                            GetROM(listResponse.body().get(0).ItemId(),sp.loadToken(),listResponse.body().get(0).SoId());
                        }
                        else{
                            Toast.makeText(activity, "查無資料", Toast.LENGTH_SHORT).show();
                            return;
                        }

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

    public void GetROM(String item_id, String token, String sale_order){
        apiService.getBOM(item_id,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<ROMResponse>>>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull Response<List<ROMResponse>> listResponse) {
                        getROMData = GetROMData.getInstance();
                        int size = listResponse.body().size();
                        ROMarrayList.clear();

                        if(size != 0){
                            for (int i=0;i<size;i++){
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("Num", String.valueOf(i+1));
                            hashMap.put("UnitId",listResponse.body().get(i).UnitId());
                            hashMap.put("UnitQty",listResponse.body().get(i).UnitQty());
                            hashMap.put("NuseQty",listResponse.body().get(i).NuseQty());
                            hashMap.put("BaseQty",listResponse.body().get(i).BaseQty());
                            hashMap.put("BomkeyName",listResponse.body().get(i).BomkeyName());
                            hashMap.put("BomkeyId",listResponse.body().get(i).BomkeyId());
                            hashMap.put("CreatedAt",listResponse.body().get(i).CreatedAt());
                            hashMap.put("UpdatedAt",listResponse.body().get(i).UpdatedAt());
                            hashMap.put("MaterialId",listResponse.body().get(i).MaterialId());
                            hashMap.put("DownId",listResponse.body().get(i).DownId());

                            ROMarrayList.add(hashMap);
                            }
                            getROMData.setROMArrayList(ROMarrayList);
                            Log.d("GetROMMfg", "onNext: "+ROMarrayList);
                            GetAfterMfg(sale_order,listResponse.body().get(0).DownId(),sp.loadToken());
                        }
                        else{
                            Toast.makeText(activity, "查無資料", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("getROM", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getROM", "onComplete");
                    }
                });
    }

    public void GetAfterMfg(String sale_order, String id, String token){
        apiService.getAfterMfg(sale_order,id,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<AfterMfgResponse>>>() {

                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull Response<List<AfterMfgResponse>> listResponse) {
                        getAfterData = GetAfterData.getInstance();
                        int size = listResponse.body().size();
                        AfterarrayList.clear();

                        if(size != 0){
                            for (int i=0;i<size;i++) {
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("Num", String.valueOf(i+1));
                                hashMap.put("ItemId", listResponse.body().get(0).ItemId());
                                hashMap.put("CreatedAt", listResponse.body().get(0).CreatedAt());
                                hashMap.put("UpdatedAt", listResponse.body().get(0).UpdatedAt());
                                hashMap.put("NuseQty", listResponse.body().get(0).NuseQty());
                                hashMap.put("MoId", listResponse.body().get(0).MoId());
                                hashMap.put("ItemName", listResponse.body().get(0).ItemName());
                                hashMap.put("OnlineDate", listResponse.body().get(0).OnlineDate());
                                hashMap.put("CompleteDate", listResponse.body().get(0).CompleteDate());
                                hashMap.put("Qty", listResponse.body().get(0).Qty());
                                hashMap.put("BomkeyName", listResponse.body().get(0).BomkeyName());
                                hashMap.put("UnitId", listResponse.body().get(0).UnitId());

                                Log.d("getAfterMfg", "onNext: " + listResponse.body().get(0).ItemId());
                                AfterarrayList.add(hashMap);
                            }
                            getAfterData.setAfterArrayList(AfterarrayList);
                            GetCurrentStage(sale_order,listResponse.body().get(0).ItemId(),sp.loadToken());
                        }
                        else{
                            Toast.makeText(activity, "查無資料", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("getAfterMfg", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getAfterMfg", "onComplete");
                    }
                });
    }

    public void GetCurrentStage(String sale_order,String item,String token){
        apiService.getCurrentStage(sale_order,item,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<CurrentStageResponse>>() {


                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull Response<CurrentStageResponse> listResponse) {
                        getCurrentStageData = GetCurrentStageData.getInstance();
                        CurrentStagearrayList.clear();

                        for(int i=0;i<1;i++) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("Num", String.valueOf(i+1));
                            hashMap.put("MoId", listResponse.body().MoId());
                            hashMap.put("ItemId", listResponse.body().ItemId());
                            hashMap.put("ItemName", listResponse.body().ItemName());
                            hashMap.put("CreatedAt", listResponse.body().CreatedAt());
                            hashMap.put("UpdatedAt", listResponse.body().UpdatedAt());
                            hashMap.put("SoId", listResponse.body().SoId());
                            hashMap.put("OnlineDate", listResponse.body().OnlineDate());
                            hashMap.put("CompleteDate", listResponse.body().CompleteDate());
                            hashMap.put("Qty", listResponse.body().Qty());
                            hashMap.put("Tech_routing_name", listResponse.body().TechRouteName());

                            Log.d("getCurrentStageMfg", "onNext: " + listResponse.body());
                            CurrentStagearrayList.add(hashMap);
                        }
                        getCurrentStageData.setCurrentStageArrayList(CurrentStagearrayList);
                        GetSaleOrder(sale_order,"","",token);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("getCurrentStage", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getCurrentStage", "onComplete");
                    }
                });
    }

    public void GetSaleOrder(String sale_order,String customer,String online_date,String token){
        apiService.getSaleOrder(sale_order,customer,online_date,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<SaleOrderResponse>>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull Response<List<SaleOrderResponse>> listResponse) {
                        getSaleOrder = GetSaleOrder.getInstance();
                        int size = listResponse.body().size();
                        SaleOrderarrayList.clear();

                        if(size != 0){
                            for(int i=0;i<size;i++){
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("Num", String.valueOf(i+1));
                                hashMap.put("Item_id",listResponse.body().get(0).getItem_id());
                                hashMap.put("Created_at",listResponse.body().get(0).getCreated_at());
                                hashMap.put("Updated_at",listResponse.body().get(0).getUpdated_at());
                                hashMap.put("Item",listResponse.body().get(0).getItem());
                                hashMap.put("Customer_name",listResponse.body().get(0).getCustomer_name());
                                hashMap.put("Qty",listResponse.body().get(0).getQty());
                                hashMap.put("Person_id",listResponse.body().get(0).getPerson_id());

                                SaleOrderarrayList.add(hashMap);
                            }
                            getSaleOrder.setSaleOrderArrayList(SaleOrderarrayList);
                        }
                        else{
                            Toast.makeText(activity, "查無資料", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("getSaleOrder", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getSaleOrder", "onComplete");
                    }
                });
    }
}
