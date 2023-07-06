package com.example.incode

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.incode.datab.Places
import com.example.incode.databinding.ActivitySecondBinding
import com.example.incode.fragments.HomeFragment
import com.example.incode.fragments.PlaceFragment
import com.example.incode.models.Place
import com.skydoves.transformationlayout.TransformationAppCompatActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var bind: ActivitySecondBinding
    private var places = ArrayList<Place>()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bind.root)



    }


}