package com.example.aps_test.ui.main;

import java.util.regex.Pattern;

public class MainPresenter implements MainContract.mainpresenter{
    private MainContract.view callBake;
    private String str;

    public MainPresenter(MainContract.view view){
        this.callBake=view;
    }

    @Override
    public void getData(String account,String password) {
        boolean gDBefore = getDataBefore(account,password);
        if(gDBefore){
            callBake.correct();
        }
        else {
            callBake.isError(str);
        }
    }

    private boolean getDataBefore(String account,String password){
        boolean isEmpty = isEmpty(account) || isEmpty(password);
        if(isEmpty){
            str = "輸入不為空白";
            return false;
        }
        return true;
    }

    private boolean isEmpty(String s){
        return s == null || s.length() == 0;
    }
}
