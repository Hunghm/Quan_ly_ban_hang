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

import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;

public class adapter_spinner_san_pham extends ArrayAdapter<SanPham> {

    public adapter_spinner_san_pham(@NonNull Context context, ArrayList object) {
        super(context, 0, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        ViewHoder viewHoder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.san_pham_spinner_row,parent,false);
            viewHoder = new ViewHoder();
            viewHoder.imageViewFlag = convertView.findViewById(R.id.image_view_flag);
            viewHoder.textViewName = convertView.findViewById(R.id.text_view_name);
            convertView.setTag(viewHoder);
        }else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        SanPham currentItem = getItem(position);

        viewHoder.imageViewFlag.setImageResource(currentItem.getAnh());
        viewHoder.textViewName.setText(currentItem.getTenSanPham());
        return convertView;
    }
    public class ViewHoder{
        ImageView imageViewFlag;
        TextView textViewName;
    }
}
