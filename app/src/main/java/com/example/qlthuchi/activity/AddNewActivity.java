package com.example.qlthuchi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlthuchi.R;
import com.example.qlthuchi.adapter.DanhMucSpinnerAdapter;
import com.example.qlthuchi.adapter.LoaiTienSpinnerAdapter;
import com.example.qlthuchi.dao.DanhMucDAO;
import com.example.qlthuchi.dao.GiaoDichDAO;
import com.example.qlthuchi.dao.LoaiTienDAO;
import com.example.qlthuchi.models.DanhMuc;
import com.example.qlthuchi.models.GiaoDich;
import com.example.qlthuchi.models.GiaoDichViewModel;
import com.example.qlthuchi.models.LoaiTien;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddNewActivity extends AppCompatActivity {
    private Spinner spinner1;
    private Spinner spiner2;
    private DanhMucDAO danhmucdao;
    private LoaiTienDAO loaiTienDAO;
    private GiaoDichDAO giaoDichDAO;
    private EditText edtsotien;
    private EditText edtghichu;

    private TextView txtthoigian;
    private TextView txtdanhmuccanhan;
    List<DanhMuc> danhMucList;
    List<LoaiTien> loaiTien;
    DanhMucSpinnerAdapter adapter;
    Integer id;

    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        calendar = Calendar.getInstance();

        edtsotien = findViewById(R.id.edtnhapsotien);
        edtghichu = findViewById(R.id.edtghichu);
        txtthoigian = findViewById(R.id.txtthoigian);
        txtthoigian.setText(String.valueOf(getdatetime()));
        txtthoigian = findViewById(R.id.txtthoigian);
        txtthoigian.setText(String.valueOf(getdatetime()));
        spinner1 = findViewById(R.id.spinner);
        spiner2 = findViewById(R.id.spinner2);
        txtdanhmuccanhan = findViewById(R.id.txtdanhmuccanhan);



        txtdanhmuccanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewActivity.this, AddDanhMucActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        txtthoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("giaoDichsua")) {
           GiaoDich giaoDich = (GiaoDich) intent.getSerializableExtra("giaoDichsua");

            edtsotien.setText(giaoDich.getSoTien());
            edtghichu.setText(giaoDich.getGhiChu());
            txtthoigian.setText(giaoDich.getThoiGian());
            id = giaoDich.getId();
        }

        danhmucdao = new DanhMucDAO(this);
        loaiTienDAO = new LoaiTienDAO(this);
        giaoDichDAO = new GiaoDichDAO(this);
        danhMucList = danhmucdao.GetAll();
        loaiTien = loaiTienDAO.GetAll();

        // Sử dụng custom adapter
        adapter = new DanhMucSpinnerAdapter(this, danhMucList);
        LoaiTienSpinnerAdapter adapter1 = new LoaiTienSpinnerAdapter(this, loaiTien);
        spinner1.setAdapter(adapter);
        spiner2.setAdapter(adapter1);

        Button btnluu = findViewById(R.id.btnluu);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sotieninput = edtsotien.getText().toString();
                if(!sotieninput.isEmpty()){
                    GiaoDich giaoDich = new GiaoDich();
                    giaoDich.setSoTien(sotieninput);
                    giaoDich.setDanhMuc(spinner1.getSelectedItemPosition() + 1);
                    giaoDich.setPhuongTienThanhToan("");
                    giaoDich.setGhiChu(edtghichu.getText().toString());
                    giaoDich.setLoaitien(spiner2.getSelectedItemPosition() + 1);
                    giaoDich.setThoiGian(txtthoigian.getText().toString());
                    String tendanhmuc = danhMucList.get(spinner1.getSelectedItemPosition()).getTenDanhMuc();
                    String tenloaitien = loaiTien.get(spiner2.getSelectedItemPosition()).getTenLoaiTien();

                    giaoDich.setTenloaitien(tenloaitien);
                    giaoDich.setTendanhmuc(tendanhmuc);
                    if(id!=null){
                        giaoDich.setId(id);
                        giaoDichDAO.Update(giaoDich);
                    }else giaoDichDAO.Insert(giaoDich);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("giaoDich", giaoDich);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();


                }else{  Toast.makeText(AddNewActivity.this, "Vui lòng nhập số tiền", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public String getdatetime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime.toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                // Nhận dữ liệu từ ActivityB
                String tendanhmuc = data.getStringExtra("danhmuc");
                DanhMuc danhMuc= new DanhMuc();
                danhMuc.setId(danhMucList.size()+1);
                danhMuc.setTenDanhMuc(tendanhmuc);
                danhMucList.add(danhMuc);
                adapter.notifyDataSetChanged();

            }
        }
    }

    private void showDateTimePicker() {
        // Hiển thị DatePickerDialog để chọn ngày/tháng/năm
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        // Lưu giá trị ngày/tháng/năm khi người dùng chọn
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Hiển thị giá trị thời gian lên TextView (chỉ lấy ngày/tháng/năm)
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String dateTime = sdf.format(calendar.getTime());
                        txtthoigian.setText(dateTime);

                        // Ở đây bạn đã có được giá trị ngày/tháng/năm mà người dùng đã chọn,
                        // có thể sử dụng biến calendar để lưu trữ hoặc xử lí dữ liệu.
                        // Ví dụ: String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(calendar.getTime());
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


}