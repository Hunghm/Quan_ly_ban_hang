package com.example.quan_ly_ban_hang.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Model.HoaDonChiTiet;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterNhapRecyclerView extends RecyclerView.Adapter<AdapterNhapRecyclerView.View_holder> {

    private Context context;
    private List<HoaDonChiTiet> list;
    private onClickListener listenner;
    private onClickListener DeleteListenner;

    public AdapterNhapRecyclerView(Context context, List<HoaDonChiTiet> list) {
        this.context = context;
        this.list = list;
        Log.e("test","chay vao adapter");
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
        holder.tvMiniNews.setText(String.valueOf(list.get(position).getMaHoaDonChiTiet()));
        holder.tvTitle.setText(String.valueOf(list.get(position).getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class View_holder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvMiniNews;
        ImageView imgClose;
        public View_holder(@NonNull View itemView, onClickListener listener,onClickListener deleteListener)  {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_value_ma_hdct);
            tvMiniNews = (TextView) itemView.findViewById(R.id.tv_value_mahd);
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
    public void refresh(ArrayList arrayList){
        list.clear();
        list.addAll(arrayList);
        if (arrayList != null){
            notifyDataSetChanged();
        }
    }
}
