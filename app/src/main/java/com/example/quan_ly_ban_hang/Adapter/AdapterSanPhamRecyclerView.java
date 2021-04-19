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
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterSanPhamRecyclerView extends RecyclerView.Adapter<AdapterSanPhamRecyclerView.View_holder> {

    private Context context;
    private List<SanPham> list;
    private onClickListener listenner;
    private onClickListener deleteListenner;

    public AdapterSanPhamRecyclerView(Context context, List<SanPham> list) {
        this.context = context;
        this.list = list;
    }

    public interface onClickListener {
        void onClick(int possion);
    }

    public void onClickItemListener(onClickListener listener) {
        this.listenner = listener;
    }

    public void onClickDeleteListener(onClickListener listener) {
        this.deleteListenner = listener;
    }

    @NonNull
    @Override
    public View_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_sanpham, null);
        return new View_holder(view, listenner, deleteListenner);
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder holder, int position) {
        holder.tvTenSanPham.setText(String.valueOf(list.get(position).getTenSanPham()));
        holder.tvSoLuong.setText(String.valueOf(list.get(position).getGiaXuat()));
        holder.imgIcon.setImageResource(R.drawable.img_sanpham);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class View_holder extends RecyclerView.ViewHolder {
        TextView tvTenSanPham, tvSoLuong;
        ImageView imgClose, imgIcon;

        public View_holder(@NonNull View itemView, onClickListener listener, onClickListener deleteListenner) {
            super(itemView);
            imgClose = (ImageView) itemView.findViewById(R.id.imgClose);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_product);
            tvTenSanPham = (TextView) itemView.findViewById(R.id.tv_value_tenSanPham);
            tvSoLuong = (TextView) itemView.findViewById(R.id.tv_value_soluong);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(getAdapterPosition());
                    }
                }
            });

            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        deleteListenner.onClick(getAdapterPosition());
                    }
                }
            });

        }
    }

    public void refresh(ArrayList arrayList) {
        list.clear();
        list.addAll(arrayList);
        if (arrayList != null) {
            notifyDataSetChanged();
        }
    }
}
