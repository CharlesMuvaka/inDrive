package com.example.incode

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.LocationManager
import android.media.audiofx.Equalizer.Settings
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.incode.datab.Drivers
import com.example.incode.datab.Places
import com.example.incode.models.Driver
import com.example.incode.models.Place
import com.example.incode.models.PlaceResult
import com.example.incode.models.Resource
import com.example.incode.repository.PlacesRepo
import com.example.incode.viewmodel.PlacesModelFactory
import com.example.incode.viewmodel.PlacesViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.skydoves.transformationlayout.onTransformationStartContainer
class MainActivity : AppCompatActivity() {
private lateinit var locationProvider: FusedLocationProviderClient
    lateinit var placesViewModel: PlacesViewModel
    lateinit var placesGymModel: PlacesViewModel
    lateinit var placesBreakModel: PlacesViewModel
    lateinit var placesMidMorningModel: PlacesViewModel
    lateinit var placesAfternoonModel: PlacesViewModel
    lateinit var placesEveningModel: PlacesViewModel
    lateinit var golfViewModel: PlacesViewModel
    lateinit var movieViewModel: PlacesViewModel
    var listGyms = ArrayList<PlaceResult>()
    var listworship = ArrayList<PlaceResult>()


    var places = ArrayList<Place>()
    var drivers = ArrayList<Driver>()


    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer() //initialising view transformation
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationProvider = LocationServices.getFusedLocationProviderClient(this)

        //getting the users current location
        //getUserCurrentLocation()

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
        val gymFactory = PlacesModelFactory("gym", place, placesRepo)
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

        placesGymModel.places.observe(this, Observer { placeDetails ->
            when (placeDetails) {
                is Resource.Success -> {
                    placeDetails.let {
                        val placeList = it.placesData!!.results
                        for (i in placeList.indices){
                            listGyms.add(placeList[i])
                        }
                        Toast.makeText(this, listGyms.size.toString(), Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Failure -> {
                    placeDetails.let {
                        Toast.makeText(this
                            , it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(this, "Loading please wait", Toast.LENGTH_SHORT)
                        .show()

                }

            }


        })

        placesViewModel.places.observe(this, Observer { placesDetails ->
            when (placesDetails) {
                is Resource.Success -> {
                    placesDetails.let {
                        val worship = it.placesData!!.results
                        for(i in worship.indices){
                            listworship.add(worship[i])
                        }
                    }
                }

                is Resource.Failure -> {
                    placesDetails.message.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                        Log.d("Message Error", "onCreateView:$it ")
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()

                }
            }

        })



    }
    private fun isLocationEnabled(): Boolean{
        val location = getSystemService(LOCATION_SERVICE) as LocationManager
        return location.isProviderEnabled(LocationManager.GPS_PROVIDER) || location.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }
    private fun getUserCurrentLocation() {
        //checking if location permissions are enabled
        if(isLocationEnabled()){

            //checking other permissions
            if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationProvider.lastLocation.addOnCompleteListener{
                    val location = it.result
                    if (location == null){
                        Toast.makeText(this, "requesting for your current location", Toast.LENGTH_LONG).show()
                        requestUsersCurrentLocation()
                    }else{
                        Toast.makeText(this, "lat: ->  ${location.latitude} long: ->  ${location.longitude}", Toast.LENGTH_LONG).show()

                    }
                }
            }else{
                //requesting user to turn on course and fine permissions
                getPermissions()
            }

        }else{
            val locationIntent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(locationIntent)
        }
    }

    private fun getPermissions() {
        val permissions = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions,44 )
    }

    private fun requestUsersCurrentLocation() {
        val locationCallBack = object: LocationCallback(){
            override fun onLocationResult(p0: LocationResult) {
                val lastLocation = p0.lastLocation
                val latitude = lastLocation!!.latitude
                val longitude = lastLocation.longitude
                Toast.makeText(
                    this@MainActivity,
                    "latitude: $latitude, longitude: $longitude",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val request = LocationRequest()
        request.priority =PRIORITY_HIGH_ACCURACY
        request.interval = 5
        request.fastestInterval = 0
        request.numUpdates = 1

        if(ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED){
            locationProvider.requestLocationUpdates(request, locationCallBack, Looper.myLooper())
        }else{
            getPermissions()
        }

    }
}