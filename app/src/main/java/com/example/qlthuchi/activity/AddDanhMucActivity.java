package com.example.qlthuchi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.qlthuchi.R;
import com.example.qlthuchi.adapter.DanhMucSpinnerAdapter;
import com.example.qlthuchi.dao.DanhMucDAO;
import com.example.qlthuchi.models.DanhMuc;

import java.util.List;

public class AddDanhMucActivity extends AppCompatActivity {
    private DanhMucDAO danhMucDAO;
    EditText edttendanhmuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_danh_muc);
        danhMucDAO = new DanhMucDAO(this);

        List<DanhMuc> danhMucList = danhMucDAO.GetAll();
        ListView listView = findViewById(R.id.listView);
        DanhMucSpinnerAdapter customAdapter = new DanhMucSpinnerAdapter(this, danhMucList);
        listView.setAdapter(customAdapter);

        edttendanhmuc = findViewById(R.id.edttendanhmuc);
        Button btnluu = findViewById(R.id.btnthemdanhmuc);



        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = edttendanhmuc.getText().toString();
                if(temp!=""){
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.setTenDanhMuc(temp);
                    danhMucDAO.Insert(danhMuc);
                    Intent intent = new Intent();
                    String userInput = danhMuc.getTenDanhMuc(); // Thay thế bằng dữ liệu người dùng thực sự nhập
                    intent.putExtra("danhmuc", temp);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}