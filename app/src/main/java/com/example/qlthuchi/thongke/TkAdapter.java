package com.example.qlthuchi.thongke;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TkAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3; // Số lượng tab hoặc fragment trong ViewPager

    public TkAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TKNgayFragment(); // Thay thế bằng Fragment của bạn
            case 1:
                return new TKTuyChonFragment(); // Thay thế bằng Fragment của bạn

            default:
                return new TKNgayFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Ngày"; // Tiêu đề cho tab thứ nhất
            case 1:
                return "Tháng"; // Tiêu đề cho tab thứ hai
            default:
                return "Ngày";
        }
    }
}
