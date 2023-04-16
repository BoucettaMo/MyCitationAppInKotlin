package com.example.citations

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbManager(private val context: Context): SQLiteOpenHelper(context,tableName,null,1) {

    companion object {
        private val tableName ="Citations"
        private val colCitation = "citations"
        private val id = "id"
        private val version = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $tableName ($id INTEGER PRIMARY KEY AUTOINCREMENT,$colCitation TEXT)"
        db?.execSQL(query)

    }

    fun addCitation(citation:String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(colCitation,citation)
        db.insert(tableName,null, contentValues)

    }

    fun readDb(): MutableList<String> {
        val list = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery(" SELECT * FROM $tableName",null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun deleteCitation(citation: String) {
        val db = this.writableDatabase

        db.delete(tableName,"citations=?", arrayOf(citation))
        db.close()
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
     db?.execSQL("DROP TABLE IF EXISTS $tableName ")
        onCreate(db)
    }

}
