package com.example.aps_test.instance;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class GetCurrentStageData {
    private static GetCurrentStageData instance;
    private ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    private GetCurrentStageData() {}

    public static GetCurrentStageData getInstance() {
        if (instance == null) {
            synchronized (GetCurrentStageData.class) { // 確保線程安全
                if (instance == null) {
                    instance = new GetCurrentStageData();
                }
            }
        }
        return instance;
    }

    public void setCurrentStageArrayList(ArrayList arrayList){
        this.arrayList = arrayList;
        Log.d("setCurrentStageArrayList", "setCurrentStageArrayList: "+arrayList);
    }

    public ArrayList getCurrentStageArrayList(){
        Log.d("getCurrentStageArrayList", "getCurrentStageArrayList: "+arrayList);
        return arrayList;
    }
}
