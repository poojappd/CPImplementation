package com.external.cpimplementation

import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class MyHelper(context: Context?) : SQLiteOpenHelper(context, "MyDB", null, 1) {


        override fun onCreate(db: SQLiteDatabase?) {
            Log.e(TAG, "oncreate of DB")
            db?.execSQL("CREATE TABLE SOMETABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,  MEANING TEXT)")
            db?.execSQL("INSERT INTO SOMETABLE(NAME, MEANING) VALUES('POOJA','WORSHIP' )")
            db?.execSQL("INSERT INTO SOMETABLE(NAME, MEANING) VALUES('SUMA','FLOWER ILA FIRE' )")

        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS " + "MyDB");

            // Create tables again
            onCreate(db);

        }




}