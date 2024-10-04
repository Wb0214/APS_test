package com.example.aps_test.ui.second.schedule;

import java.util.ArrayList;

public interface ScheduleContract {
    interface view{
        void getData(ArrayList arrayList);
    }

    interface schedulepresenter{
        void Ans();
    }
}
