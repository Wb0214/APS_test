package com.example.aps_test.ui.second;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.aps_test.ui.second.production.ProductionFragment;
import com.example.aps_test.ui.second.schedule.ScheduleFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private Context context;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, Context context) {
        super(fragmentActivity);
        this.context = context;
    }

    @NonNull
    @Override
    //這裡是用來建立Fragment的，會利用得到的position，去新建一個Fragment
    public Fragment createFragment(int position) {
        switch (position){//依照索引值回傳不同的Fragment
            case 0:
                return new ProductionFragment(context);
            case 1:
                return new ScheduleFragment(context);
            case 2:
                return new Fragment();
            default:
                return null;
        }
    }

    @Override
    //這裡是在回傳有多少個Fragment
    public int getItemCount() {
        return 3;
    }
}
