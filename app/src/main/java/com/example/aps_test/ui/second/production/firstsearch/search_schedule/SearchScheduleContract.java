package com.example.aps_test.ui.second.production.firstsearch.search_schedule;

import java.util.ArrayList;

public interface SearchScheduleContract {
    interface view{
        void Data(ArrayList arrayList);
    }

    interface searchSchedulepresenter{
        void getData(String customerName, String soId, String token);
    }
}
