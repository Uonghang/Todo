package com.example.sqliteoderintent.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqliteoderintent.model.Category;
import com.example.sqliteoderintent.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class SQLiteTodoHelper extends SQLiteOpenHelper {
    private static final String DATANAME="lenlich.db";
    private  static final int DATAVESION=1;

    public SQLiteTodoHelper(@Nullable Context context) {
        super(context,DATANAME,null,DATAVESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlll="CREATE TABLE todo(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,des TEXT,ngay TEXT," +
                "time TEXT,remind Boolean,theloai TEXT)";
        db.execSQL(sqlll);
    }
    public long addTodo(Category o){
        ContentValues v=new ContentValues();
        v.put("name",o.getName());
        v.put("des",o.getDes());
        v.put("ngay",o.getDate());
        v.put("time",o.getTime());
        v.put("theloai",o.getTheloai());
        v.put("remind",o.isRemind());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("todo",null,v);
    }
    public List<Category> getAllTodo(){
        List<Category> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        Cursor cr=st.query("todo",null,null
                ,null,null,null,null);
        while(cr!=null && cr.moveToNext()){
            int id=cr.getInt(0);
            String name=cr.getString(1);
            String des=cr.getString(2);
            String ng=cr.getString(3);
            String t=cr.getString(4);
            boolean c=cr.getInt(5)==1;
            String theloai=cr.getString(6);
            list.add(new Category(id,name,des,ng,t,c,theloai));
        }
        return list;
    }
    public Todo getTodoById(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor cr=st.query("todo",null,whereClause,whereArgs,null
        ,null,null);
        while(cr.moveToNext()){
            String n=cr.getString(1);
            String d=cr.getString(2);
            String ng=cr.getString(3);
            String t=cr.getString(4);
            boolean c=cr.getString(5).equals(true);
            return new Todo(id,n,d,ng,t,c);
        }
        return null;
    }
    public int deleteTodo(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getReadableDatabase();
        return st.delete("todo",whereClause,whereArgs);

    }
    public int updateTodo(Category o){
        ContentValues v=new ContentValues();
        v.put("name",o.getName());
        v.put("des",o.getDes());
        v.put("ngay",o.getDate());
        v.put("time",o.getTime());
        v.put("remind",o.isRemind());
        v.put("theloai",o.getTheloai());
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(o.getId())};
        SQLiteDatabase st=getWritableDatabase();
       return  st.update("todo",v,whereClause,whereArgs);

    }
    public List<Category> getByName(String name) {
        String sql = "name like ?";
        String[] args = { "%" + name + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("todo", null, sql, args, null, null, null);
        List<Category> list = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String n = cursor.getString(1);
            String d=cursor.getString(2);
            String ng=cursor.getString(3);
            String t=cursor.getString(4);
            boolean c=cursor.getString(5).equals(true);
            String theloai=cursor.getString(6);
            Category cate = new Category(id, n,d, ng, t,c,theloai);
            list.add(cate);
        }
        return list;
    }
    public List<Category> getByCategory(String tl) {
        String sql = "theloai=?";
        String[] args = {tl};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("todo", null, sql, args, null, null, null);
        List<Category> list = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String n = cursor.getString(1);
            String d=cursor.getString(2);
            String ng=cursor.getString(3);
            String t=cursor.getString(4);
            boolean c=cursor.getString(5).equals(true);
            String theloai=cursor.getString(6);
            Category cate = new Category(id, n,d, ng, t,c,theloai);
            list.add(cate);
        }
        return list;
    }
    public List<Category> getByDate(String ngay) {
        String sql = "ngay=?";
        String[] args = {ngay};
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("todo", null, sql, args, null, null, null);
        List<Category> list = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String n = cursor.getString(1);
            String d=cursor.getString(2);
            String ng=cursor.getString(3);
            String t=cursor.getString(4);
            boolean c=cursor.getString(5).equals(true);
            String theloai=cursor.getString(6);
            Category cate = new Category(id, n,d, ng, t,c,theloai);
            list.add(cate);
        }
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
