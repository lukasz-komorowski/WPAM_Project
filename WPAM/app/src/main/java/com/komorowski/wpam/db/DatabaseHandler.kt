package com.komorowski.wpam.db

import com.komorowski.wpam.model.Entry
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSIOM) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME " +
                "($ID Integer PRIMARY KEY, $DATE TEXT, $TYPE TEXT)"
                //"($ID Integer PRIMARY KEY, $TEST_TEXT TEXT, $DATE TEXT, $TYPE TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Called when the database needs to be upgraded
    }

    //Inserting (Creating) data
    fun addEntry(entry: Entry): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase
        val values = ContentValues()
        //values.put(TEST_TEXT, entry.testText)
        values.put(DATE, entry.date)
        values.put(TYPE, entry.type)
        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    //delete row
    fun deleteRow(row_id: String): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, "$ID = ?", arrayOf(row_id))
        db.close()
        Log.v("DeletedID", "$_success")
        return (Integer.parseInt("$_success") != -1)
    }

    //get all users
    fun getAllEntries(): String {
        var allEntries: String = "";
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    var id = cursor.getString(cursor.getColumnIndex(ID))
                    //var testText = cursor.getString(cursor.getColumnIndex(TEST_TEXT))
                    var date = cursor.getString(cursor.getColumnIndex(DATE))
                    var type = cursor.getString(cursor.getColumnIndex(TYPE))

                    //allEntries = "$allEntries\n$id $testText $date $type"
                    allEntries = "$allEntries\n$id $date $type"
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return allEntries
    }

    companion object {
        private val DB_NAME = "EntriesDB"
        private val DB_VERSIOM = 1;
        private val TABLE_NAME = "entries"
        private val ID = "id"
        //private val TEST_TEXT = "testText"
        private val DATE = "date"
        private val TYPE = "type"
    }
}