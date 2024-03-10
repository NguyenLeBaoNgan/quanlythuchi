package com.example.qlthuchi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlthuchi.models.LoaiTien;

import java.util.List;

public class LoaiTienSpinnerAdapter extends ArrayAdapter<LoaiTien> {
    private Context context;
    private List<LoaiTien> loaiTienList;

    public LoaiTienSpinnerAdapter(Context context, List<LoaiTien> loaiTienList) {
        super(context, 0, loaiTienList);
        this.context = context;
        this.loaiTienList = loaiTienList;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(android.R.id.text1);
        LoaiTien loaiTien = loaiTienList.get(position);
        String displayText = loaiTien.getTenLoaiTien(); // Chỉ lấy tên loại tiền
        textView.setText(displayText);

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
