package com.example.quan_ly_ban_hang.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_ban_hang.DAO.ThongKeDAO;
import com.example.quan_ly_ban_hang.Model.ThongKe;
import com.example.quan_ly_ban_hang.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ThongKeTheoThangFragment extends Fragment {

    BarChart barChart;
    ThongKeDAO thongKeDAO;
    ArrayList<ThongKe> listThongKe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thong_ke_theo_thang_fragment,container,false);
        barChart = (BarChart) view.findViewById(R.id.bar_chart);

        thongKeDAO = new ThongKeDAO(getContext());
        listThongKe = new ArrayList<>();
        listThongKe = (ArrayList<ThongKe>) thongKeDAO.getThongKe();
        if (listThongKe.size()!=0) {
            float tongTienDouble = listThongKe.get(0).getTongTien().floatValue();
        }

        ArrayList<BarEntry> visitors = new ArrayList<>();
        for (ThongKe thongke : listThongKe) {
            visitors.add(new BarEntry( 4, thongke.getTongTien().floatValue()));
        }
        Toast.makeText(getContext(), String.valueOf(listThongKe.size()), Toast.LENGTH_SHORT).show();

//        visitors.add(new BarEntry(2, 420));
//        visitors.add(new BarEntry(2015, 745));
//        visitors.add(new BarEntry(2016, 508));
//        visitors.add(new BarEntry(2017, 660));
//        visitors.add(new BarEntry(2018, 550));
//        visitors.add(new BarEntry(2019, 630));
//        visitors.add(new BarEntry(2020, 470));

        BarDataSet barDataSet = new BarDataSet(visitors,"Visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);

        return view;
    }
}
