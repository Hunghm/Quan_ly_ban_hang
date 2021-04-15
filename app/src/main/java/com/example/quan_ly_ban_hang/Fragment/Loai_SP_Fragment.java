package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_ban_hang.Activity.XuatActivity;
import com.example.quan_ly_ban_hang.Adapter.AdapterLoaiSPListView;
import com.example.quan_ly_ban_hang.Adapter.AdapterNhapRecyclerView;
import com.example.quan_ly_ban_hang.DAO.LoaiSanPhamDAO;
import com.example.quan_ly_ban_hang.Model.LoaiSanPham;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Loai_SP_Fragment extends Fragment {

    FloatingActionButton btnAdd;
    ListView listView ;
    LoaiSanPhamDAO loaiSanPhamDAO;
    ArrayList<LoaiSanPham> list ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loai_sp_fragment,container,false);
        loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
        listView = (ListView) view.findViewById(R.id.list_view);
        list = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
        AdapterLoaiSPListView adapter = new AdapterLoaiSPListView(getContext(),R.layout.row_item_loai_sp,list);
        listView.setAdapter(adapter);

        adapter.onClickItemListener(new AdapterNhapRecyclerView.onClickListener() {
            @Override
            public void onClick(int possion) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có muốn xoá không ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiSanPham loaiSanPham = list.get(possion);
                        loaiSanPhamDAO.delete(String.valueOf(loaiSanPham.getMaLoai()));
                        reloadList();
                        adapter.refresh((ArrayList) loaiSanPhamDAO.getAll());
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
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.loai_san_pham_add,null);
                EditText edTenSanPham = (EditText) layout.findViewById(R.id.ed_ten_san_pham);
                Button btnAdd = (Button) layout.findViewById(R.id.btn_add);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(layout);
                AlertDialog alertDialog = builder.create();
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (valiDate(edTenSanPham)) {
                            LoaiSanPham loaiSanPham = new LoaiSanPham();
                            loaiSanPham.setTenLoai(edTenSanPham.getText().toString());
                            long result = loaiSanPhamDAO.insert(loaiSanPham);
                            Toast.makeText(getContext(), String.valueOf(result), Toast.LENGTH_SHORT).show();
                            reloadList();
                            alertDialog.dismiss();
                            adapter.refresh((ArrayList) loaiSanPhamDAO.getAll());
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
        list.clear();
        list.addAll((ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll());
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
