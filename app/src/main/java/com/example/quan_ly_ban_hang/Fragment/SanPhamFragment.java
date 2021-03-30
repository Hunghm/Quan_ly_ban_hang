package com.example.quan_ly_ban_hang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Activity.MainActivity;
import com.example.quan_ly_ban_hang.Activity.ThemSanPhamActivity;
import com.example.quan_ly_ban_hang.Adapter.ExampleAdapter;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SanPhamFragment extends Fragment {

    RecyclerView recySanPham;
    FloatingActionButton btnadd;
    List listSanPham = new ArrayList();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.san_pham_fragment,container,false);
        recySanPham = (RecyclerView) view.findViewById(R.id.recycler_view_san_pham);
        btnadd = (FloatingActionButton) view.findViewById(R.id.btn_add);

        listSanPham.add(new SanPham("SP01","Tra Xanh 0 do",900000.0,1000000.0,"ghichu",R.drawable.img_sanpham));
        listSanPham.add(new SanPham("SP03","Tra Xanh 0 do",900000.0,100000.0,"ghichu",R.drawable.img_sanpham));
        listSanPham.add(new SanPham("SP04","Tra Xanh 0 do",900000.0,100000.0,"ghichu",R.drawable.img_sanpham));
        listSanPham.add(new SanPham("SP05","Tra Xanh 0 do",900000.0,100000.0,"ghichu",R.drawable.img_sanpham));
        listSanPham.add(new SanPham("SP06","Tra Xanh 0 do",900000.0,100000.0,"ghichu",R.drawable.img_sanpham));
        listSanPham.add(new SanPham("SP07","Tra Xanh 0 do",900000.0,100000.0,"ghichu",R.drawable.img_sanpham));
        listSanPham.add(new SanPham("SP08","Tra Xanh 0 do",900000.0,100000.0,"ghichu",R.drawable.img_sanpham));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recySanPham.setLayoutManager(linearLayoutManager);
        ExampleAdapter  exampleAdapter = new ExampleAdapter((ArrayList<SanPham>) listSanPham);
        recySanPham.setAdapter(exampleAdapter);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThemSanPhamActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
