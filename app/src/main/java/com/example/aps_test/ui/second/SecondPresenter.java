package com.example.aps_test.ui.second;

import com.example.aps_test.ui.main.MainContract;

public class SecondPresenter implements SecondContract.secondpresenter{
    private SecondContract.view callBack;
    private String str;

    public SecondPresenter(SecondContract.view view){
        this.callBack=view;
    }
}
