package com.example.aps_test.instance;

import java.util.ArrayList;
import java.util.HashMap;

public class GetCurrentStageData {
    private static GetCurrentStageData instance;
    private ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    private GetCurrentStageData() {}

    public static GetCurrentStageData getInstance() {
        if (instance == null) {
            synchronized (GetPrevMfgData.class) { // 確保線程安全
                if (instance == null) {
                    instance = new GetCurrentStageData();
                }
            }
        }
        return instance;
    }

    public void setCurrentStageArrayList(ArrayList arrayList){
        this.arrayList = arrayList;
    }

    public ArrayList getCurrentStageArrayList(){
        return arrayList;
    }
}
