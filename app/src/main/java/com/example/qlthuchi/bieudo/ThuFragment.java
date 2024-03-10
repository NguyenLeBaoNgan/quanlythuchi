package com.example.qlthuchi.bieudo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.qlthuchi.R;
import com.example.qlthuchi.adapter.ListThongKeAdapter;
import com.example.qlthuchi.dao.GiaoDichDAO;
import com.example.qlthuchi.models.DanhMucTongTien;
import com.example.qlthuchi.models.GiaoDich;
import com.example.qlthuchi.models.GiaoDichViewModel;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ThuFragment extends Fragment {
    PieChart pieChart;
    ListView listView2;
    ListView listView1;
    private GiaoDichDAO giaoDichDAO;
    private Button btnthongke;
    private Spinner monthspiner;
    private Spinner yearspiner;
    String selected;
    private GiaoDichViewModel giaoDichViewModel;

    public ThuFragment() {
        // Required empty public constructor
    }

    public static ThuFragment newInstance(String param1, String param2) {
        ThuFragment fragment = new ThuFragment();
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        giaoDichDAO = new GiaoDichDAO(getActivity());
        listView2= view.findViewById(R.id.list2);
        listView1= view.findViewById(R.id.list1);
        pieChart = view.findViewById(R.id.piechart);
        monthspiner =view.findViewById(R.id.monthSpinner);
        yearspiner = view.findViewById(R.id.yearSpinner);


        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                getMonthList()
        );

        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthspiner.setAdapter(monthAdapter);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                getYearList()
        );
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearspiner.setAdapter(yearAdapter);

        selected = getdatelocal();
        setData(selected);


        btnthongke = view.findViewById(R.id.btnthongke);
        btnthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected =monthspiner.getSelectedItem().toString()+"-"+yearspiner.getSelectedItem().toString();
                pieChart.clearChart();
                setData(selected);

            }
        });

        giaoDichViewModel = new ViewModelProvider(requireActivity()).get(GiaoDichViewModel.class);

        giaoDichViewModel.getLiveGiaoDichList().observe(getViewLifecycleOwner(), new Observer<List<GiaoDich>>() {
            @Override
            public void onChanged(List<GiaoDich> giaoDichList) {
                setData(selected);
            }
        });


        return view;
    }
    private void setData( String selected) {
        List<DanhMucTongTien> danhMucTongTiens = giaoDichDAO.GetTotalSotienThuByDanhmucNgay(selected);

        if(danhMucTongTiens.size()==0){
            DanhMucTongTien temp=   new DanhMucTongTien();
            temp.setTendanhmuc("Trống");
            temp.setTotalSotien(0);
            danhMucTongTiens.add(temp);
            pieChart.clearChart();

        }

        List<PieModel> pieModels = new ArrayList<>();

        // Tạo danh sách màu ngẫu nhiên cho biểu đồ
        List<Integer> randomColors = generateRandomColors(danhMucTongTiens.size());

        for (int i = 0; i < danhMucTongTiens.size(); i++) {
            DanhMucTongTien danhMucTongTien = danhMucTongTiens.get(i);
            int color = randomColors.get(i);

            pieModels.add(new PieModel(
                    danhMucTongTien.getTendanhmuc(),
                    (float) danhMucTongTien.getTotalSotien(),
                    color
            ));

            danhMucTongTien.setColor(color); // Lưu màu vào danh sách danhMucTongTiens
        }

        // Tạo adapter cho ListView
        ListThongKeAdapter adapter = new ListThongKeAdapter(getContext(), danhMucTongTiens);
        listView2.setAdapter(adapter);
        listView1.setAdapter(adapter);

        // Tạo và cấu hình biểu đồ tròn
        for (PieModel pieModel : pieModels) {
            pieChart.addPieSlice(pieModel);
        }
        pieChart.startAnimation();
    }

    private List<Integer> generateRandomColors(int count) {
        List<Integer> colors = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            int color = Color.rgb(r, g, b);
            colors.add(color);
        }

        return colors;
    }

    private List<String> getMonthList() {
        List<String> monthList = new ArrayList<>();
        for (int i = 0 ; i<12;i++){
            monthList.add(String.valueOf(i+1));
        }
        return monthList;
    }


    private List<String> getYearList() {
        List<String> yearList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = currentYear - 3; year <= currentYear; year++) {
            yearList.add(String.valueOf(year));
        }
        return yearList;
    }
    private  String getdatelocal(){
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);

        int defaultMonthPosition = currentMonth - 1;
        monthspiner.setSelection(defaultMonthPosition);

        int startYear = currentYear - 3;
        int defaultYearPosition = currentYear - startYear;
        yearspiner.setSelection(defaultYearPosition);
        String date = currentMonth+"-"+currentYear;
        return date;
    }


}