package com.example.sqliteoderintent.model;

import java.io.Serializable;

public class Todo implements Serializable {
    private int id;
    private String name,des,date,time;
    private boolean remind;

    public Todo() {
    }

    public Todo(int id, String name, String des, String date, String time, boolean remind) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.date = date;
        this.time = time;
        this.remind = remind;
    }

    public Todo(String name, String des, String date, String time, boolean remind) {
        this.name = name;
        this.des = des;
        this.date = date;
        this.time = time;
        this.remind = remind;
    }

    public boolean isRemind() {
        return remind;
    }

    public void setRemind(boolean remind) {
        this.remind = remind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
