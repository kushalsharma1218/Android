package com.example.hijeck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.arch.core.executor.DefaultTaskExecutor;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String  name;
    public DatabaseHelper(Context context)
    {
        super(context,"Login.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table userDetails(name text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userDetails");
    }
    //inserting database
    public boolean insert(String name ,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("password",password);
        long ins = db.insert("userDetails",null,contentValues);
        if(ins==-1)return false;
        else return true;
    }
    public Boolean checkName(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from userDetails where name=?",new String[]{name});
        if(cursor.getCount()>0)return  false;
        else return true;

    }
    //checking
    public boolean emailpasscheck(String name,String pass)
    {
        DatabaseHelper.name=name;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from userDetails where name=? and password=?",new String[]{name,pass});
        if(cursor.getCount()>0) return true;
        else return false;
    }

    public static String getName() {
        return name;
    }

}
