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
    lateinit var placesGymModel: PlacesViewModel
    lateinit var placesBreakModel: PlacesViewModel
    lateinit var placesMidMorningModel: PlacesViewModel
    lateinit var placesAfternoonModel: PlacesViewModel
    lateinit var placesEveningModel: PlacesViewModel
    lateinit var golfViewModel: PlacesViewModel
    lateinit var movieViewModel: PlacesViewModel


    var places = ArrayList<Place>()
    var drivers = ArrayList<Driver>()


    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer() //initialising view transformation
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialising dummy search types
        val searchType = "worship"
        val searchType1 = "restaurant"
        val searchType2 = "swimming pool"
        val searchType3 = "park"
        val searchType4 = "museum"
        val searchType5 = "golf club"
        val searchType6 = "Movie Theatre"
        val searchType7 = "gym"

        val place = "Nairobi"

        places = Places().places
        drivers = Drivers().drivers

        //initialising the places repository
        val placesRepo = PlacesRepo()

        // initialising the view model factory
        val worshipFactory = PlacesModelFactory(searchType, place, placesRepo)
        val gymFactory = PlacesModelFactory(searchType7, place, placesRepo)
        val restaurantsFactory = PlacesModelFactory(searchType1, place, placesRepo)
        val swimmingPoolFactory = PlacesModelFactory(searchType2, place, placesRepo)
        val parksFactory = PlacesModelFactory(searchType3, place, placesRepo)
        val museumsFactory = PlacesModelFactory(searchType4, place, placesRepo)
        val golfClubsFactory = PlacesModelFactory(searchType5, place, placesRepo)
        val movieTheatreFactory = PlacesModelFactory(searchType6, place, placesRepo)

        //initialising view models
        placesViewModel = ViewModelProvider(this, worshipFactory)[PlacesViewModel::class.java]
        placesBreakModel = ViewModelProvider(this, restaurantsFactory)[PlacesViewModel::class.java]
        placesGymModel = ViewModelProvider(this, gymFactory)[PlacesViewModel::class.java]
        placesMidMorningModel = ViewModelProvider(this, swimmingPoolFactory)[PlacesViewModel::class.java]
        placesAfternoonModel = ViewModelProvider(this, parksFactory)[PlacesViewModel::class.java]
        placesEveningModel = ViewModelProvider(this, museumsFactory)[PlacesViewModel::class.java]
        golfViewModel = ViewModelProvider(this, golfClubsFactory)[PlacesViewModel::class.java]
        movieViewModel = ViewModelProvider(this, movieTheatreFactory)[PlacesViewModel::class.java]



    }
}