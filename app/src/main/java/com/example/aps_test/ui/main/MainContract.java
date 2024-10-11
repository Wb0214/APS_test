package com.example.aps_test.ui.main;

public interface MainContract {
    interface view{
        void isError(String s);
        void GetToken();
        void correct();
    }

    interface mainpresenter{
        void getData(String account,String password);
        void getToken(String account,String password);
        void getLoginData(String token);
    }
}
