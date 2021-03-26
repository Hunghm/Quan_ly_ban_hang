package com.example.quan_ly_ban_hang.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.quan_ly_ban_hang.Fragment.SanPhamFragment;
import com.example.quan_ly_ban_hang.R;

public class XuatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuat);

    }
    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        final Bundle bundle = new Bundle();
        final Intent intent = new Intent(this, MainActivity.class);

        bundle.putInt("SWITCH_TAB", R.id.item_2); // Both constants are defined in your code
        intent.putExtras(bundle);

        return intent;
    }
}