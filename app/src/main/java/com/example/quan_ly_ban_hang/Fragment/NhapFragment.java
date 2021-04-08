package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Activity.NhapActivity;
import com.example.quan_ly_ban_hang.Adapter.AdapterNhapRecyclerView;
import com.example.quan_ly_ban_hang.DAO.HoaDonChiTietDAO;
import com.example.quan_ly_ban_hang.DAO.HoaDonDAO;
import com.example.quan_ly_ban_hang.DAO.SanPhamDAO;
import com.example.quan_ly_ban_hang.Model.HoaDon;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NhapFragment extends Fragment {

    FloatingActionButton btnAdd;
    RecyclerView recyclerView;
    HoaDonChiTietDAO donChiTietDAO;
    ArrayList<HoaDonChiTiet> listHoaDonChiTiet;
    AdapterNhapRecyclerView adapterNhapRecyclerView;
    HoaDonDAO hoaDonDAO;
    SanPhamDAO sanPhamDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nhap_fragment, container, false);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_nhap);
        donChiTietDAO = new HoaDonChiTietDAO(getContext());
        hoaDonDAO = new HoaDonDAO(getContext());
        sanPhamDAO = new SanPhamDAO(getContext());

        listHoaDonChiTiet = (ArrayList<HoaDonChiTiet>) donChiTietDAO.getAll();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapterNhapRecyclerView = new AdapterNhapRecyclerView(getContext(), listHoaDonChiTiet);
        recyclerView.setAdapter(adapterNhapRecyclerView);
        Toast.makeText(getContext(), String.valueOf(listHoaDonChiTiet.size()), Toast.LENGTH_SHORT).show();

        adapterNhapRecyclerView.onClickItemListener(new AdapterNhapRecyclerView.onClickListener() {
            @Override
            public void onClick(int possion) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog_detail_bill_nhap,null);
                TextView tvMaHD = (TextView) layout.findViewById(R.id.tv_value_mahd);
                TextView tvTenSanPham = (TextView) layout.findViewById(R.id.tv_value_san_pham);
                TextView tvNgayNhap = (TextView) layout.findViewById(R.id.tv_value_ngay_nhap);
                TextView tvSoLuong = (TextView) layout.findViewById(R.id.tv_value_so_san_pham);
                TextView tvHanLuuTru = (TextView) layout.findViewById(R.id.tv_value_han_luu_tru);
                TextView tvThanhTien = (TextView) layout.findViewById(R.id.tv_value_thanh_tien);

                HoaDonChiTiet hoaDonChiTiet = listHoaDonChiTiet.get(possion);
                HoaDon hoaDon = hoaDonDAO.getID(String.valueOf(hoaDonChiTiet.getMaHoaDon()));
                SanPham sanPham = sanPhamDAO.getID(String.valueOf(hoaDonChiTiet.getMaSanPham()));
                tvMaHD.setText(hoaDon.getMaHoaDon());
                tvTenSanPham.setText(sanPham.getTenSanPham());
                tvNgayNhap.setText(sdf.format(hoaDon.getNgayNhapXuat()));
                tvSoLuong.setText(hoaDonChiTiet.getSoLuong());
                tvHanLuuTru.setText(sdf.format(hoaDonChiTiet.getHanLuuTru()));
                Double thanhTien = hoaDonChiTiet.getSoLuong()*sanPham.getGiaNhap();
                tvThanhTien.setText(String.valueOf(thanhTien));

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "ABCD", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), NhapActivity.class));
//                LayoutInflater inflater = getLayoutInflater();
//                View layout = inflater.inflate(R.layout.activity_nhap,null);
//                EditText edNhap = (EditText) layout.findViewById(R.id.ed_ma_hd);
//                Button btn_them = (Button) layout.findViewById(R.id.btn_add);
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setView(layout);
//                AlertDialog alertDialog = builder.create();
//                btn_them.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        them();
//                    }
//                });
//                alertDialog.show();
            }
        });
        return view;
    }

    public void them() {

        Toast.makeText(getContext(), "bam vao add", Toast.LENGTH_SHORT).show();
    }

}
