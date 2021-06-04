package com.example.sqliteoderintent.Category;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.sqliteoderintent.R;
import com.example.sqliteoderintent.RecyclerViewAdapter;
import com.example.sqliteoderintent.SQLiteHelper.SQLiteTodoHelper;
import com.example.sqliteoderintent.model.Category;

import java.util.List;

public class LichhocFragment extends Fragment {
private RecyclerView recyclerView;
private RecyclerViewAdapter recyclerViewAdapter;
private List<Category> list;
SQLiteTodoHelper sqlite;
View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.activity_lichhoc, container, false);
        recyclerView=v.findViewById(R.id.recyclerview);
        recyclerViewAdapter=new RecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        sqlite=new SQLiteTodoHelper(v.getContext());
        list=sqlite.getByCategory("Lich hoc");
        recyclerViewAdapter.setStudents(list);
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }
}