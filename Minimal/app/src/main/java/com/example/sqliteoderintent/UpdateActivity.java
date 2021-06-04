package com.example.sqliteoderintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.sqliteoderintent.SQLiteHelper.SQLiteTodoHelper;
import com.example.sqliteoderintent.model.Category;
import com.example.sqliteoderintent.model.Todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {
    private EditText ID,name,ngay,mota,gio;
    private Button capnhap,xoa,can;
    SQLiteTodoHelper sqlite;
    private Calendar myCalendar;
    Spinner spin;
    SwitchCompat compat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        intView();
        setData();
        if(!compat.isChecked()){
            ngay.setVisibility(View.GONE);
            gio.setVisibility(View.GONE);
        }
        compat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ngay.setVisibility(View.VISIBLE);
                    gio.setVisibility(View.VISIBLE);}
                else{
                    ngay.setVisibility(View.GONE);
                    gio.setVisibility(View.GONE);
                    ngay.setText("");
                    gio.setText("");
                }
            }
        });
        sqlite=new SQLiteTodoHelper(this);
        myCalendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                ngay.setText(sdf.format(myCalendar.getTime()));
            }
        };
        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        TimePickerDialog.OnTimeSetListener time=new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
            }
        };
        gio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(UpdateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        gio.setText( selectedHour + ":" + selectedMinute);
                    }
                }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
            }
        });
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        capnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int i=Integer.parseInt(ID.getText().toString());
                    String n=name.getText().toString();
                    String ng=ngay.getText().toString();
                    String g=gio.getText().toString();
                    String m=mota.getText().toString();
                    String theloai=spin.getSelectedItem().toString();
                    boolean com=compat.isChecked();
                    Category o=new Category(i,n,m,ng,g,com,theloai);

                    sqlite.updateTodo(o);
                        finish();
                }
                catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                }
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {

                   int i=Integer.parseInt(ID.getText().toString());
                   sqlite.deleteTodo(i);
                   finish();
               }catch (NumberFormatException e){
                   System.out.println(e.getMessage());
               }
            }
        });
    }
    public void setData(){
        Intent intent=getIntent();
        Category o= (Category) intent.getSerializableExtra("todo");
        ID.setText(o.getId()+"");
        name.setText(o.getName());
        ngay.setText(o.getDate());
        gio.setText(o.getTime());
        mota.setText(o.getDes());
        compat.setChecked(o.isRemind());

        if(o.getTheloai().equals("Lich hoc")){
            spin.setSelection(0);
        }
        if(o.getTheloai().equals("Lich di lam")){
            spin.setSelection(1);
        }
        if(o.getTheloai().equals("Lich thi")){
            spin.setSelection(2);
        }
    }
    private void intView()
    {

        name=findViewById(R.id.name);
        ngay=findViewById(R.id.date);
        gio=findViewById(R.id.time);
        mota=findViewById(R.id.des);
        capnhap=findViewById(R.id.update);
        xoa=findViewById(R.id.delete);
        can=findViewById(R.id.cancel);
        ID=findViewById(R.id.id);
        compat=findViewById(R.id.switchcompat);
        spin=findViewById(R.id.spinner);
    }
}