package com.example.sqliteoderintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


public class AddActivity extends AppCompatActivity {
    private EditText ten,ngay,d,Time;
    private Button add,can;
    SQLiteTodoHelper sqlite;
    Calendar myCalendar;
    SwitchCompat compat;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        intView();
        ngay.setVisibility(View.GONE);
        Time.setVisibility(View.GONE);
        sqlite=new SQLiteTodoHelper(this);
        compat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ngay.setVisibility(View.VISIBLE);
                    Time.setVisibility(View.VISIBLE);}
                else{
                    ngay.setVisibility(View.GONE);
                    Time.setVisibility(View.GONE);
                }
            }
        });
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

        ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, date, myCalendar
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
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String n=ten.getText().toString();
                    String ng=ngay.getText().toString();
                    String des=d.getText().toString();
                    String t=Time.getText().toString();
                    boolean remind=false;
                    if(compat.isChecked()){
                         remind=true;
                    }
                    String theloai=spin.getSelectedItem().toString();
                    Category o=new Category(n,des,ng,t,remind,theloai);
                    sqlite.addTodo(o);
                    String [] time_spilt=ng.split("/");
                    int date=Integer.parseInt(time_spilt[1]);
                    int month=Integer.parseInt(time_spilt[0])-1;
                    int year=Integer.parseInt(time_spilt[2]);
                    String [] date_spilt=t.split(":");
                    int hour=Integer.parseInt(date_spilt[0]);
                    int minute=Integer.parseInt(date_spilt[1]);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(year,month,date,hour,minute);
                    AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(AddActivity.this,
                            MyReceiver.class);
                    intent.putExtra("myAction", "mDoNotify");
                    intent.putExtra("Title",ten.getText().toString());
                    intent.putExtra("Description", d.getText().toString());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this,
                            0, intent, 0);
                    am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    finish();
                }catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void intView()
    {
        ten=findViewById(R.id.name);
        ngay=findViewById(R.id.date);
        d=findViewById(R.id.des);
        Time=findViewById(R.id.time);
        add=findViewById(R.id.Add);
        can=findViewById(R.id.cancel);
        compat=findViewById(R.id.switchcompat);
        spin=findViewById(R.id.spinner);
    }
}