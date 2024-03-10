package com.example.qlthuchi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlthuchi.R;
import com.example.qlthuchi.dao.GiaoDichDAO;
import com.example.qlthuchi.models.GiaoDich;
import com.example.qlthuchi.my_interface.clickitem;

import java.text.DecimalFormat;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<GiaoDich> listProduct;
    private GiaoDichDAO giaoDichDAO;
    private clickitem clickitem;
    public ItemAdapter(List<GiaoDich> listProduct,clickitem clickitem1) {
        this.listProduct = listProduct;
        this.clickitem=clickitem1;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // đọc xml layout file và chuyển đổi các thuộc tính của nó thành 1 View
        View monhocView = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(monhocView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GiaoDich p = listProduct.get(position);
        holder.txtdanhmuc.setText(p.getTendanhmuc());
        holder.txtngay.setText(p.getThoiGian());
        holder.txtghichu.setText(p.getGhiChu());
        holder.txtloai.setText(p.getTenloaitien());
        if (p.getTenloaitien().equals("Thu") || p.getTenloaitien().equals("Cho vay")) {
            holder.txtloai.setTextColor(Color.parseColor("#33B1DF")); // Mã màu đỏ
        } else if (p.getTenloaitien().equals("Chi")|| p.getTenloaitien().equals("Nợ")) {
            holder.txtloai.setTextColor(Color.parseColor("#F06428")); // Mã màu xanh lá cây
        }
        Long temp = Long.valueOf(p.getSoTien());
        String sotien = formatCurrency(temp);
        holder.txtsotien.setText(sotien);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickitem.OnClickItem(p);
            }
        });
    }
    public String formatCurrency(long amount) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,### đ");
        return decimalFormat.format(amount);
    }


    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public int deleteItem(int pos) {
        GiaoDich p = listProduct.get(pos);
        listProduct.remove(p);
        return p.getId();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtdanhmuc;
        TextView txtngay;
        TextView txtghichu;
        TextView txtloai;
        TextView txtsotien;
        // ImageButton btnthongtin;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdanhmuc = (TextView) itemView.findViewById(R.id.txtdanhmuc);
            txtngay = (TextView) itemView.findViewById(R.id.txtngay);
            txtghichu = (TextView) itemView.findViewById(R.id.txtghichu);
            txtloai = (TextView) itemView.findViewById(R.id.txtloai);
            txtsotien = (TextView) itemView.findViewById(R.id.txtsotien);
//            btnthongtin = (ImageButton)   itemView.findViewById(R.id.btnthongtin);

        }
    }
    public void setData(List<GiaoDich> newData) {
        listProduct = newData;
    }

}
