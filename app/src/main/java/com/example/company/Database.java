package com.example.company;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="Company.db";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q="create table employee (ssn text , name text, age text , department text)";
        db.execSQL(q);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists employee");
        onCreate(db);
    }
    boolean insertData(String ssn,String name,String age,String department){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("ssn",ssn);
        c.put("name",name);
        c.put("age",age);
        c.put("department",department);
        long r=db.insert("employee",null,c);
        if(r==-1) return false;
        else{
            return true;
        }

    }
    public List<String> getData(){
        List<String> datalist=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String[] projection={"ssn","name","age","department"};
        Cursor cursor=db.query("employee",projection,null,null,null,null,null);
        while(cursor.moveToNext()){
            String ssn=cursor.getString(cursor.getColumnIndexOrThrow("ssn"));
            String name=cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String age=cursor.getString(cursor.getColumnIndexOrThrow("age"));
            String department=cursor.getString(cursor.getColumnIndexOrThrow("department"));

            String entry="Name: "+name+"\n"+"Department: "+department;
            datalist.add(entry);
    }
        cursor.close();
        db.close();
        return datalist;

    }


}
