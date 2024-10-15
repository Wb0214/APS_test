package com.example.aps_test.ui.second.production.firstsearch;

public interface FirstSearchContract {
    interface view{
        void Get_customer_name(String person);
        void customer_name(String[] customerNames);
    }

    interface firstSearchPresenter{
        void Get_customerName(String customer_name,String token);
    }
}
