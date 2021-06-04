package com.example.sqliteoderintent.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqliteoderintent.R;
import com.example.sqliteoderintent.RecyclerViewAdapter;
import com.example.sqliteoderintent.SQLiteHelper.SQLiteTodoHelper;
import com.example.sqliteoderintent.model.Category;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private RecyclerViewAdapter recyclerViewAdapter;
private List<Category> list;
Calendar myCalendar;
SQLiteTodoHelper sqlite;
private EditText ngay;
private Button btnngay,xem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        ngay=findViewById(R.id.date);
        btnngay=findViewById(R.id.btndate);
        xem=findViewById(R.id.show);
        myCalendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                ngay.setText(sdf.format(myCalendar.getTime()));
                Calendar calendar;

            }
        };

        btnngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DateActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView=findViewById(R.id.recyclerview);
                recyclerViewAdapter=new RecyclerViewAdapter();
                recyclerView.setLayoutManager(new LinearLayoutManager(DateActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);
                sqlite=new SQLiteTodoHelper(DateActivity.this);
                list=sqlite.getByDate(ngay.getText().toString());
                recyclerViewAdapter.setStudents(list);
                recyclerView.setAdapter(recyclerViewAdapter);

            }
        });


    }
}