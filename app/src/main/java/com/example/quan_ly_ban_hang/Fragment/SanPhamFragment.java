package com.example.quan_ly_ban_hang.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Activity.ThemSanPhamActivity;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SanPhamFragment extends Fragment {

    RecyclerView recySanPham;
    FloatingActionButton btnadd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.san_pham_fragment,container,false);
        recySanPham = (RecyclerView) view.findViewById(R.id.recycler_view_san_pham);
        btnadd = (FloatingActionButton) view.findViewById(R.id.btn_add);
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
