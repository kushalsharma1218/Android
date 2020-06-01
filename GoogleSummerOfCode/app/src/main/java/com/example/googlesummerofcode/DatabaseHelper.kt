package com.example.googlesummerofcode

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, "Login.db", null, 1) {

      var name: String? = null

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("Create table userDetails(name text primary key,password text)")
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("drop table if exists userDetails")
    }

    //inserting database
    fun insert(name: String?, password: String?): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("password", password)
        val ins = db.insert("userDetails", null, contentValues)
        return ins != -1L
    }

    fun checkName(name: String): Boolean {
        val db = this.readableDatabase
        val cursor =
            db.rawQuery("select * from userDetails where name=?", arrayOf(name))
        return if (cursor.count > 0) false else true
    }

    //checking
    fun emailpasscheck(name: String?, pass: String?): Boolean {
         this.name = name
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select * from userDetails where name=? and password=?",
            arrayOf(name, pass)
        )
        return cursor.count > 0
    }

}