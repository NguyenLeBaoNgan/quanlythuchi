package com.example.qlthuchi.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.ImageView;


import com.example.qlthuchi.activity.AddNewActivity;
import com.example.qlthuchi.R;
import com.example.qlthuchi.adapter.ItemAdapter;
import com.example.qlthuchi.dao.GiaoDichDAO;
import com.example.qlthuchi.models.GiaoDich;
import com.example.qlthuchi.models.GiaoDichViewModel;
import com.example.qlthuchi.my_interface.clickitem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private  GiaoDichDAO giaoDichDAO;
    Button btnloc;
    private GiaoDichViewModel giaoDichViewModel;

    private static final int ADD_NEW_REQUEST_CODE = 1;

    private List<GiaoDich> data = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        giaoDichViewModel = new ViewModelProvider(requireActivity()).get(GiaoDichViewModel.class);
        giaoDichViewModel.getLiveGiaoDichList().observe(getViewLifecycleOwner(), new Observer<List<GiaoDich>>() {
            @Override
            public void onChanged(List<GiaoDich> giaoDichList) {
                adapter.setData(giaoDichList);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView = view.findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        giaoDichDAO = new GiaoDichDAO(getActivity());
        data=giaoDichDAO.GetAll();
        giaoDichViewModel.setGiaoDichList(data);
        btnloc = view.findViewById(R.id.btnchi);
        EditText editText = view.findViewById(R.id.editloc);

        btnloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<GiaoDich> thu = new ArrayList();
               String textloc= editText.getText().toString();
                if (textloc.isEmpty()){
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                }else{
                    for (GiaoDich a : data) {
                        if(a.getTenloaitien().equalsIgnoreCase(textloc) || a.getTendanhmuc().equalsIgnoreCase(textloc)){
                            thu.add(a);
                        }

                    }

                    adapter.setData(thu);
                    adapter.notifyDataSetChanged();
                }


            }
        });


        // Thêm dữ liệu vào data ở đây

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
        FloatingActionButton btnaddnew = view.findViewById(R.id.btnaddnew);
        btnaddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GiaoDich giaoDich = new GiaoDich();
                Intent intent = new Intent(getActivity() , AddNewActivity.class);
                intent.putExtra("giaoDich", giaoDich);
                startActivityForResult(intent, 1);

            }
        });
        return view;
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

}

