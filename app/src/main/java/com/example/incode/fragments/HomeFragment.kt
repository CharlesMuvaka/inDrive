package com.example.incode.fragments

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.incode.R
import com.example.incode.databinding.FragmentHomeBinding
import com.example.incode.uitils.Constants
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Suppress("DEPRECATION")
class HomeFragment : Fragment(), OnMapReadyCallback, View.OnClickListener {
    private lateinit var locationProvider: FusedLocationProviderClient
    private lateinit var bind: FragmentHomeBinding
    private lateinit var googleMap: GoogleMap
    private val locationPermissions: Int = 44
    private lateinit var mapFragment: SupportMapFragment

    //users location
    private var userLat: Double? = null
    private var userLong: Double? = null

    //dummy origin location
    private val originLat = 28.5021359
    private val originLong = 77.4054901

    //dummy destination location
    private val destLat = 28.5151087
    private val destLong = 77.3932163

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(inflater)

        locationProvider = LocationServices.getFusedLocationProviderClient(requireContext())

        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        //initialising the places api
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), Constants.yes)
        }

        //getting the users location
        getUsersLocation()
        
        //initialising the autocomplete support fragment instance
        val autoCompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        //specifying the type of place data to return
        autoCompleteFragment.setPlaceFields(
            listOf(
                com.google.android.libraries.places.api.model.Place.Field.ID,
                com.google.android.libraries.places.api.model.Place.Field.NAME
            )
        )

        //setting up a place selection listener to handle the response
        autoCompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Log.d("Home Fragment", "onError: ${p0.statusMessage}")
            }

            override fun onPlaceSelected(p0: com.google.android.libraries.places.api.model.Place) {
                Log.d("Home Fragment", "onPlaceSelected: ${p0.name}")
            }

        })
        //setting onclick listeners
        bind.search.editText!!.setOnClickListener(this::onClick)
        return bind.root
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissions) {
            if (grantResults.isNotEmpty() || grantResults[0] == PERMISSION_GRANTED) {
                getUsersLocation()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getUsersLocation() {
        //check if location enabled
        if (isLocationEnabled()) {
            //checking if permissions are enabled
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    ACCESS_FINE_LOCATION
                ) == PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    ACCESS_COARSE_LOCATION
                ) == PERMISSION_GRANTED
            ) {
                locationProvider.lastLocation.addOnCompleteListener {
                    val location = it.result
                    if (location == null) {
                        requestNewLocation()
                    } else {
                        userLat = location.latitude
                        userLong = location.longitude
                        mapFragment.getMapAsync(this::onMapReady)

                        Toast.makeText(
                            requireContext(),
                            "latitude: $userLat, longitude: $userLong",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            } else {
                //requesting for the permissions
                getPermissions()
            }

        } else {
            //requesting the user to turn on his location settings
            Toast.makeText(requireContext(), "Turn on your location", Toast.LENGTH_SHORT).show()
            val locationIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            requireActivity().startActivity(locationIntent)
        }

    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            val lastLocation = p0.lastLocation
            val latitude = lastLocation!!.latitude
            val longitude = lastLocation.longitude
            Toast.makeText(
                requireContext(),
                "latitude: $latitude, longitude: $longitude",
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    //requesting for permissions
    private fun getPermissions() {
        val permissions = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION)
        Toast.makeText(requireContext(), "Please turn on ", Toast.LENGTH_SHORT).show()
        ActivityCompat.requestPermissions(requireActivity(), permissions, locationPermissions)
    }

    //requesting for the users new location
    private fun requestNewLocation() {
        val request = LocationRequest()
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        request.interval = 5
        request.fastestInterval = 0
        request.numUpdates = 1

        locationProvider = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) == PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_COARSE_LOCATION
            ) == PERMISSION_GRANTED
        ) {
            locationProvider.requestLocationUpdates(request, locationCallback, Looper.myLooper())

        } else {
            getPermissions()
        }
    }

    //checking if location is enabled
    private fun isLocationEnabled(): Boolean {
        val location =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return location.isProviderEnabled(LocationManager.GPS_PROVIDER) || location.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        val sydney = LatLng(userLat!!, userLong!!)

        //creating a geocoder instance to get the location details
        val coder = Geocoder(requireContext())

        //creating a list to store the location info
        var locationsList = mutableListOf<Address>()
        val searchString = "Nairobi"

        try {
            locationsList =
                coder.getFromLocationName("Nairobi Golf club", 1) as MutableList<Address>
        } catch (e: IOException) {
            Log.d("HomeFragment", "onMapReady: ${e.message}")

        }
        if (locationsList!!.isNotEmpty()) {
            val address: Address = locationsList[0]
            Log.d("HomeFragment", "onMapReady: ${address.toString()}")
        } else {
            Log.d("HomeFragment", "onMapReady: null pointer")

        }


        googleMap.addMarker(MarkerOptions().position(sydney).title("Sidney city"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onResume() {
        super.onResume()
        //checking if permissions are enabled
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) == PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_COARSE_LOCATION
            ) == PERMISSION_GRANTED
        ) {
            getUsersLocation()
        } else {
            getPermissions()
        }

    }

    //getting the directions url
    private fun getDirectionsUrl(): String {
        return "https://maps.googleapis.com/maps/api/directions/json?origin=$originLat,$originLong&destination=$destLat,$destLong&sensor=false&mode=driving&key=${Constants.yes}"
    }

    //creating the polyline
    private fun decodePolyline(encode: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encode.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0


            do {
                b = encode[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)

            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat
            shift = 0
            result = 0

            do {
                b = encode[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)

            val dLng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dLng
            val latLng = LatLng((lat.toDouble() / 1E5), (lng.toDouble() / 1E5))
            poly.add(latLng)
        }

        return poly
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.search.editText) {


        }
    }


    //creating an inner class to pass the url string generated
//    private inner class GetDirection(var url: String) :
//        AsyncTask<Void, Void, List<List<LatLng>>>() {
//        override fun doInBackground(vararg p0: Void?): List<List<LatLng>> {
//            val client = OkHttpClient()
//            val request = Request.Builder().url(url).build()
//            val response = client.newCall(request).request()
//            val data = response.body().toString()
//
//            val result = ArrayList<List<LatLng>>()
//
//            try {
//                val responseObject = Gson().fromJson(data, MapData::class.java)
//                val path = ArrayList<LatLng>()
//
//                for (i in 0 until responseObject.routes[0].legs[0].steps.size) {
//                    path.addAll(decodePolyline(responseObject.routes[0].legs[0].steps[i].polyLine.points))
//                }
//
//                result.add(path)
//            } catch (e: Exception) {
//                println(e.message)
//            }
//            return result
//        }
//
//        override fun onPostExecute(result: List<List<LatLng>>?) {
//            val lineOption = PolylineOptions()
//            for (i in result!!.indices) {
//                lineOption.addAll(result[i]).width(10f).color(Color.GREEN).geodesic(true)
//            }
//            googleMap.addPolyline(lineOption)
//        }
//    }

}