package com.external.cpimplementation

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var meaning:EditText
    var cursor:Cursor? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById<EditText>(R.id.editTextTextPersonName)
        meaning = findViewById<EditText>(R.id.editTextTextPersonName2)
        cursor = contentResolver.query(MyContentProvider.URI, arrayOf(MyContentProvider.ID,
            MyContentProvider.NAME, MyContentProvider.MEANING),null,null,null)
   // UriMatcher(1).apply {  addURI("","",1)
    //}
    }
    fun onClickInsert(view: View){
        val values = ContentValues()
        values.put("NAME", name.text.toString())
        values.put("MEANING", meaning.text.toString())
        contentResolver.insert(MyContentProvider.URI, values)

    }

    fun updateOnClick(view: View){

    }
    fun onClickDelete(view: View){

    }
    fun onClickNext(view: View){
        if(cursor?.moveToNext()!!){
            name.setText(cursor!!.getString(1))
            meaning.setText(cursor!!.getString(2))

        }
    }
    fun onClickPrevious(view: View){
        if(cursor?.moveToPrevious()!!){
            name.setText(cursor!!.getString(1))
            meaning.setText(cursor!!.getString(2))
        }
    }
    fun onClickClear(view: View){
        name.setText("")
        meaning.setText("")
        cursor?.moveToPosition(-1)
        name.requestFocus()
    }


}