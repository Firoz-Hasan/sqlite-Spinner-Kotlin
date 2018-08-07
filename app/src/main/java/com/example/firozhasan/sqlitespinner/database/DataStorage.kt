package com.example.firozhasan.sqlitespinner.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
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

//    private fun dbOpen() {
//        sqliteDB = dbHelper.writableDatabase
//        print("dbopen reached")
//
//    }

    private fun dbClose() {
        dbHelper.close()
    }


    /*fun getAllLocationDetails(): ArrayList<Corporate_Locaiton> {

        val detailsLists = ArrayList<Corporate_Locaiton>()
        dbHelper.openDataBase()

        val selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME


        val cursor = sqliteDB?.rawQuery(selectQuery, null)

        if (cursor != null && cursor!!.getCount() > 0) {

            cursor!!.moveToFirst()

            for (i in 0 until cursor!!.getCount()) {

                val l_ID = cursor!!.getString(cursor!!.getColumnIndex(DBHelper.L_ID))
                val location_Name = cursor!!.getString(cursor!!.getColumnIndex(DBHelper.L_NAME))
                val location_Name_bangla = cursor!!.getString(cursor!!.getColumnIndex(DBHelper.L_NAME_BANGLA))
                //String profile_flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                val details = Corporate_Locaiton(l_ID, location_Name, location_Name_bangla)
                detailsLists.add(details)
                cursor!!.moveToNext()
            }
        }
        dbHelper.close()
        this.dbClose()
        return detailsLists
    }*/

    val allDomesticLocations: Array<String>
        get() {
            val OrgTypes = ArrayList<String>()
            dbHelper.openDataBase()
            //   val selectQuery = "SELECT " + DBHelper.LOCATIONS_COL_LOCATION_NAME + " FROM " + DBHelper.TABLE_NAME_LOCATIONS + " WHERE " + DBHelper.LOCATIONS_COL_LOCATION_TYPE + " != 'Country' ORDER BY " + DBHelper.LOCATIONS_COL_LOCATION_NAME
            //    val selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME
            //val selectQuery = "SELECT " + DBHelper.L_NAME + " FROM " + DBHelper.TABLE_NAME
            val selectQuery = "SELECT " + DBHelper.L_NAME + " FROM " + DBHelper.TABLE_NAME + " WHERE " + DBHelper.L_ID + " != 'null' ORDER BY " + DBHelper.L_NAME

//            Log.d("selectQuery", selectQuery)
            val cursor = dbHelper.getCursor(selectQuery)

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                for (i in 0 until cursor.count) {
                    OrgTypes.add(i, cursor.getString(cursor.getColumnIndex(DBHelper.L_NAME)))

                    cursor.moveToNext()
                }
            }
            dbHelper.close()

            return OrgTypes.toTypedArray()
        }


}