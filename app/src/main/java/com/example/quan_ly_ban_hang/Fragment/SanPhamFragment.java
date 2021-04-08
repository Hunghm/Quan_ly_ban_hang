package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Activity.MainActivity;
import com.example.quan_ly_ban_hang.Activity.ThemSanPhamActivity;
import com.example.quan_ly_ban_hang.Adapter.AdapterSanPhamRecyclerView;
import com.example.quan_ly_ban_hang.Adapter.AdapterSpinnerLoaiSP;
import com.example.quan_ly_ban_hang.Adapter.ExampleAdapter;
import com.example.quan_ly_ban_hang.Adapter.adapter_spinner_san_pham;
import com.example.quan_ly_ban_hang.DAO.LoaiSanPhamDAO;
import com.example.quan_ly_ban_hang.DAO.SanPhamDAO;
import com.example.quan_ly_ban_hang.Model.LoaiSanPham;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SanPhamFragment extends Fragment {

    RecyclerView recySanPham;
    FloatingActionButton btnadd;
    List listSanPham = new ArrayList();
    SanPhamDAO sanPhamDAO;
    LoaiSanPhamDAO loaiSanPhamDAO;
    LoaiSanPham loaiSanPham;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.san_pham_fragment,container,false);
        sanPhamDAO = new SanPhamDAO(getContext());
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        recySanPham = (RecyclerView) view.findViewById(R.id.recycler_view_san_pham);
        btnadd = (FloatingActionButton) view.findViewById(R.id.btn_add);

        listSanPham = sanPhamDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recySanPham.setLayoutManager(linearLayoutManager);
        AdapterSanPhamRecyclerView adapterSanPhamRecy = new AdapterSanPhamRecyclerView(getContext(), listSanPham);
        recySanPham.setAdapter(adapterSanPhamRecy);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ThemSanPhamActivity.class);
//                startActivity(intent);
                Log.e("abc","chay vao day");
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.activity_them_san_pham,null);
                EditText edTenSP = (EditText) layout.findViewById(R.id.ed_ten_sp_add);
                EditText edGiaNhap = (EditText) layout.findViewById(R.id.ed_gia_nhap_add);
                EditText edGiaXuat = (EditText) layout.findViewById(R.id.ed_gia_xuat_add);
                EditText edGhiChu = (EditText) layout.findViewById(R.id.ed_ghi_chu_add);
                Spinner spinnerLoaiSP = (Spinner) layout.findViewById(R.id.spn_loai_sp_add);
                Button btn_them = (Button) layout.findViewById(R.id.btn_add);

                ArrayList<LoaiSanPham> listLoaiSP = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();

                AdapterSpinnerLoaiSP adapter_spinner = new AdapterSpinnerLoaiSP(getContext(),listLoaiSP);
                spinnerLoaiSP.setAdapter(adapter_spinner);
                spinnerLoaiSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        loaiSanPham = (LoaiSanPham) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();
                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SanPham sanPham = new SanPham();
                        sanPham.setTenSanPham(edTenSP.getText().toString());
                        sanPham.setGiaNhap(Double.parseDouble(edGiaNhap.getText().toString()));
                        sanPham.setGiaXuat(Double.parseDouble(edGiaXuat.getText().toString()));
                        sanPham.setGhiChu(edGhiChu.getText().toString());
                        sanPham.setAnh(R.drawable.san_pham_icon);
                        sanPham.setMaLoaiSanPham(loaiSanPham.getMaLoai());
                        long result = sanPhamDAO.insert(sanPham);
                        Toast.makeText(getContext(), String.valueOf(result), Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
                
            }
        });
        return view;
    }

    private void them() {

    }
}
