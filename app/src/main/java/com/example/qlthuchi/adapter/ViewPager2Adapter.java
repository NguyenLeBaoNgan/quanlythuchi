package com.example.qlthuchi.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.qlthuchi.fragment.HistoryFragment;
import com.example.qlthuchi.fragment.HomeFragment;
import com.example.qlthuchi.fragment.NotificationFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position ==2){
            return new NotificationFragment();
        }if (position==1){
            return  new HistoryFragment();

        }else return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
