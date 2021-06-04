package com.example.sqliteoderintent.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.sqliteoderintent.AddActivity;
import com.example.sqliteoderintent.Category.MainCategoryActivity;
import com.example.sqliteoderintent.Category.DateActivity;
import com.example.sqliteoderintent.R;
import com.example.sqliteoderintent.RecyclerViewAdapter;
import com.example.sqliteoderintent.SQLiteHelper.SQLiteTodoHelper;
import com.example.sqliteoderintent.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LenlichFragemt extends Fragment implements SearchView.OnQueryTextListener {
    private FloatingActionButton add;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<Category> list;
    View v;
    SQLiteTodoHelper sqlite;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
         v=inflater.inflate(R.layout.activity_lenlich_fragemt, container, false);

        init();

        recyclerViewAdapter=new RecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        sqlite=new SQLiteTodoHelper(v.getContext());
        list=sqlite.getAllTodo();
        recyclerViewAdapter.setStudents(list);
        recyclerView.setAdapter(recyclerViewAdapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
    private void init() {
        add=v.findViewById(R.id.btnadd);
        recyclerView=v.findViewById(R.id.recyclerview);
    }
    @Override
    public void onStart() {
        super.onStart();
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        sqlite = new SQLiteTodoHelper(v.getContext());
        List<Category> list = sqlite.getAllTodo();
        recyclerViewAdapter.setStudents(list);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Lich:
                Intent intent=new Intent(v.getContext(), MainCategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.Lichhomnay:
                Intent intent1=new Intent(v.getContext(), DateActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.mSearch);
        SearchView searchView=(SearchView)menuItem.getActionView();

        searchView.setOnQueryTextListener(this);

    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Category> list=new ArrayList<>();
        list=sqlite.getByName(newText);
        recyclerViewAdapter.setStudents(list);
        recyclerView.setAdapter(recyclerViewAdapter);
        return false;
    }
}