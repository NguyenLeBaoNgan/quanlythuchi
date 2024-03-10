package com.example.qlthuchi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.qlthuchi.R;
import com.example.qlthuchi.models.DanhMucTongTien;
import com.example.qlthuchi.models.GiaoDich;

import java.text.DecimalFormat;
import java.util.List;

public class ListThongKeAdapter extends ArrayAdapter<DanhMucTongTien> {
    private Context context;
    private List<DanhMucTongTien> danhMucTongTienList;

    public ListThongKeAdapter(Context context, List<DanhMucTongTien> danhMucTongTienList) {
        super(context, 0, danhMucTongTienList);
        this.context = context;
        this.danhMucTongTienList = danhMucTongTienList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_itemthongke_layout, parent, false);
        }

        DanhMucTongTien danhMucTongTien = danhMucTongTienList.get(position);

        TextView tvSotien = itemView.findViewById(R.id.tvSotien);
        TextView tvTendanhmuc = itemView.findViewById(R.id.tvTendanhmuc);

        if (parent.getId() == R.id.list1) {
            // Nếu ListView có id là list1, hiển thị màu nền và tên danh mục bên cạnh
            tvSotien.setVisibility(View.GONE);
            tvTendanhmuc.setText(danhMucTongTien.getTendanhmuc());
            tvTendanhmuc.setTextColor(ContextCompat.getColor(itemView.getContext(), android.R.color.white));

            itemView.setBackgroundColor(danhMucTongTien.getColor());
        } else {
            // Nếu không, hiển thị cả tên danh mục và tổng số tiền
            tvSotien.setVisibility(View.VISIBLE);
            String sotien = formatCurrency(danhMucTongTien.getTotalSotien());

            tvSotien.setText(sotien);
            tvTendanhmuc.setText(danhMucTongTien.getTendanhmuc());
            itemView.setBackgroundColor(Color.TRANSPARENT); // Đặt màu nền về mặc định
        }

        return itemView;
    }
    public String formatCurrency(int amount) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### đ");
        return decimalFormat.format(amount);
    }

}
