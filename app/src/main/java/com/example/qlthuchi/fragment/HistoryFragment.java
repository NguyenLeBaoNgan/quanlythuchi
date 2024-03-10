package com.example.qlthuchi.fragment;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlthuchi.R;

import com.example.qlthuchi.thongke.TkAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TkAdapter pagerAdapter;
    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        // Khởi tạo adapter và đặt adapter cho ViewPager
        pagerAdapter = new TkAdapter(getChildFragmentManager()); // Đổi tên cho phù hợp với tên adapter của bạn
        viewPager.setAdapter(pagerAdapter);

        // Kết nối TabLayout với ViewPager
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }

}