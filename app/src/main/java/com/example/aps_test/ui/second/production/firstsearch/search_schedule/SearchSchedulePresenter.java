package com.example.aps_test.ui.second.production.firstsearch.search_schedule;

import android.content.Context;
import android.util.Log;

import com.example.aps_test.api.ApiClient;
import com.example.aps_test.api.ApiService;
import com.example.aps_test.api.request.MoResponse;
import com.example.aps_test.sharedPreferences.SP;
import com.example.aps_test.ui.second.schedule.ScheduleContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class SearchSchedulePresenter implements SearchScheduleContract.searchSchedulepresenter{
    private SearchScheduleContract.view callback;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    //final static String[] companyname = {"霹靂啪啦股份有限公司", "Ben10不變天公司"};

    private Context context;
    private ApiClient apiClient;
    private ApiService apiService;
    private SP sp;

    public SearchSchedulePresenter(SearchScheduleContract.view view, Context context){
        this.callback = view;
        this.context = context;
        this.apiClient = new ApiClient();
        this.apiService = apiClient.LoginApi().create(ApiService.class);
        this.sp = new SP(context);
    }
    @Override
    public void getData(String customerName, String soId, String token) {
        apiService.getMfg(customerName,soId,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<MoResponse>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<List<MoResponse>> listResponse) {
                        int size = listResponse.body().size();

                        Log.d("getMfg", "onNext: " + size);
                        Log.d("getMfg", "onNext: " + soId);
                        Log.d("getMfg", "onNext: " + customerName);

                        for (int i = 0; i < size; i++) {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("Num", String.valueOf(i+1));
                            hashMap.put("mo_id", listResponse.body().get(i).MoId());
                            hashMap.put("so_id", listResponse.body().get(i).SoId());
                            hashMap.put("item_id", listResponse.body().get(i).ItemId());
                            hashMap.put("customer", listResponse.body().get(i).Customer());
                            hashMap.put("qty",String.format("　數量：") +listResponse.body().get(i).Qty());
                            hashMap.put("complete_date",String.format("結關日：")+listResponse.body().get(i).CompleteDate());
                            hashMap.put("online_date",String.format("上線日：")+listResponse.body().get(i).OnlineDate());
                            hashMap.put("related_tech_route", listResponse.body().get(i).TechRoutingName());
                            hashMap.put("takeEffect",String.format("生效"));
                            arrayList.add(hashMap);
                            Log.d("getMfg", "onNext: " + i);
                        }
                        Log.d("getMfg", "onNext: " + arrayList);
                        callback.Data(arrayList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("getMfg", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("getMfg", "onComplete");
                    }
                });

    }
}
