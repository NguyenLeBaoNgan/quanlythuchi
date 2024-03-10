package com.example.qlthuchi.bieudo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.qlthuchi.thongke.TKNgayFragment;
import com.example.qlthuchi.thongke.TKTuyChonFragment;

public class BieuDoAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 3; // Số lượng tab hoặc fragment trong ViewPager

    public BieuDoAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ThuFragment(); // Thay thế bằng Fragment của bạn
            case 1:
                return new ChiFragment(); // Thay thế bằng Fragment của bạn
            case 2:
                return new Thu_ChiFragment(); // Thay thế bằng Fragment của bạn

            default:
                return new ThuFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Thu"; // Tiêu đề cho tab thứ nhất
            case 1:
                return "Chi"; // Tiêu đề cho tab thứ hai
            case 2:
                return "Thu-Chi"; // Tiêu đề cho tab thứ hai
            default:
                return "Thu";
        }
    }
}
