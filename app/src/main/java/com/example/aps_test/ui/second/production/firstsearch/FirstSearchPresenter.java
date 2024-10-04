package com.example.aps_test.ui.second.production.firstsearch;

public class FirstSearchPresenter implements FirstSearchContract.firstSearchPresenter{
    private FirstSearchContract.view callback;

    public FirstSearchPresenter(FirstSearchContract.view view){
        this.callback = view;
    }

}
