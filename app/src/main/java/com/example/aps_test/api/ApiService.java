package com.example.aps_test.api;

import com.example.aps_test.api.response.AfterMfgResponse;
import com.example.aps_test.api.response.DataCustomerResponse;
import com.example.aps_test.api.response.DataSoIdResponse;
import com.example.aps_test.api.response.LoginDataResponse;
import com.example.aps_test.api.response.LoginResponse;
import com.example.aps_test.api.response.MoResponse;
import com.example.aps_test.api.response.PrevMfgResponse;
import com.example.aps_test.api.response.ROMResponse;

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

    @GET("get-prev-manufacture")
    Observable<Response<List<PrevMfgResponse>>> getPrevMfg(
            @Query("so_id") String so_id,
            @Query("item_id") String item_id,
            @Query("token") String token
    );

    @GET("get-current-stage-com")
    Observable<Response<List<ROMResponse>>> getBOM(
            @Query("item_id") String item_id,
            @Query("token") String token
    );

    @GET("get-next-part")
    Observable<Response<List<AfterMfgResponse>>> getAfterMfg(
            @Query("sale_order") String sale_order,
            @Query("id") String id,
            @Query("token") String token
    );


//    @GET("get-sale-order")
//    Observable<Response<List<DataResponse>>> getSaleOrder(
//            @Query("sale_order") String so_id,
//            @Query("customer") String customer,
//            @Query("online_date") String online_date,
//            @Query("token") String token
//    );
}
