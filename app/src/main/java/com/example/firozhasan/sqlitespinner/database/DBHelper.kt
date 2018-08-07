package com.example.firozhasan.sqlitespinner.database
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException
import android.system.Os.mkdir


/**
 * Created by FIROZ HASAN on 8/1/2017.
 */
/* dhhelper class contains database name, table name, db version
* sqlite db create & upgrade sql query
*
* */

internal class DBHelper(private val myContext: Context) : SQLiteOpenHelper(myContext, DB_NAME, null, DATABASE_VERSION) {
    //************************************************************************************************//


    private var myDataBase: SQLiteDatabase? = null

    private val isDatabaseExist: Boolean
        get() {
            var kontrol: SQLiteDatabase? = null

            try {
                val myPath = DB_PATH + DB_NAME
                kontrol = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)

            } catch (e: SQLiteException) {
                kontrol = null
            }

            if (kontrol != null) {
                kontrol.close()
            }
            return if (kontrol != null) true else false
        }

    @Throws(IOException::class)
    fun crateDatabase() {
        val vtVarMi = isDatabaseExist

        if (!vtVarMi) {
            this.readableDatabase
        //    this.getReadableDatabase();
            try {
                copyDataBase()
            } catch (e: Exception) {
                // throw new Error("Error copying database");
                e.printStackTrace()
            }

        }
    }

    private fun copyDataBase() {



        //------------------------------------------
        // Open your local db as the input stream
        try {

          /*  val myInput = this.myContext.getAssets().open(DB_NAME)
            val dbFile = myContext.getDatabasePath(DB_NAME)
            dbFile.getParentFile().mkdir()
            dbFile.createNewFile()
            val myOutput = FileOutputStream(dbFile.getAbsolutePath())

*/
            val myInput = myContext.assets.open(DB_NAME)

            // Path to the just created empty db
            val outFileName = DB_PATH + DB_NAME

            // Open the empty db as the output stream
           val myOutput = FileOutputStream(outFileName)

          //  val myOutput = FileOutputStream(myContext.getDatabasePath(DB_NAME))

            // transfer bytes from the inputfile to the outputfile
            val buffer = ByteArray(1024)
            var length: Int =myInput.read(buffer)
            while (length > 0) {
                myOutput.write(buffer, 0, length)
                length=myInput.read(buffer)
            }

            // Close the streams
            myOutput.flush()
            myOutput.close()
            myInput.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }



    fun openDataBase() {
        // Open the database
        try {
            val myPath = DB_PATH + DB_NAME
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun dataBase(): SQLiteDatabase? {
        return myDataBase
    }

    fun getCursor(query: String): Cursor {

        return myDataBase!!.rawQuery(query, null)
    }

    @Synchronized
    override fun close() {
        if (myDataBase != null)
            myDataBase!!.close()
        super.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {

        val DB_PATH = "/data/data/com.example.firozhasan.sqlitespinner/databases/"
     //    val DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/"
        val DB_NAME = "recruiterdb.sqlite"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "location_corp"

        val L_ID = "L_ID"
        val L_NAME = "L_Name"
        val L_NAME_BANGLA = "L_NameBangla"

    }
}