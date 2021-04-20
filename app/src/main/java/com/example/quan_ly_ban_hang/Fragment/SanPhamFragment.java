package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Activity.MainActivity;
import com.example.quan_ly_ban_hang.Activity.SuaSanPhamActivity;
import com.example.quan_ly_ban_hang.Activity.ThemSanPhamActivity;
import com.example.quan_ly_ban_hang.Adapter.AdapterSanPhamRecyclerView;
import com.example.quan_ly_ban_hang.Adapter.AdapterSpinnerLoaiSP;
import com.example.quan_ly_ban_hang.Adapter.ExampleAdapter;
import com.example.quan_ly_ban_hang.Adapter.adapter_spinner_san_pham;
import com.example.quan_ly_ban_hang.DAO.HoaDonChiTietDAO;
import com.example.quan_ly_ban_hang.DAO.LoaiSanPhamDAO;
import com.example.quan_ly_ban_hang.DAO.SanPhamDAO;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.LoaiSanPham;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SanPhamFragment extends Fragment {

    RecyclerView recySanPham;
    FloatingActionButton btnadd;
    List<SanPham> listSanPham = new ArrayList();
    SanPhamDAO sanPhamDAO;
    LoaiSanPhamDAO loaiSanPhamDAO;
    LoaiSanPham loaiSanPham;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.san_pham_fragment,container,false);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        sanPhamDAO = new SanPhamDAO(getContext());
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        recySanPham = (RecyclerView) view.findViewById(R.id.recycler_view_san_pham);
        btnadd = (FloatingActionButton) view.findViewById(R.id.btn_add);

        listSanPham = sanPhamDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recySanPham.setLayoutManager(linearLayoutManager);
        AdapterSanPhamRecyclerView adapterSanPhamRecy = new AdapterSanPhamRecyclerView(getContext(), listSanPham);
        recySanPham.setAdapter(adapterSanPhamRecy);

        adapterSanPhamRecy.onClickDeleteListener(new AdapterSanPhamRecyclerView.onClickListener() {
            @Override
            public void onClick(int possion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có muốn xoá không ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int resultDel = sanPhamDAO.delete(String.valueOf(listSanPham.get(possion).getMaSanPham()));
                        if (resultDel>0){
                            reloadList();
                            adapterSanPhamRecy.refresh((ArrayList) sanPhamDAO.getAll());
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        adapterSanPhamRecy.onClickItemListener(new AdapterSanPhamRecyclerView.onClickListener() {
            @Override
            public void onClick(int possion) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.dialog_detail_sanpham,null);
                TextView tvTenSanPham = (TextView) layout.findViewById(R.id.tv_value_ten_sp_dialog);
                TextView tvMaSanPham = (TextView) layout.findViewById(R.id.tv_value_ma_san_pham);
                TextView tvLoaiSanPham = (TextView) layout.findViewById(R.id.tv_loai_sanpham);
                TextView tvSoLuong = (TextView) layout.findViewById(R.id.tv_value_soluong_dialog);
                TextView tvGiaNhap = (TextView) layout.findViewById(R.id.tv_value_gia_nhap);
                TextView tvGiaXuat = (TextView) layout.findViewById(R.id.tv_value_gia_xuat);
                TextView tvGhiChu = (TextView) layout.findViewById(R.id.tv_ghichu);
                ImageView imgSua = (ImageView) layout.findViewById(R.id.img_sua);

                SanPham sanPham = listSanPham.get(possion);
                int soLuongXuat =  hoaDonChiTietDAO.getSoLuongXuatByMaSP(String.valueOf(sanPham.getMaSanPham()));
                int soLuongNhap =  hoaDonChiTietDAO.getSoLuongNhapByMaSP(String.valueOf(sanPham.getMaSanPham()));
                int soLuong = soLuongNhap - soLuongXuat;

                tvTenSanPham.setText(sanPham.getTenSanPham());
                tvMaSanPham.setText(String.valueOf(sanPham.getMaSanPham()));
                LoaiSanPham loaiSanPham = loaiSanPhamDAO.getID(String.valueOf(sanPham.getMaLoaiSanPham()));
                tvLoaiSanPham.setText(loaiSanPham.getTenLoai());
                tvSoLuong.setText(String.valueOf(soLuong));
                tvGiaNhap.setText(String.valueOf(sanPham.getGiaNhap()));
                tvGiaXuat.setText(String.valueOf(sanPham.getGiaXuat()));
                tvGhiChu.setText(sanPham.getGhiChu());
                imgSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), SuaSanPhamActivity.class);
                        intent.putExtra("idsp", sanPham.getMaSanPham());
                        startActivity(intent);
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ThemSanPhamActivity.class);
//                startActivity(intent);
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
                        if (valiDate(edTenSP,edGiaNhap,edGiaXuat)) {
                            SanPham sanPham = new SanPham();
                            sanPham.setTenSanPham(edTenSP.getText().toString());
                            sanPham.setGiaNhap(Double.parseDouble(edGiaNhap.getText().toString()));
                            sanPham.setGiaXuat(Double.parseDouble(edGiaXuat.getText().toString()));
                            sanPham.setGhiChu(edGhiChu.getText().toString());
                            sanPham.setAnh(R.drawable.img_sanpham);
                            sanPham.setMaLoaiSanPham(loaiSanPham.getMaLoai());
                            long result = sanPhamDAO.insert(sanPham);
                            reloadList();
                            adapterSanPhamRecy.refresh((ArrayList) sanPhamDAO.getAll());
                            alertDialog.dismiss();
                            Toast.makeText(getContext(), String.valueOf(result), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.show();
                
            }
        });
        return view;
    }

    public void reloadList(){
        listSanPham.clear();
        listSanPham.addAll( sanPhamDAO.getAll());
    }

    public boolean valiDate(EditText... editTexts){
        for (int i=0; i<editTexts.length ;i++ ){
            if(editTexts[i].getText().toString().isEmpty()){
                return false;
            };
        }
        return true;
    }

}
