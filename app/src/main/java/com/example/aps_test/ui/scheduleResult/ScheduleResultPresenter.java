package com.example.aps_test.ui.scheduleResult;

public class ScheduleResultPresenter implements ScheduleResultContract.scheduleResultPresenter{
    private ScheduleResultContract.view callback;

    public ScheduleResultPresenter(ScheduleResultContract.view view){
        this.callback = view;
    }
}
