package com.example.quan_ly_ban_hang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.Model.Top;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterTopSpRecycler extends RecyclerView.Adapter<AdapterTopSpRecycler.View_holder> {

    private Context context;
    private List<Top> list;
    private onClickListener listenner;
    private onClickListener deleteListenner;

    public AdapterTopSpRecycler(Context context, List<Top> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_top, null);
        return new View_holder(view, listenner, deleteListenner);
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder holder, int position) {
        holder.tvSoLuongHD.setText(String.valueOf(list.get(position).getSoHoaDon()));
        holder.tvMaSanPham.setText(String.valueOf(list.get(position).getMaSanPham()));
        holder.tvSoLuong.setText(String.valueOf(list.get(position).getSoLuong()));
//        holder.imgIcon.setImageResource(R.drawable.img_sanpham);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class View_holder extends RecyclerView.ViewHolder {
        TextView tvMaSanPham, tvSoLuong,tvSoLuongHD;
        ImageView imgClose, imgIcon;

        public View_holder(@NonNull View itemView, onClickListener listener, onClickListener deleteListenner) {
            super(itemView);
//            imgClose = (ImageView) itemView.findViewById(R.id.imgClose);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_product);
            tvMaSanPham = (TextView) itemView.findViewById(R.id.tv_value_ma_san_pham);
            tvSoLuong = (TextView) itemView.findViewById(R.id.tv_value_soluong);
            tvSoLuongHD = (TextView) itemView.findViewById(R.id.tv_value_soluong_hd);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClick(getAdapterPosition());
                    }
                }
            });

//            imgClose.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        deleteListenner.onClick(getAdapterPosition());
//                    }
//                }
//            });

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
