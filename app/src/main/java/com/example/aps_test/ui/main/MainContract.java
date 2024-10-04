package com.example.aps_test.ui.main;

public interface MainContract {
    interface view{
        void isError(String s);
        void correct();
    }

    interface mainpresenter{
        void getData(String account,String password);
    }
}
