package com.example.quan_ly_ban_hang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quan_ly_ban_hang.Model.LoaiSanPham;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;

public class AdapterLoaiSPListView extends ArrayAdapter<LoaiSanPham> {

    private Context context;
    private int resource;
    private ArrayList<LoaiSanPham> arrContact;
    private AdapterNhapRecyclerView.onClickListener listenner;

    public interface onClickListener{
        void onClick(int possion);
    }

    public void onClickItemListener(AdapterNhapRecyclerView.onClickListener listener){
        this.listenner= listener;
    }

    public AdapterLoaiSPListView(@NonNull Context context, int resource, ArrayList<LoaiSanPham> arrContact) {
        super(context, resource, arrContact);
        this.context=context;
        this.resource= resource;
        this.arrContact = arrContact;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.row_item_loai_sp,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.tv =(TextView) convertView.findViewById(R.id.textView2);
            viewHolder.btn_xoa = (Button) convertView.findViewById(R.id.btn_xoa);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        LoaiSanPham contact = arrContact.get(position);
        viewHolder.tv.setText(contact.getTenLoai());

        viewHolder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenner != null){
                    listenner.onClick(position);
                }
            }
        });

        return convertView;
    }
    public class ViewHolder{
        TextView tv;
        Button btn_xoa;

    }
    public void refresh(ArrayList arrayList){
        arrContact.clear();
        arrContact.addAll(arrayList);
        if (arrayList != null){
            notifyDataSetChanged();
        }
    }
}
