package com.example.incode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.incode.api.RetrofitClient
import com.example.incode.datab.Drivers
import com.example.incode.datab.Places
import com.example.incode.models.Driver
import com.example.incode.models.Place
import com.example.incode.models.TestResultsOne
import com.skydoves.transformationlayout.onTransformationStartContainer
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    var places = ArrayList<Place>()
    var drivers = ArrayList<Driver>()
    private lateinit var call: Call<TestResultsOne>

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer() //initialising view transformation
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        places = Places().places
        drivers = Drivers().drivers

        val retrofit = RetrofitClient.apiInstance

        call = retrofit.getPlace("gym", "Nairobi")

    }
}