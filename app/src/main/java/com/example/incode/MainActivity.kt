package com.example.incode

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.incode.api.RetrofitClient
import com.example.incode.datab.Drivers
import com.example.incode.databinding.ActivityMainBinding
import com.example.incode.models.Driver
import com.example.incode.models.PlaceResult
import com.example.incode.models.TestResultsOne
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.skydoves.transformationlayout.onTransformationStartContainer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var locationProvider: FusedLocationProviderClient
    var locationLatitude: Double? = null
    var locationLongitude: Double? = null


    var drivers = ArrayList<Driver>()
    var listGyms = ArrayList<PlaceResult>()
    var listMuseums = ArrayList<PlaceResult>()
    var listRestaurants = ArrayList<PlaceResult>()
    var listHotels = ArrayList<PlaceResult>()
    var listWorship = ArrayList<PlaceResult>()
    var listMovie = ArrayList<PlaceResult>()
    var listSchools = ArrayList<PlaceResult>()
    var listHospitals = ArrayList<PlaceResult>()
    var listGolf = ArrayList<PlaceResult>()
    var listShopping = ArrayList<PlaceResult>()


    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer() //initialising view transformation
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        locationProvider = LocationServices.getFusedLocationProviderClient(this)

        //getting the users current location and latitude details
        getUserCurrentLocation()

        drivers = Drivers().drivers
        val client = RetrofitClient.apiInstance

        val call = client.getPlaces("gyms", "Nairobi")
        val call1 = client.getPlaces("museums", "Nairobi")
        val call2 = client.getPlaces("movie theatres", "Nairobi")
        val call3 = client.getPlaces("schools", "Nairobi")
        val call4 = client.getPlaces("hospitals", "Nairobi")
        val call5 = client.getPlaces("restaurants", "Nairobi")
        val call6 = client.getPlaces("golf clubs", "Nairobi")
        val call7 = client.getPlaces("hotels", "Nairobi")
        val call8 = client.getPlaces("worship", "Nairobi")
        val call9 = client.getPlaces("supermarkets", "Nairobi")

        call9.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listShopping.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listGyms.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call1.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listMuseums.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call2.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listMovie.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call3.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listSchools.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call4.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listHospitals.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call5.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listRestaurants.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call6.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listGolf.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call7.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listHotels.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })
        call8.enqueue(object: Callback<TestResultsOne> {
            override fun onResponse(call: Call<TestResultsOne>, response: Response<TestResultsOne>) {
                if (response.isSuccessful){
                    val list = response.body()!!.results
                    for (element in list){
                        listWorship.add(element)
                    }
                    Toast.makeText(this@MainActivity, list.size.toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<TestResultsOne>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()

            }

        })


    }

    private fun isLocationEnabled(): Boolean {
        val location = getSystemService(LOCATION_SERVICE) as LocationManager
        return location.isProviderEnabled(LocationManager.GPS_PROVIDER) || location.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getUserCurrentLocation() {
        //checking if location permissions are enabled
        if (isLocationEnabled()) {

            //checking other permissions
            if (ActivityCompat.checkSelfPermission(
                    this,
                    ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                locationProvider.lastLocation.addOnCompleteListener {
                    if (it.result == null) {
                        Toast.makeText(
                            this,
                            "requesting for your current location",
                            Toast.LENGTH_LONG
                        ).show()
                        requestUsersCurrentLocation()
                    } else {
                        locationLatitude = it.result.latitude
                        locationLongitude = it.result.longitude
                        Toast.makeText(
                            this,
                            "lat: ->  ${locationLatitude} long: ->  ${locationLongitude}",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                }
            } else {
                //requesting user to turn on course and fine permissions
                getPermissions()
            }

        } else {
            val locationIntent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(locationIntent)
        }
    }

    private fun getPermissions() {
        val permissions = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions, 44)
    }

    private fun requestUsersCurrentLocation() {
        val locationCallBack = object : LocationCallback() {
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
        request.priority = PRIORITY_HIGH_ACCURACY
        request.interval = 5
        request.fastestInterval = 0
        request.numUpdates = 1

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) == PERMISSION_GRANTED
        ) {
            locationProvider.requestLocationUpdates(request, locationCallBack, Looper.myLooper())
        } else {
            getPermissions()
        }

    }
}