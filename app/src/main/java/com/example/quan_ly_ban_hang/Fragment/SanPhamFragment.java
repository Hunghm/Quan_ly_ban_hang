package com.example.quan_ly_ban_hang.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quan_ly_ban_hang.Adapter.ExampleAdapter;
import com.example.quan_ly_ban_hang.Model.ExampleItem;
import com.example.quan_ly_ban_hang.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SanPhamFragment extends Fragment {

    RecyclerView recySanPham;
    private ExampleAdapter mAdapter;
    private ArrayList<ExampleItem> mExampleList;
    FloatingActionButton btnAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.san_pham_fragment,container,false);
        recySanPham = (RecyclerView) view.findViewById(R.id.recycler_view_san_pham);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "abcd", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.book, "one", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.book, "two", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.book, "three", "Line 6"));
        mExampleList.add(new ExampleItem(R.drawable.book, "four", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.book, "five", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.book, "six", "Line 6"));
        mExampleList.add(new ExampleItem(R.drawable.book, "serven", "Line 2"));
        mExampleList.add(new ExampleItem(R.drawable.book, "eight", "Line 4"));
        mExampleList.add(new ExampleItem(R.drawable.book, "night", "Line 6"));
        mExampleList.add(new ExampleItem(R.drawable.book, "ten", "Line 6"));
        mAdapter = new ExampleAdapter(mExampleList);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuInflater menuInflater = inflater;
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }


}
