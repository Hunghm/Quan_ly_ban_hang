package com.example.quan_ly_ban_hang.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.quan_ly_ban_hang.Adapter.ExampleAdapter;
import com.example.quan_ly_ban_hang.Fragment.BanChayFragment;
import com.example.quan_ly_ban_hang.Fragment.NhapFragment;
import com.example.quan_ly_ban_hang.Fragment.NhapXuatFragment;
import com.example.quan_ly_ban_hang.Fragment.SanPhamFragment;
import com.example.quan_ly_ban_hang.Fragment.ThongKeFragment;
import com.example.quan_ly_ban_hang.Model.ExampleItem;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_naviation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SanPhamFragment()).commit();
//            navigationView.setCheckedItem(R.id.item_1);
//        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.item_1:
                        selectedFragment = new SanPhamFragment();
                        break;
                    case R.id.item_2:
//                        Log.e("abcdef","chay vao day");
                        selectedFragment = new NhapXuatFragment();
                        break;
                    case R.id.item_3:
                        selectedFragment = new ThongKeFragment();
                        break;
                    case R.id.item_4:
                        selectedFragment = new BanChayFragment();
                        break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                }
                return true;
            }
        });

        //        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
        //                new FragmentA()).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_1:
                //                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentE()).commit();
                break;
            case R.id.item_2:
                //                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentF()).commit();
                break;
            case R.id.item_3:
                //                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentG()).commit();
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

