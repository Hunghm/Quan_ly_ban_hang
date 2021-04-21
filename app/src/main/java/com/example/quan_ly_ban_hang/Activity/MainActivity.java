package com.example.quan_ly_ban_hang.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.quan_ly_ban_hang.Adapter.ExampleAdapter;
import com.example.quan_ly_ban_hang.DAO.UserDAO;
import com.example.quan_ly_ban_hang.Fragment.BanChayFragment;
import com.example.quan_ly_ban_hang.Fragment.Loai_SP_Fragment;
import com.example.quan_ly_ban_hang.Fragment.NhapFragment;
import com.example.quan_ly_ban_hang.Fragment.NhapXuatFragment;
import com.example.quan_ly_ban_hang.Fragment.SanPhamFragment;
import com.example.quan_ly_ban_hang.Fragment.ThongKeFragment;
import com.example.quan_ly_ban_hang.Model.ExampleItem;
import com.example.quan_ly_ban_hang.Model.User;
import com.example.quan_ly_ban_hang.R;
import com.example.quan_ly_ban_hang.login.DoiMatKhau;
import com.example.quan_ly_ban_hang.login.ListUserActivity;
import com.example.quan_ly_ban_hang.login.ThemTaiKhoan;
import com.example.quan_ly_ban_hang.login.login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawer;
    String nguoiDung;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDAO = new UserDAO(this);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SanPhamFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.item_1);
        }

        Intent intent =getIntent();
        nguoiDung = intent.getStringExtra("user");
        Toast.makeText(this, nguoiDung, Toast.LENGTH_SHORT).show();
        Log.e("user",nguoiDung);
        View header = navigationView.getHeaderView(0);
        TextView tvUser = header.findViewById(R.id.tv_user);
        User user = userDAO.getID(nguoiDung);
        String tenUser = user.getHoTen();
        tvUser.setText("Xin chào "+ tenUser);
        if (nguoiDung.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.item_1).setVisible(true);
            navigationView.getMenu().findItem(R.id.item_2).setVisible(true);
        }else {
            navigationView.getMenu().findItem(R.id.item_1).setVisible(false);
            navigationView.getMenu().findItem(R.id.item_2).setVisible(false);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.item_1:
                        selectedFragment = new SanPhamFragment();
                        break;
                    case R.id.item_2:
                        selectedFragment = new Loai_SP_Fragment();
                        break;
                    case R.id.item_3:
                        selectedFragment = new NhapXuatFragment();
                        break;
                    case R.id.item_4:
                        selectedFragment = new ThongKeFragment();
                        break;
                    case R.id.item_5:
                        selectedFragment = new BanChayFragment();
                        break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).addToBackStack("TAG").commit();
                }
                return true;
            }
        });
//        final Intent intent = getIntent();
//        if (intent.hasExtra("SWITCH_TAB")) {
//            final Bundle bundle = intent.getBundleExtra("SWITCH_TAB");
//            Integer tab = bundle.getInt("SWITCH_TAB",R.id.item_1);
//
//            if (tab == R.id.item_2) {
//                Log.e("abcde","chay vao intent");
//                Fragment fragment = new NhapXuatFragment();
//                fragment.setArguments(bundle);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//                bottomNavigationView.setSelectedItemId(R.id.item_2);
//                Integer tabViewPager = bundle.getInt("tab",R.id.item_1);
//            }
//        }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new FragmentA()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_1:
                startActivity(new Intent(this, ListUserActivity.class));
                break;
            case R.id.item_2:
                startActivity(new Intent(this, ThemTaiKhoan.class));
                break;
            case R.id.item_3:
                Intent intent = new Intent(this, DoiMatKhau.class);
                intent.putExtra("user",nguoiDung);
                startActivity(intent);
                break;
            case R.id.item_4:
                startActivity(new Intent(this, login.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        return true;
    }


}

