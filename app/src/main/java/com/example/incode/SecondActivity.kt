package com.example.incode

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.incode.adapters.ViewPagerAdapter
import com.example.incode.datab.Places
import com.example.incode.databinding.ActivitySecondBinding
import com.example.incode.fragments.PlaceFragment
import com.example.incode.models.PlaceResult
import com.example.incode.models.Resource
import com.example.incode.repository.PlacesRepo
import com.example.incode.viewmodel.PlacesModelFactory
import com.example.incode.viewmodel.PlacesViewModel

class SecondActivity : AppCompatActivity() {
    lateinit var golfViewModel: PlacesViewModel
    private lateinit var bind: ActivitySecondBinding
    private var places = ArrayList<Fragment>()
    private var listGyms = ArrayList<PlaceResult>()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val place = Places().places[0]

        val placesRepo = PlacesRepo()
        val gymFactory = PlacesModelFactory("gym", "Nairobi", placesRepo)
        golfViewModel = ViewModelProvider(this, gymFactory)[PlacesViewModel::class.java]

        golfViewModel.places.observe(this, Observer { placeDetails ->
            when (placeDetails) {
                is Resource.Success -> {
                    placeDetails.let {
                        val placeList = it.placesData!!.results
                        for (i in placeList.indices) {
                            listGyms.add(placeList[i])
                        }
                        Toast.makeText(this, listGyms.size.toString(), Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Failure -> {
                    placeDetails.let {
                        Toast.makeText(
                            this, it.message, Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(this, "Loading please wait", Toast.LENGTH_SHORT)
                        .show()

                }

            }


        })


        bind.back.setOnClickListener{
            finish()
        }

        for (i in 0 until listGyms.size){
            val fragment = PlaceFragment.newInstance(listGyms[i])
            places.add(fragment)
        }

        val pagerAdapter = ViewPagerAdapter(places, supportFragmentManager, lifecycle)
        bind.frame.adapter = pagerAdapter

        val intent = intent
        val type = intent.getIntExtra("type", 1)

        when(type){
            1 -> {
                bind.scholarName.text = "Restaurants and Hotels"
            }
            2 -> {
                bind.scholarName.text = "Restaurants and Hotels"
            }
            3 -> {
                bind.scholarName.text = "Mosques and Worship centers"

            }
            4 -> {
                bind.scholarName.text = "Museums and Parks"

            }
            5 -> {
                bind.scholarName.text = "Movie theatres and Pools"
            }
            else -> {
                bind.scholarName.text = "Entertainment places"

            }
        }
    }


}