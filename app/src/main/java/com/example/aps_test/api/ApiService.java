package com.example.aps_test.api;

import com.example.aps_test.api.request.DataCustomerResponse;
import com.example.aps_test.api.request.DataSoIdResponse;
import com.example.aps_test.api.request.LoginDataResponse;
import com.example.aps_test.api.request.LoginResponse;
import com.example.aps_test.api.request.MoResponse;

import java.util.List;

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

    @GET("app-search-customer")
    Observable<Response<List<DataCustomerResponse>>> getCustomer(
            @Query("customer_name") String customer_name,
            @Query("token") String token
    );

    @GET("app-search-so")
    Observable<Response<List<DataSoIdResponse>>> getOrder(
            @Query("so_id") String so_id,
            @Query("token") String token
    );

    @GET("get-manufacture")
    Observable<Response<List<MoResponse>>> getMfg(
            @Query("customer") String customer,
            @Query("sale_order") String sale_order,
            @Query("token") String token
    );
}
