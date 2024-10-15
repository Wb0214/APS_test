package com.example.aps_test.ui.second.production.firstsearch;

import android.content.Context;
import android.util.Log;

import com.example.aps_test.api.ApiClient;
import com.example.aps_test.api.ApiService;
import com.example.aps_test.api.request.DataResponse;
import com.example.aps_test.sharedPreferences.SP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class FirstSearchPresenter implements FirstSearchContract.firstSearchPresenter{
    private FirstSearchContract.view callback;
    private Context context;

    private ApiClient apiClient;
    private ApiService apiService;
    private SP sp;

    public FirstSearchPresenter(FirstSearchContract.view view, Context context){
        this.callback = view;
        this.context = context;
        this.apiClient = new ApiClient();
        this.apiService = apiClient.LoginApi().create(ApiService.class);
        this.sp = new SP(context);
    }

    @Override
    public void Get_customerName(String customer_name,String token){
        apiService.getCustomer(customer_name, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response<List<DataResponse>>>() {
                    @Override
                    public void onNext(@NonNull Response<List<DataResponse>> listResponse) {
                        if (sp.loadToken() == token) {
                            int size = listResponse.body().size();
                            String[] customerNamesList = new String[size];
                            for (int i = 0; i < size; i++) {
                                Log.e("PER",customer_name);
                                customerNamesList[i] = listResponse.body().get(i).getCustomer_name();
                                Log.d("TAG", "onNext: " + customerNamesList);
                                callback.customer_name(customerNamesList);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("test", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("test", "onComplete: ");
                    }
                });
    }

}
