package com.example.quan_ly_ban_hang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Adapter.AdapterNhapRecyclerView;
import com.example.quan_ly_ban_hang.Adapter.AdapterTopSpRecycler;
import com.example.quan_ly_ban_hang.DAO.HoaDonChiTietDAO;
import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.Model.Top;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;

public class BanChayFragment extends Fragment {

    RecyclerView recySPBanChay;
    AdapterTopSpRecycler adapterTopSpRecycler;
    ArrayList<Top> listTop;
    HoaDonChiTietDAO hoaDonChiTietDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ban_chay_fragment,container,false);
        recySPBanChay = (RecyclerView) view.findViewById(R.id.recycler_view_sp_ban_chay);

        hoaDonChiTietDAO = new HoaDonChiTietDAO(getContext());
        listTop = (ArrayList<Top>) hoaDonChiTietDAO.getTop();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recySPBanChay.setLayoutManager(llm);
        adapterTopSpRecycler = new AdapterTopSpRecycler(getContext(),listTop);
        recySPBanChay.setAdapter(adapterTopSpRecycler);
        Toast.makeText(getContext(), String.valueOf(listTop.size()), Toast.LENGTH_SHORT).show();

        return view;
    }
}
