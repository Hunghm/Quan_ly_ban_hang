package com.example.quan_ly_ban_hang.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quan_ly_ban_hang.Adapter.AdapterTopSpRecycler;
import com.example.quan_ly_ban_hang.Adapter.AdapterUserRecycler;
import com.example.quan_ly_ban_hang.DAO.HoaDonChiTietDAO;
import com.example.quan_ly_ban_hang.DAO.UserDAO;
import com.example.quan_ly_ban_hang.Model.Top;
import com.example.quan_ly_ban_hang.Model.User;
import com.example.quan_ly_ban_hang.R;

import java.util.ArrayList;

public class ListUserActivity extends AppCompatActivity {

    RecyclerView recySPUser;
    AdapterUserRecycler adapterUserRecycler;
    ArrayList<User> listUser;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        recySPUser = (RecyclerView) findViewById(R.id.recycler_view_user);

        userDAO = new UserDAO(this);
        listUser = (ArrayList<User>) userDAO.getAll();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recySPUser.setLayoutManager(llm);
        adapterUserRecycler = new AdapterUserRecycler(this,listUser);
        recySPUser.setAdapter(adapterUserRecycler);
        Toast.makeText(this, String.valueOf(listUser.size()), Toast.LENGTH_SHORT).show();

        adapterUserRecycler.onClickDeleteListener(new AdapterUserRecycler.onClickListener() {
            @Override
            public void onClick(int possion) {
                User user = listUser.get(possion);
                int result = userDAO.delete(user.getUser());
                if (result>0){
                    Toast.makeText(ListUserActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                    adapterUserRecycler.refresh((ArrayList) userDAO.getAll());
                    reload();
                }

            }
        });

    }
    public void reload(){
        listUser.clear();
        listUser.addAll( userDAO.getAll());
    }
}