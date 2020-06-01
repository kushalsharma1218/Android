package com.example.googlesummerofcode

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseClassAdding(context: Context?) :
    SQLiteOpenHelper(context, "UserSecrets.db", null, 1) {

    private val tabelname: String?
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table " + tabelname + "user (ApplicationName text, UserName text,Password text)")
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("drop table if exists $tabelname")
        onCreate(db)
    }

    fun insertData(
        applicationname: String?,
        UserName: String?,
        pass: String?
    ): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("ApplicationName", applicationname)
        cv.put("UserName", UserName)
        cv.put("Password", pass)
        val ins = db.insert(tabelname + "User", null, cv)
        return ins != -1L
    }

    fun checkUserNameWithFireld(
        application_name: String,
        username: String
    ): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "select * from " + tabelname + "User where ApplicationName = ? and UserName =?",
            arrayOf(application_name, username)
        )
        return if (cursor.count > 0) false else true
    }

    fun cleardata() {
        val db = this.readableDatabase
        db.execSQL("DROP TABLE IF EXISTS " + tabelname + "User")
        onCreate(db)
    }

    val allData: Cursor
        get() {
            val db = this.readableDatabase
            return db.rawQuery("select * from " + tabelname + "User", null)
        }

    fun update(
        appname: String,
        username: String,
        pass: String?
    ): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Password", pass)
        val ins = db.update(
            tabelname + "User",
            cv,
            "ApplicationName = ? and UserName = ?",
            arrayOf(appname, username)
        ).toLong()
        return ins != -1L
    }

    init {
        var databaseHelper:DatabaseHelper = DatabaseHelper(context)
        tabelname =databaseHelper.name
    }
}