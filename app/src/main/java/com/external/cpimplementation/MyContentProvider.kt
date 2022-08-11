package com.external.cpimplementation

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class MyContentProvider : ContentProvider() {
    companion object{
        const val AUTHORITY = "com.external.cpimplementation.MyContentProvider"
        //const val PROVIDER_NAME = "$AUTHORITY/MyContentProvider"
        val URI = Uri.parse("content://$AUTHORITY/SOMETABLE")
        const val ID = "_id"
        const val NAME = "NAME"
        const val MEANING = "MEANING"
    }

    private lateinit var myHelper:MyHelper
    private lateinit var db:SQLiteDatabase
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val affectedRowsCount = db.delete("SOMETABLE", selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return affectedRowsCount

    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.com.external.cpimplementation.SOMETABLE"
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri?{
        var returnUri:Uri? = null
        db.insert("SOMETABLE", null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return uri

    }

    override fun onCreate(): Boolean {
        myHelper = MyHelper(context) //but we read somewhere that db shouldn't be created in onCreate?
        db = myHelper.readableDatabase
        return if (db == null) false else true

    }

    override fun query(

        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
       return  db.query("SOMETABLE", projection, selection, selectionArgs, null, null, sortOrder )

    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val affectedRowsCount =  db.update("SOMETABLE", values, selection, selectionArgs )
        context?.contentResolver?.notifyChange(uri, null)
        return affectedRowsCount
    }
}