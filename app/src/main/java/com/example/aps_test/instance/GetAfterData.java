package com.example.aps_test.instance;

import java.util.ArrayList;
import java.util.HashMap;

public class GetAfterData {
    private static GetAfterData instance;
    private ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    private GetAfterData() {}

    public static GetAfterData getInstance() {
        if (instance == null) {
            synchronized (GetAfterData.class) { // 確保線程安全
                if (instance == null) {
                    instance = new GetAfterData();
                }
            }
        }
        return instance;
    }

    public void setAfterArrayList(ArrayList arrayList){
        this.arrayList = arrayList;
    }

    public ArrayList getAfterArrayList(){
        return arrayList;
    }

}
