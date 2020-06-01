package com.example.hijeck;

import android.content.ContentValues;
import android.content.Context;
import android.content.QuickViewConstants;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.common.util.Strings;

public class DataBaseClassAdding extends SQLiteOpenHelper {

    private String tabelname;
   public  DataBaseClassAdding(Context context) {
       super(context, "UserSecrets.db", null, 1);
       tabelname=DatabaseHelper.getName();
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table "+tabelname+"user (ApplicationName text, UserName text,Password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+DatabaseHelper.getName());
        onCreate(db);
    }

    boolean insertData(String applicationname, String UserName, String pass)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("ApplicationName",applicationname);
        cv.put("UserName",UserName);
        cv.put("Password",pass);
        long ins=db.insert(DatabaseHelper.getName()+"User",null,cv);
        return ins != -1;
    }
    boolean checkUserNameWithFireld(String application_name, String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+DatabaseHelper.getName()+"User where ApplicationName = ? and UserName =?",new String[]{application_name,username});
        if(cursor.getCount()>0)return  false;
        else return  true;
    }
    void cleardata()
    {
        SQLiteDatabase db = this.getReadableDatabase();
       db.execSQL("DROP TABLE IF EXISTS "+DatabaseHelper.getName()+"User");
        onCreate(db);
    }
    Cursor getAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("select * from "+DatabaseHelper.getName()+"User",null);
    }
    boolean update(String appname,String username,String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Password",pass);
        long ins=db.update(DatabaseHelper.getName()+"User",cv,"ApplicationName = ? and UserName = ?",new String[]{appname,username});
        return ins!=-1;
    }
}
