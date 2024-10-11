package com.example.aps_test.ui.main;

import android.content.Context;
import android.util.Log;

import com.example.aps_test.api.ApiClient;
import com.example.aps_test.api.ApiService;
import com.example.aps_test.api.request.LoginDataResponse;
import com.example.aps_test.api.request.LoginResponse;
import com.example.aps_test.sharedPreferences.SP;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DefaultObserver;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;


public class MainPresenter implements MainContract.mainpresenter{
    private MainContract.view callBake;
    private String str;
    private Context context;

    private ApiClient apiClient;
    private ApiService apiService;
    private SP sp;

    public MainPresenter(MainContract.view view , Context context){
        this.callBake=view;
        this.context = context;
        this.apiClient = new ApiClient();
        this.apiService = apiClient.LoginApi().create(ApiService.class);
        this.sp = new SP(context);
    }

    @Override
    public void getData(String account,String password) {
        boolean gDBefore = getDataBefore(account,password);
        if(gDBefore){
            callBake.GetToken();
        }
        else {
            callBake.isError(str);
        }
    }

    private boolean getDataBefore(String account,String password){
        boolean isEmpty = isEmpty(account) || isEmpty(password);
        if(isEmpty){
            str = "輸入不為空白";
            return false;
        }
        return true;
    }

    private boolean isEmpty(String s){
        return s == null || s.length() == 0;
    }

    @Override
    public void getToken(String account,String password) {
        apiService.getToken(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response<LoginResponse>>() {
                    @Override
                    public void onNext(@NonNull Response<LoginResponse> loginResponse) {
                        if(loginResponse.body().gettoken() != null){
                            sp.saveToken(loginResponse.body().gettoken());
                            Log.d("TAG", "onNext: "+loginResponse.body().gettoken());
                        }
                        callBake.correct();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("test", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("test", "onComplete: ");
                    }
                });
    }

    @Override
    public void getLoginData(String token) {
        apiService.getLoginData(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<LoginDataResponse>() {
                    @Override
                    public void onNext(@NonNull LoginDataResponse loginDataResponse) {
                        if (sp.loadToken() != null) {
                            String account = loginDataResponse.getAccount();
                            String name = loginDataResponse.getName();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("test", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("test", "onComplete: ");
                    }
                });
    }

}