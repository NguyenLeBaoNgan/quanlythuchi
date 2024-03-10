package com.example.qlthuchi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlthuchi.models.DanhMuc;

import java.util.List;

public class DanhMucSpinnerAdapter extends ArrayAdapter<DanhMuc> {
    private Context context;
    private List<DanhMuc> danhMucList;

    public DanhMucSpinnerAdapter(Context context, List<DanhMuc> danhMucList) {
        super(context, 0, danhMucList);
        this.context = context;
        this.danhMucList = danhMucList;
    }
    @Override
    public int getCount() {
        return danhMucList.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = view.findViewById(android.R.id.text1);
        DanhMuc danhMuc = danhMucList.get(position);
        String displayText = danhMuc.getTenDanhMuc(); // Chỉ lấy tên danh mục
        textView.setText(displayText);

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
