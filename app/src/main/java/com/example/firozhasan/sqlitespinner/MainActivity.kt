package com.example.firozhasan.sqlitespinner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.appcompat.R.styleable.Spinner
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.firozhasan.sqlitespinner.database.DataStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    var dataStorage: DataStorage? = null
   // internal var detailsLists: ArrayList<Corporate_Locaiton>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataStorage = DataStorage(applicationContext)
      //  detailsLists = dataStorage?.getAllLocationDetails()
        val loc = dataStorage?.allDomesticLocations
        var loc2 = arrayOf(loc)
        Log.d("ddd", Arrays.toString(loc))
      //  Log.d("data", detailsLists.toString())


      // Spinner element
      var spinner: Spinner= findViewById(R.id.spinner);

      // Spinner click listener
      //spinner.setOnItemSelectedListener(this);

      var categories =  ArrayList<String>()
        for (item in loc!!){
            categories.add(item)
        }

      // Creating adapter for spinner
     // ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        // Drop down layout style - list view with radio button
      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      // attaching data adapter to spinner
      spinner.setAdapter(dataAdapter);

    }
}
