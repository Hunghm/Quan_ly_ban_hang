package com.example.quan_ly_ban_hang.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_ban_hang.DAO.ThongKeDAO;
import com.example.quan_ly_ban_hang.Model.TKTuyChon;
import com.example.quan_ly_ban_hang.Model.ThongKe;
import com.example.quan_ly_ban_hang.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ThongKeTuyChonFragment extends Fragment {

    Button btnNgayBD, btnNgayKT,btnXem;
    TextView tvTongChi, tvTongThu, tvLai;
    Date ngayBD,ngayKT;
    SimpleDateFormat sdfHienThi = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdfLuu = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ThongKeDAO thongKeDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thong_ke_tuy_chon_fragment,container,false);

        btnNgayBD = (Button) view.findViewById(R.id.btn_ngay_bd);
        btnNgayKT = (Button) view.findViewById(R.id.btn_ngay_kt);
        btnXem = (Button) view.findViewById(R.id.btn_xem);
        tvLai = view.findViewById(R.id.tv_value_lai);
        tvTongChi = view.findViewById(R.id.tv_value_tong_chi);
        tvTongThu = view.findViewById(R.id.tv_value_tong_thu);

        thongKeDAO = new ThongKeDAO(getContext());

        btnNgayBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                int day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        ngayBD = calendar.getTime();
                        btnNgayBD.setText(sdfHienThi.format(ngayBD));
                    }
                };
// Create DatePickerDialog (Spinner Mode):
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
// Show
                datePickerDialog.show();
            }
        });

        btnNgayKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                int day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        ngayKT = calendar.getTime();
                        btnNgayKT.setText(sdfHienThi.format(ngayKT));
                    }
                };
// Create DatePickerDialog (Spinner Mode):
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dateSetListener, year, month, day);
// Show
                datePickerDialog.show();
            }
        });

        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<TKTuyChon> list = new ArrayList<>();
                list = thongKeDAO.getTKTheoNgay(sdfLuu.format(ngayBD),sdfLuu.format(ngayKT));
                tvTongChi.setText(String.valueOf(list.get(0).getTongChi()));
                tvTongThu.setText(String.valueOf(list.get(0).getTongThu()));
                Double lai = list.get(0).getTongThu() - list.get(0).getTongChi();
                tvLai.setText(String.valueOf(lai));
            }
        });

        return view;
    }
}
