package com.example.aps_test.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public Retrofit LoginApi() {
        return new Retrofit.Builder() //初始化 Retrofit 的建構器，以便配置各種參數來定制 Retrofit 實例
                .baseUrl("http://10.20.1.2:9000/api/") // 設定 base URL
                .addConverterFactory(GsonConverterFactory.create()) //JSON 數據自動轉換為 Java 對象
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //支持 RxJava3 的非同步編程
                .build(); //返回一個 Retrofit 實例
    }


}