package com.example.aps_test.ui.second.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SchedulePresenter implements ScheduleContract.schedulepresenter{
    private ScheduleContract.view callback;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    final static String[] companyname = {"霹靂啪啦股份有限公司", "Ben10不變天公司"};

    public SchedulePresenter(ScheduleContract.view view){
        this.callback = view;
    }

    @Override
    public void Ans(){
        int min=10,j=10;
        //利用迴圈呼叫次將資料放入HashMap中
        for (int i = 0; i < 11; i++)
        {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Num", String.valueOf(i+1));
            String companyName = companyname[new Random().nextInt(2)];

            if(companyName == "霹靂啪啦股份有限公司"){
                hashMap.put("SearialNum1", String.format("1MO18120300")+String.valueOf(new Random().nextInt(99)));
                hashMap.put("SearialNum2", String.format("1SOA18113000")+String.valueOf(new Random().nextInt(99)));
                hashMap.put("Company1", String.format("F10318M-") +String.valueOf(new Random().nextInt(10)));
            }
            else{
                hashMap.put("SearialNum1", String.format("1MO18120400")+String.valueOf(new Random().nextInt(99)));
                hashMap.put("SearialNum2", String.format("1SOA18112700")+String.valueOf(new Random().nextInt(99)));
                hashMap.put("Company1", String.format("F260011ATN-")+String.valueOf(new Random().nextInt(10)));
            }

            hashMap.put("Company2", String.valueOf(companyName));
            hashMap.put("Quantity1",String.format("　數量：") +String.valueOf(new Random().nextInt(90)+1));
                    hashMap.put("Quantity2",String.format("結關日：2018-12-07"));
            hashMap.put("Job", String.format("一群-點焊"));

            min+=20;
            if(min>50){
                min=10;
                j++;
                hashMap.put("Data", String.format("計畫開始：" + String.valueOf(j)
                        + String.format(":") + String.valueOf(min)));
            }
            hashMap.put("Data", String.format("計畫開始：" + String.valueOf(j)
                    + String.format(":") + String.valueOf(min)));
            arrayList.add(hashMap);
        }
        callback.getData(arrayList);
    }
}
