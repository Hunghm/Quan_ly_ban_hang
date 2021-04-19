package com.example.quan_ly_ban_hang.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quan_ly_ban_hang.Fragment.NhapFragment;
import com.example.quan_ly_ban_hang.Fragment.ThongKeTheoThangFragment;
import com.example.quan_ly_ban_hang.Fragment.ThongKeTuyChonFragment;
import com.example.quan_ly_ban_hang.Fragment.XuatFragment;

public class ViewPagerThongKeAdapter extends FragmentStatePagerAdapter {

    private String listTab[]={"TK Tùy chọn","TK Biểu đồ "};

    public ViewPagerThongKeAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        Log.e("abcdef",String.valueOf(position));
        switch (position){
            case 0:
                return new ThongKeTuyChonFragment();
            case 1:
                return new ThongKeTheoThangFragment();
            default:
                return new ThongKeTheoThangFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
