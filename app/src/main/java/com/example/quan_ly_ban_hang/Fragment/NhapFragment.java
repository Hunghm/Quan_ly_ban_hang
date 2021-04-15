package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Activity.NhapActivity;
import com.example.quan_ly_ban_hang.Adapter.AdapterNhapRecyclerView;
import com.example.quan_ly_ban_hang.Adapter.adapter_spinner_san_pham;
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
    AdapterNhapRecyclerView adapterNhapRecyclerView;
    HoaDonChiTietDAO donChiTietDAO;
    HoaDonDAO hoaDonDAO;
    SanPhamDAO sanPhamDAO;
    ArrayList<HoaDonChiTiet> listHoaDonChiTiet;
    ArrayList<HoaDon> listHoaDon;
    private ArrayList<SanPham> listSanPham;
    private SanPham sanPhamSelectedSpinner;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nhap_fragment, container, false);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_nhap);
        donChiTietDAO = new HoaDonChiTietDAO(getContext());
        hoaDonDAO = new HoaDonDAO(getContext());
        sanPhamDAO = new SanPhamDAO(getContext());

//        listHoaDonChiTiet = (ArrayList<HoaDonChiTiet>) donChiTietDAO.getAll();
        listHoaDon = (ArrayList<HoaDon>) hoaDonDAO.layTheoLoai("1");
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapterNhapRecyclerView = new AdapterNhapRecyclerView(getContext(), listHoaDon);
        recyclerView.setAdapter(adapterNhapRecyclerView);
//        Toast.makeText(getContext(), String.valueOf(listHoaDon.size()), Toast.LENGTH_SHORT).show();

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

                HoaDon hoaDon =listHoaDon.get(possion);
                HoaDonChiTiet hoaDonChiTiet = donChiTietDAO.getByIDHoaDon(String.valueOf(hoaDon.getMaHoaDon()));
                SanPham sanPham = sanPhamDAO.getID(String.valueOf(hoaDonChiTiet.getMaSanPham()));
                tvMaHD.setText(String.valueOf(hoaDon.getMaHoaDon()));
                tvTenSanPham.setText(sanPham.getTenSanPham());
                tvNgayNhap.setText(sdf.format(hoaDon.getNgayNhapXuat()));
                tvSoLuong.setText(String.valueOf(hoaDonChiTiet.getSoLuong()));
                tvHanLuuTru.setText(sdf.format(hoaDonChiTiet.getHanLuuTru()));
                Double thanhTien = hoaDonChiTiet.getSoLuong()*sanPham.getGiaNhap();
                tvThanhTien.setText(String.valueOf(thanhTien));

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }
        });

        adapterNhapRecyclerView.onClickDeleteListener(new AdapterNhapRecyclerView.onClickListener() {
            @Override
            public void onClick(int possion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có muốn xoá không ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int resultDel = hoaDonDAO.delete(String.valueOf(listHoaDon.get(possion).getMaHoaDon()));
                        if (resultDel>0){
                            reload();
                            adapterNhapRecyclerView.refresh((ArrayList) hoaDonDAO.layTheoLoai("1"));
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "ABCD", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getContext(), NhapActivity.class));
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.activity_nhap,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();

                EditText edSoSanPham = (EditText) layout.findViewById(R.id.ed_so_san_pham);
                EditText edHanLuuTru = (EditText) layout.findViewById(R.id.ed_han_luu_tru);
                Spinner spinnerSanPham = (Spinner) layout.findViewById(R.id.spinner_san_pham);
                Button btn_them = (Button) layout.findViewById(R.id.btn_add);

                listSanPham = (ArrayList<SanPham>) sanPhamDAO.getAll();
                Log.e("Listsanpham",String.valueOf(listSanPham.size()));
                adapter_spinner_san_pham adapter_spinner = new adapter_spinner_san_pham(getContext(), listSanPham);
                spinnerSanPham.setAdapter(adapter_spinner);
                spinnerSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        sanPhamSelectedSpinner = (SanPham) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (valiDate(edSoSanPham,edHanLuuTru)) {
                            HoaDon hoaDon = new HoaDon();
                            hoaDon.setLoaiHoaDon(1);
                            hoaDon.setNgayNhapXuat(Calendar.getInstance().getTime());
                            long resultHoaDon = hoaDonDAO.insert(hoaDon);
                            HoaDon hoaDon1 = hoaDonDAO.getHoaDonNew();

                            Calendar c = Calendar.getInstance();
                            c.setTime(Calendar.getInstance().getTime());
                            c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(edHanLuuTru.getText().toString()));
                            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                            hoaDonChiTiet.setHanLuuTru(c.getTime());
                            hoaDonChiTiet.setLoaiHoaDon(1);
                            hoaDonChiTiet.setMaSanPham(sanPhamSelectedSpinner.getMaSanPham());
                            hoaDonChiTiet.setMaHoaDon(hoaDon1.getMaHoaDon());
                            hoaDonChiTiet.setSoLuong(Integer.parseInt(edSoSanPham.getText().toString()));

                            long resultHoaDonChiTiet = donChiTietDAO.insert(hoaDonChiTiet);
                            if (resultHoaDonChiTiet > 0) {
                                adapterNhapRecyclerView.refresh((ArrayList) hoaDonDAO.layTheoLoai("1"));
                                reload();
                            }
                            alertDialog.dismiss();
                            Toast.makeText(getContext(), String.valueOf(resultHoaDonChiTiet), Toast.LENGTH_SHORT).show();
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

    public void reload(){
        listHoaDon.clear();
        listHoaDon.addAll( hoaDonDAO.layTheoLoai("1"));
    }

    public boolean valiDate(EditText... editTexts){
        for (int i=0; i<editTexts.length ;i++ ){
            if(editTexts[i].getText().toString().isEmpty()){
                return false;
            };
        }
        return true;
    }

//    public void them() {
//        Toast.makeText(getContext(), "bam vao add", Toast.LENGTH_SHORT).show();
//    }

}
