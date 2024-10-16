package com.example.aps_test.ui.second.production.firstsearch;

public interface FirstSearchContract {
    interface view{
        void Date();
        void Get_customer_name(String person);
        void customer_name(String[] customerNames);
        void showCustomerName(String[] customerNames);
        void Get_so_id(String id);
        void so_id(String[] so_id);
        void showSoId(String[] so_id);
    }

    interface firstSearchPresenter{
        void Get_customerName(String customer_name,String token);
        void Get_soId(String id,String token);
    }
}
