package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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

import com.example.quan_ly_ban_hang.Activity.NhapActivity;
import com.example.quan_ly_ban_hang.Activity.XuatActivity;
import com.example.quan_ly_ban_hang.Adapter.AdapterNhapRecyclerView;
import com.example.quan_ly_ban_hang.Adapter.AdapterXuatRecyclerView;
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
import java.util.Date;

public class XuatFragment extends Fragment {

    FloatingActionButton btnAdd;
    RecyclerView recyclerView;
    AdapterXuatRecyclerView adapterXuatRecyclerView;
    HoaDonDAO hoaDonDAO;
    SanPhamDAO sanPhamDAO;
    HoaDonChiTietDAO donChiTietDAO;
//    ArrayList<HoaDonChiTiet> listHoaDonChiTiet;
    ArrayList<HoaDon> listHoaDon;
    private ArrayList<SanPham> listSanPham;
    private SanPham sanPhamSelectedSpinner;
    Date ngayChon;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdfHienThi = new SimpleDateFormat("dd-MM-yyyy");
    int soLuongXuat =  0;
    int soLuongNhap =  0;
    int soLuongTrongKho = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xuat_fragment,container,false);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_xuat);
        donChiTietDAO = new HoaDonChiTietDAO(getContext());
        hoaDonDAO = new HoaDonDAO(getContext());
        sanPhamDAO = new SanPhamDAO(getContext());

        listHoaDon = (ArrayList<HoaDon>) hoaDonDAO.layTheoLoai("2");
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapterXuatRecyclerView = new AdapterXuatRecyclerView(getContext(), listHoaDon);
        recyclerView.setAdapter(adapterXuatRecyclerView);
//        Toast.makeText(getContext(), String.valueOf(listHoaDon.size()), Toast.LENGTH_SHORT).show();

        adapterXuatRecyclerView.onClickItemListener(new AdapterXuatRecyclerView.onClickListener() {
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
                TextView tvTitleNgayNhap = (TextView) layout.findViewById(R.id.tv_ngay_nhap);

                HoaDon hoaDon =listHoaDon.get(possion);
                HoaDonChiTiet hoaDonChiTiet = donChiTietDAO.getByIDHoaDon(String.valueOf(hoaDon.getMaHoaDon()));
//                HoaDonChiTiet hoaDonChiTiet = listHoaDonChiTiet.get(possion);
//                HoaDon hoaDon = hoaDonDAO.getID(String.valueOf(hoaDonChiTiet.getMaHoaDon()));
                SanPham sanPham = sanPhamDAO.getID(String.valueOf(hoaDonChiTiet.getMaSanPham()));
                tvMaHD.setText(String.valueOf(hoaDon.getMaHoaDon()));
                tvTenSanPham.setText(sanPham.getTenSanPham());
                tvNgayNhap.setText(sdf.format(hoaDon.getNgayNhapXuat()));
                tvSoLuong.setText(String.valueOf(hoaDonChiTiet.getSoLuong()));
                tvHanLuuTru.setText(sdf.format(hoaDonChiTiet.getHanLuuTru()));
                tvTitleNgayNhap.setText("Ng??y xu???t");
                Double thanhTien = hoaDonChiTiet.getSoLuong()*sanPham.getGiaXuat();
                tvThanhTien.setText(String.valueOf(thanhTien));

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }
        });

        adapterXuatRecyclerView.onClickDeleteListener(new AdapterXuatRecyclerView.onClickListener() {
            @Override
            public void onClick(int possion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("B???n c?? mu???n xo?? kh??ng ?");
                builder.setCancelable(false);
                builder.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int resultDel = hoaDonDAO.delete(String.valueOf(listHoaDon.get(possion).getMaHoaDon()));
                        donChiTietDAO.delete(String.valueOf(listHoaDon.get(possion).getMaHoaDon()));
                        if (resultDel>0){
                            reload();
                            adapterXuatRecyclerView.refresh((ArrayList) hoaDonDAO.layTheoLoai("2"));
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Kh??ng ?????ng ??", new DialogInterface.OnClickListener() {
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
                View layout = inflater.inflate(R.layout.activity_xuat,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();

                ImageView imgNgayChon = (ImageView) layout.findViewById(R.id.img_ngay_chon);
                TextView tvNgayChon = (TextView) layout.findViewById(R.id.tv_ngay_chon);
                TextView tvValueSoLuong = (TextView) layout.findViewById(R.id.tv_value_so_luong);
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
                        soLuongXuat =  donChiTietDAO.getSoLuongXuatByMaSP(String.valueOf(sanPhamSelectedSpinner.getMaSanPham()));
                        soLuongNhap =  donChiTietDAO.getSoLuongNhapByMaSP(String.valueOf(sanPhamSelectedSpinner.getMaSanPham()));
                        soLuongTrongKho = soLuongNhap - soLuongXuat;
                        tvValueSoLuong.setText(String.valueOf(soLuongTrongKho));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                Calendar calendar = Calendar.getInstance();
                imgNgayChon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int year = calendar.get(calendar.YEAR);
                        int month = calendar.get(calendar.MONTH);
                        int day = calendar.get(calendar.DAY_OF_MONTH);
                        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calendar.set(java.util.Calendar.YEAR, year);
                                calendar.set(java.util.Calendar.MONTH, monthOfYear);
                                calendar.set(java.util.Calendar.DAY_OF_MONTH, dayOfMonth);
                                ngayChon = calendar.getTime();
                                tvNgayChon.setText(sdfHienThi.format(ngayChon));
                            }
                        };
// Create DatePickerDialog (Spinner Mode):
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
// Show
                        datePickerDialog.show();
                    }
                });

                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (valiDate(edSoSanPham,edHanLuuTru)) {
                            int soLuong = soLuongTrongKho - Integer.parseInt(edSoSanPham.getText().toString());
                            if (soLuong>=0) {
                                HoaDon hoaDon = new HoaDon();
                                hoaDon.setLoaiHoaDon(2);
                                if (ngayChon==null) {
                                    hoaDon.setNgayNhapXuat(Calendar.getInstance().getTime());
                                }else {
                                    hoaDon.setNgayNhapXuat(ngayChon);
                                }
                                long resultHoaDon = hoaDonDAO.insert(hoaDon);
//                                HoaDon hoaDon1 = hoaDonDAO.getHoaDonNew();

                                Calendar c = Calendar.getInstance();
                                c.setTime(Calendar.getInstance().getTime());
                                c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(edHanLuuTru.getText().toString()));
                                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                                hoaDonChiTiet.setHanLuuTru(c.getTime());
                                hoaDonChiTiet.setLoaiHoaDon(2);
                                hoaDonChiTiet.setMaSanPham(sanPhamSelectedSpinner.getMaSanPham());
                                hoaDonChiTiet.setMaHoaDon((int) resultHoaDon);
                                hoaDonChiTiet.setSoLuong(Integer.parseInt(edSoSanPham.getText().toString()));

                                long resultHoaDonChiTiet = donChiTietDAO.insert(hoaDonChiTiet);
                                if (resultHoaDonChiTiet > 0) {
                                    adapterXuatRecyclerView.refresh((ArrayList) hoaDonDAO.layTheoLoai("2"));
                                    Toast.makeText(getContext(), "Th??m h??a ????n xu???t th??nh c??ng", Toast.LENGTH_SHORT).show();
                                    reload();
                                }
                                alertDialog.dismiss();
                            }else {
                                Toast.makeText(getContext(), "Trong kho kh??ng ?????", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getContext(), "??i???n ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
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
        listHoaDon.addAll( hoaDonDAO.layTheoLoai("2"));
    }

    public boolean valiDate(EditText... editTexts){
        for (int i=0; i<editTexts.length ;i++ ){
            if(editTexts[i].getText().toString().isEmpty()){
                return false;
            };
        }
        return true;
    }

    public boolean valiDateSoLuong(EditText editText, int maSP){
        int soLuongXuat =  donChiTietDAO.getSoLuongXuatByMaSP(String.valueOf(maSP));
        int soLuongNhap =  donChiTietDAO.getSoLuongNhapByMaSP(String.valueOf(maSP));
        int soLuongTrongKho = soLuongNhap - soLuongXuat;
        int soLuong = soLuongTrongKho - Integer.parseInt(editText.getText().toString());
        if (soLuong<0){
            return false;
        }
        return true;
    }

    private void them() {

    }
}
