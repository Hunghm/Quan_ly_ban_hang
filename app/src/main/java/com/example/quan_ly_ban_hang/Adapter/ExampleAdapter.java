package com.example.quan_ly_ban_hang.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Model.ExampleItem;
import com.example.quan_ly_ban_hang.Model.SanPham;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>
         {
    private ArrayList<SanPham> mExampleList;
    private List<SanPham> exampleListFull;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ExampleAdapter(ArrayList<SanPham> mExampleList) {
        this.mExampleList = mExampleList;
        exampleListFull = new ArrayList<>();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            mTextView1 = (TextView) itemView.findViewById(R.id.textView);
            mTextView2 = (TextView) itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_nhap,parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        SanPham currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getAnh());
        holder.mTextView1.setText(currentItem.getTenSanPham());
        holder.mTextView2.setText(currentItem.getGhiChu());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

//    @Override
//    public Filter getFilter() {
//        return examplerFilter;
//    }
//
//    private Filter examplerFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<ExampleItem> filteredList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(exampleListFull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (ExampleItem item :exampleListFull) {
//                    if (item.getmText1().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
//            return results;
//        }

//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            mExampleList.clear();
//            mExampleList.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };

}
