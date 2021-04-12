package com.example.quan_ly_ban_hang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Model.HoaDon;
import com.example.quan_ly_ban_hang.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterXuatRecyclerView extends RecyclerView.Adapter<AdapterXuatRecyclerView.View_holder> {

    private Context context;
    private List<HoaDon> list;
    private onClickListener listenner;
    private onClickListener DeleteListenner;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public AdapterXuatRecyclerView(Context context, List<HoaDon> list) {
        this.context = context;
        this.list = list;
//        Log.e("test","chay vao adapter");
    }

    public interface onClickListener{
        void onClick(int possion);
    }

    public void onClickItemListener(onClickListener listener){
        this.listenner= listener;
    }
    public void onClickDeleteListener(onClickListener listener){
        this.DeleteListenner= listener;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_nhap,null);
        return new View_holder(view, listenner, DeleteListenner);
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder holder, int position) {
        holder.tvMaHD.setText(String.valueOf(list.get(position).getMaHoaDon()));
        holder.tvNgayNhap.setText(sdf.format(list.get(position).getNgayNhapXuat()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class View_holder extends RecyclerView.ViewHolder{
        TextView tvMaHD,tvNgayNhap;
        ImageView imgClose;
        public View_holder(@NonNull View itemView, onClickListener listener,onClickListener deleteListener)  {
            super(itemView);
            tvMaHD = (TextView) itemView.findViewById(R.id.tv_value_ma_hd);
            tvNgayNhap = (TextView) itemView.findViewById(R.id.tv_value_ngay_nhap);
            imgClose = (ImageView) itemView.findViewById(R.id.imgClose);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null) {
                        listener.onClick(getAdapterPosition());
                    }
                }
            });

            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(deleteListener != null){
                        deleteListener.onClick(getAdapterPosition());
                    }
                }
            });

        }
    }
    public void refresh(ArrayList<HoaDon> arrayList){
        list.clear();
        list.addAll(arrayList);
        if (arrayList != null){
            notifyDataSetChanged();
        }
    }
}
