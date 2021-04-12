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
import com.example.quan_ly_ban_hang.Model.User;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterUserRecycler extends RecyclerView.Adapter<AdapterUserRecycler.View_holder> {

    private Context context;
    private List<User> list;
    private onClickListener listenner;
    private onClickListener DeleteListenner;

    public AdapterUserRecycler(Context context, List<User> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_user,null);
        return new View_holder(view, listenner, DeleteListenner);
    }

    @Override
    public void onBindViewHolder(@NonNull View_holder holder, int position) {
        holder.tvHoTen.setText(String.valueOf(list.get(position).getHoTen()));
        holder.tvPassword.setText(String.valueOf(list.get(position).getPassword()));
        holder.tvUser.setText(String.valueOf(list.get(position).getUser()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class View_holder extends RecyclerView.ViewHolder{
        TextView tvHoTen,tvPassword,tvUser;
        ImageView imgClose;
        public View_holder(@NonNull View itemView, onClickListener listener,onClickListener deleteListener)  {
            super(itemView);
            tvHoTen = (TextView) itemView.findViewById(R.id.tv_value_ho_ten);
            tvPassword = (TextView) itemView.findViewById(R.id.tv_value_password);
            tvUser = (TextView) itemView.findViewById(R.id.tv_value_user);
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
