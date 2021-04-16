package com.example.quan_ly_ban_hang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.quan_ly_ban_hang.Adapter.ViewPagerNhapXuatAdapter;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.tabs.TabLayout;

public class NhapXuatFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nhap_xuat_fragment,container,false);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

        ViewPagerNhapXuatAdapter viewPagerAdapter = new ViewPagerNhapXuatAdapter(getActivity().getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        Bundle bundle = getArguments();
//        Integer tabViewPager = 0;
//        if (bundle != null){
//            tabViewPager = bundle.getInt("tab");
//        }
//        Boolean truefalse = bundle == null;
//        Log.e("abcde",String.valueOf(truefalse));
//        Log.e("abcde",String.valueOf(tabViewPager));

        return view;
    }

}
