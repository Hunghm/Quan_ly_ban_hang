package com.example.quan_ly_ban_hang.Adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.quan_ly_ban_hang.Fragment.NhapFragment;
import com.example.quan_ly_ban_hang.Fragment.XuatFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String listTab[]={"Xuất","nhập"};

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.e("abcdef",String.valueOf(position));
        switch (position){
            case 0:
                return new NhapFragment();
            case 1:
                return new XuatFragment();
            default:
                return new NhapFragment();
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
