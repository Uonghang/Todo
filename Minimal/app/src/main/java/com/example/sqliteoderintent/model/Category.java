package com.example.sqliteoderintent.model;

public class Category extends Todo{
    private String theloai;

    public Category(int id, String name, String des, String date, String time, boolean remind, String theloai) {
        super(id, name, des, date, time, remind);
        this.theloai = theloai;
    }

    public Category(String name, String des, String date, String time, boolean remind, String theloai) {
        super(name, des, date, time, remind);
        this.theloai = theloai;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}
