package com.example.aps_test.api;

import com.example.aps_test.api.request.LoginDataResponse;
import com.example.aps_test.api.request.LoginResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("auth/login")
    Observable<Response<LoginResponse>> getToken(
            @Query("account") String account,
            @Query("password") String password
    );

    @GET("auth/")
    Observable<LoginDataResponse> getLoginData(
            @Query("token") String token
    );

}
