package com.example.quan_ly_ban_hang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.R;

public class BanChayFragment extends Fragment {

    RecyclerView recySPBanChay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ban_chay_fragment,container,false);
        recySPBanChay = (RecyclerView) view.findViewById(R.id.recycler_view_sp_ban_chay);

        return view;
    }
}
