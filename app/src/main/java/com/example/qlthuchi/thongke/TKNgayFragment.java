package com.example.qlthuchi.thongke;

import static android.app.Activity.RESULT_OK;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlthuchi.R;
import com.example.qlthuchi.activity.AddNewActivity;
import com.example.qlthuchi.adapter.ItemAdapter;
import com.example.qlthuchi.dao.GiaoDichDAO;
import com.example.qlthuchi.models.GiaoDich;
import com.example.qlthuchi.models.GiaoDichViewModel;
import com.example.qlthuchi.my_interface.clickitem;
import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TKNgayFragment extends Fragment {

    CalendarView calendar;
    TextView txtthu;
    TextView txtchi;
    TextView txttong;
    GiaoDichDAO giaoDichDAO;
    private RecyclerView recyclerView;
    private List<GiaoDich> data = new ArrayList<>();
    private ItemAdapter adapter;
    private GiaoDichViewModel giaoDichViewModel;
    String  selectedDate;
    public TKNgayFragment() {
        // Required empty public constructor
    }

    public static TKNgayFragment newInstance(String param1, String param2) {
        TKNgayFragment fragment = new TKNgayFragment();
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
        View view = inflater.inflate(R.layout.fragment_t_k_ngay, container, false);
        giaoDichDAO = new GiaoDichDAO(getContext());

        recyclerView = view.findViewById(R.id.rcvlichsu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        giaoDichDAO = new GiaoDichDAO(getActivity());
        txtchi = view.findViewById(R.id.txtchitieu);
        txtthu = view.findViewById(R.id.txtthunhap);
        txttong = view.findViewById(R.id.txttong);

        adapter = new ItemAdapter(data, new clickitem() {
            @Override
            public void OnClickItem(GiaoDich giaoDich) {
                String[] options = {"Sửa", "Xóa"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Tùy chọn");
                builder.setAdapter(new ArrayAdapter<String>(
                                           getContext(),
                                           R.layout.list_item_with_icon,
                                           R.id.textViewOption,
                                           options) {
                                       @NonNull
                                       @Override
                                       public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                           View view = super.getView(position, convertView, parent);
                                           ImageView iconImageView = view.findViewById(R.id.iconImageView);

                                           if (position == 0) {
                                               iconImageView.setImageResource(R.drawable.edit);
                                           } else if (position == 1) {
                                               iconImageView.setImageResource(R.drawable.delete);
                                           }

                                           return view;
                                       }
                                   },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                if (position == 0) {
                                    // Xử lý sự kiện sửa
                                    Intent intent = new Intent(getActivity(), AddNewActivity.class);
                                    intent.putExtra("giaoDichsua", giaoDich);
                                    startActivityForResult(intent, 1);
                                } else if (position == 1) {
                                    // Xử lý sự kiện xóa
                                    data.remove(giaoDich);
                                    giaoDichDAO.Delete(giaoDich.getId());
                                    giaoDichViewModel.setGiaoDichList(data);
                                }
                            }
                        });

                AlertDialog optionsDialog = builder.create();
                optionsDialog.show();

            }
        });
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL); // Chỉnh sửa: Truyền Context của Fragment vào đây
        recyclerView.addItemDecoration(itemDecoration);




        calendar = view.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                selectedDate = String.format("%02d-%02d-%04d", i2, i1 + 1, i);
                data.clear();
                adapter.notifyDataSetChanged();
                data = giaoDichDAO.GetGiaoDichByDate(selectedDate);

                adapter.setData(data);
                Integer thu = 0, chi = 0, tong = 0;
                for (int j = 0; j < data.size(); j++) {
                    GiaoDich temp = data.get(j);
                    String soTienStr = temp.getSoTien();

                    if (!soTienStr.isEmpty() && soTienStr.matches("-?\\d+")) {
                        int soTien = Integer.valueOf(soTienStr);

                        if (temp.getTenloaitien().equals("Thu")) {
                            thu += soTien;
                        } else {
                            chi += soTien;
                        }

                    }
                }
                tong = thu - chi;

                txtchi.setText(String.valueOf(formatCurrency(chi)));

                txttong.setText(String.valueOf(formatCurrency(tong)));

                txtthu.setText(String.valueOf(formatCurrency(thu)));

                // Thêm dữ liệu vào data ở đây


            }
        });
        selectedDate=getdatetime();
        List<GiaoDich> giaoDichList = giaoDichDAO.GetGiaoDichByDate(selectedDate);
        Integer thu = 0, chi = 0, tong = 0;
        for (int i = 0; i < giaoDichList.size(); i++) {
            GiaoDich temp = giaoDichList.get(i);
            String soTienStr = temp.getSoTien();

            if (!soTienStr.isEmpty() && soTienStr.matches("-?\\d+")) {
                int soTien = Integer.valueOf(soTienStr);


                if (temp.getTenloaitien().equals("Thu")) {
                    thu += soTien;
                } else {
                    chi += soTien;
                }

            }
        }
        tong = thu - chi;
        txtchi.setText(String.valueOf(formatCurrency(chi)));
        txttong.setText(String.valueOf(formatCurrency(tong)));
        txtthu.setText(String.valueOf(formatCurrency(thu)));
        adapter.setData(giaoDichList);
        adapter.notifyDataSetChanged();
        giaoDichViewModel = new ViewModelProvider(requireActivity()).get(GiaoDichViewModel.class);

        giaoDichViewModel.getLiveGiaoDichList().observe(getViewLifecycleOwner(), new Observer<List<GiaoDich>>() {
            @Override
            public void onChanged(List<GiaoDich> giaoDichList) {
                List<GiaoDich> filteredGiaoDichList = new ArrayList<>();
                for (GiaoDich giaoDich : giaoDichList) {
                    if (giaoDich.getThoiGian().equals(selectedDate)) {
                        filteredGiaoDichList.add(giaoDich);
                    }
                }
                data=filteredGiaoDichList;
                if (adapter != null ) {
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                }
            }
        });


        return  view;
    }
    public String getdatetime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime.toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent d) {
        super.onActivityResult(requestCode, resultCode, d);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (d != null && d.hasExtra("giaoDich")) {
                    GiaoDich giaoDich = (GiaoDich) d.getSerializableExtra("giaoDich");
                    int isid=0;
                    for(int i= 0 ; i<data.size();i++){
                        if( data.get(i).getId()==giaoDich.getId()){
                            isid=1;
                            giaoDichViewModel.editGiaoDich(giaoDich);
                            break;
                        }
                    }
                    if(isid==0){
                        giaoDich.setId(data.size()+1);
                        giaoDichViewModel.addGiaoDich(giaoDich);
                    }


                }
            }
        }
    }
    public String formatCurrency(int amount) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### đ");
        return decimalFormat.format(amount);
    }

}