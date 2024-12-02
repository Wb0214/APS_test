package com.example.aps_test.instance;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class GetSaleOrder {
    private static GetSaleOrder instance; // 單例實例變數
    private ArrayList<HashMap<String, String>> SaleOrderArrayList = new ArrayList<HashMap<String, String>>();

    private GetSaleOrder() {}

    public static GetSaleOrder getInstance() {
        if (instance == null) {
            synchronized (GetSaleOrder.class) { // 確保線程安全
                if (instance == null) {
                    instance = new GetSaleOrder();
                }
            }
        }
        return instance;
    }

    public void setSaleOrderArrayList(ArrayList arrayList){
        this.SaleOrderArrayList = arrayList;
        Log.d("setSaleOrderArrayList", "setSaleOrderArrayList: "+arrayList);
    }

    public ArrayList getSaleOrderArrayList(){
        Log.d("getSaleOrderArrayList", "getSaleOrderArrayList: "+SaleOrderArrayList);
        return SaleOrderArrayList;
    }
}
