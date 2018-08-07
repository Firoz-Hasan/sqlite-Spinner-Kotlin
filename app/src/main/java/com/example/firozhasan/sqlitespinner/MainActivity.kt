package com.example.firozhasan.sqlitespinner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.firozhasan.sqlitespinner.database.DataStorage
import java.util.*

class MainActivity : AppCompatActivity() {


    var dataStorage: DataStorage? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataStorage = DataStorage(applicationContext)

        val loc = dataStorage?.allDomesticLocations
        Log.d("ddd", Arrays.toString(loc))

        var spinner: Spinner = findViewById(R.id.spinner);
        var location = ArrayList<String>()
        for (item in loc!!) {
            location.add(item)
        }

        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, location)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }
}
