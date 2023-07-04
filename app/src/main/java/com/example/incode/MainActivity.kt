package com.example.incode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.incode.datab.Drivers
import com.example.incode.datab.Places
import com.example.incode.models.Driver
import com.example.incode.models.Place
import com.example.incode.repository.PlacesRepo
import com.example.incode.viewmodel.PlacesModelFactory
import com.example.incode.viewmodel.PlacesViewModel
import com.skydoves.transformationlayout.onTransformationStartContainer
class MainActivity : AppCompatActivity() {
    lateinit var placesViewModel: PlacesViewModel
    var places = ArrayList<Place>()
    var drivers = ArrayList<Driver>()


    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer() //initialising view transformation
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialising dummy search types
        val searchType = "golf club"
        val place = "Nairobi"

        places = Places().places
        drivers = Drivers().drivers

        //initialising the places repository
        val placesRepo = PlacesRepo()
        // initialising the view model factory
        val placesFactory = PlacesModelFactory(searchType, place, placesRepo)

        //initialising the view model
        placesViewModel = ViewModelProvider(this, placesFactory)[PlacesViewModel::class.java]




    }
}