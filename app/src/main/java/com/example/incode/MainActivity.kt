package com.example.incode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.incode.datab.Drivers
import com.example.incode.datab.Places
import com.example.incode.models.Driver
import com.example.incode.models.Place
import com.skydoves.transformationlayout.onTransformationStartContainer

class MainActivity : AppCompatActivity() {
    var places = ArrayList<Place>()
    var drivers = ArrayList<Driver>()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer() //initialising view transformation
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        places = Places().places
        drivers = Drivers().drivers
    }
}