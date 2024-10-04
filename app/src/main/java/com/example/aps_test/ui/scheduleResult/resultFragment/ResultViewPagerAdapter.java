package com.example.aps_test.ui.scheduleResult.resultFragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ResultViewPagerAdapter extends FragmentStateAdapter {
    private Context context;

    public ResultViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,Context context) {
        super(fragmentActivity);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){//依照索引值回傳不同的Fragment
            case 0:
                return new BeforeFragment(context);
            case 1:
                return new NowFragment(context);
            case 2:
                return new AfterFragment(context);
            case 3:
                return new AssemblyFragment(context);
            case 4:
                return new OrderFragment(context);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
