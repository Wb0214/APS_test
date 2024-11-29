package com.example.aps_test.instance;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class GetPrevMfgData {
    private static GetPrevMfgData instance; // 單例實例變數

//    public String mo_id,so_id,
//            item_id,item_name,
//            online_date,qty,tech_routing_name;

    private ArrayList<HashMap<String, String>> PrevMfgArrayList = new ArrayList<HashMap<String, String>>();

    private GetPrevMfgData() {}

    public static GetPrevMfgData getInstance() {
        if (instance == null) {
            synchronized (GetPrevMfgData.class) { // 確保線程安全
                if (instance == null) {
                    instance = new GetPrevMfgData();
                }
            }
        }
        return instance;
    }

    public void setPrevMfgArrayList(ArrayList arrayList){
        this.PrevMfgArrayList = arrayList;
        Log.d("setPrevMfgArrayList", "setPrevMfgArrayList: "+arrayList);
    }

    public ArrayList getPrevMfgArrayList(){
        Log.d("getPrevMfgArrayList", "getPrevMfgArrayList: "+PrevMfgArrayList);
        return PrevMfgArrayList;
    }

//    public void setMoId(String mo_id){
//        this.mo_id = mo_id;
//    }
//    public String getMoId() {
//        return mo_id;
//    }
//
//    public void setSoId(String so_id){
//        this.so_id = so_id;
//    }
//    public String getSoId() {
//        return so_id;
//    }
//
//    public void setItemId(String item_id){
//        this.item_id = item_id;
//    }
//    public String getItemId() {
//        return item_id;
//    }
//
//    public void setItemName(String item_name){
//        this.item_name = item_name;
//    }
//    public String getItemName() {
//        return item_name;
//    }
//
//    public void setOnlineDate(String online_date){
//        this.online_date = online_date;
//    }
//    public String getOnlineDate() {
//        return online_date;
//    }
//
//    public void setQty(String qty){
//        this.qty = qty;
//    }
//    public String getQty() {
//        return qty;
//    }
//
//    public void setTechRoutingName(String tech_routing_name){
//        this.tech_routing_name = tech_routing_name;
//    }
//    public String getTechRoutingName() {
//        return tech_routing_name;
//    }
}
