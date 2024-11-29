package com.example.aps_test.instance;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class GetROMData {
    private static GetROMData instance; // 單例實例變數
    private ArrayList<HashMap<String, String>> ROMArrayList = new ArrayList<HashMap<String, String>>();

    private GetROMData() {}

    public static GetROMData getInstance() {
        if (instance == null) {
            synchronized (GetROMData.class) { // 確保線程安全
                if (instance == null) {
                    instance = new GetROMData();
                }
            }
        }
        return instance;
    }

    public void setROMArrayList(ArrayList arrayList){
        Log.e("GetROMData", "setROMArrayList: " + arrayList);
        this.ROMArrayList = arrayList;
    }

    public ArrayList getROMArrayList(){
        return ROMArrayList;
    }

}
