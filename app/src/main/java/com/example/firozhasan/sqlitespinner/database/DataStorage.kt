package com.example.firozhasan.sqlitespinner.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.SimpleCursorAdapter
import java.io.IOException
import java.util.ArrayList

class DataStorage(context: Context) {
    private val dbHelper: DBHelper
    private var sqliteDB: SQLiteDatabase? = null
    internal var simpleCursorAdapter: SimpleCursorAdapter? = null

    init {
        dbHelper = DBHelper(context)

        try {
            dbHelper.crateDatabase()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //  dbHelper.openDataBase()
    }


    val allDomesticLocations: Array<String>
        get() {
            val locationList = ArrayList<String>()
            dbHelper.openDataBase()
            val selectQuery = "SELECT " + DBHelper.L_NAME + " FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.L_ID + " != 'null' ORDER BY " + DBHelper.L_NAME
            val cursor = dbHelper.getCursor(selectQuery)

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                for (i in 0 until cursor.count) {
                    locationList.add(i, cursor.getString(cursor.getColumnIndex(DBHelper.L_NAME)))

                    cursor.moveToNext()
                }
            }
            dbHelper.close()

            return locationList.toTypedArray()
        }


}