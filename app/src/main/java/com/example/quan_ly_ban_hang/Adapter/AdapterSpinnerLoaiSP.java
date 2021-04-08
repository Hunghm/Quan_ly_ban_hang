package com.example.quan_ly_ban_hang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.quan_ly_ban_hang.Model.LoaiSanPham;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSpinnerLoaiSP extends ArrayAdapter<LoaiSanPham> {

    public AdapterSpinnerLoaiSP(@NonNull Context context, @NonNull ArrayList objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
    private View initView(int position, View convertView, ViewGroup parent){
        ViewHoder viewHoder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_loaisp_spinner,parent,false);
            viewHoder = new ViewHoder();
            viewHoder.textViewName = convertView.findViewById(R.id.tv_category);
            convertView.setTag(viewHoder);
        }else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        LoaiSanPham currentItem = getItem(position);

        viewHoder.textViewName.setText(currentItem.getTenLoai());
        return convertView;
    }
    public class ViewHoder{
        ImageView imageViewFlag;
        TextView textViewName;
    }
}
