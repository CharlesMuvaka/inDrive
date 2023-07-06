package com.example.incode.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.incode.MainActivity
import com.example.incode.databinding.FragmentMainBinding
import com.example.incode.models.PlaceResult
import com.example.incode.models.Resource

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainFragment : Fragment(){
    private var worshipList = listOf<PlaceResult>()
    private var gymList = ArrayList<PlaceResult>()
    private var movieList = ArrayList<PlaceResult>()
    private var golfList = ArrayList<PlaceResult>()
    private var poolsList = ArrayList<PlaceResult>()
    private var restaurantsList = ArrayList<PlaceResult>()
    private var parksList = ArrayList<PlaceResult>()
    private var museumsList = ArrayList<PlaceResult>()

    private lateinit var bind: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMainBinding.inflate(inflater)

        //initialising the view model
        val restaurantModel = (activity as MainActivity).placesBreakModel
        val golfModel = (activity as MainActivity).golfViewModel
        val movieModel = (activity as MainActivity).movieViewModel
        val museumModel = (activity as MainActivity).placesEveningModel
        val parksModel = (activity as MainActivity).placesAfternoonModel
        val swimmingPoolModel = (activity as MainActivity).placesMidMorningModel

        var worship = listOf<PlaceResult>()

        //getting gym responses from the view model

        //getting restaurants from the api
        restaurantModel.places.observe(viewLifecycleOwner, Observer { placeDeatils ->
            when (placeDeatils) {
                is Resource.Success -> {
                    placeDeatils.let {
                        val restaurants = it.placesData!!.results
                        for (i in restaurants.indices) {
                            restaurantsList.add(restaurants[i])
                        }

                    }
                }

                is Resource.Failure -> {
                    placeDeatils.let {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Restaurants loading", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })

        //getting museums from the api
        museumModel.places.observe(viewLifecycleOwner, Observer { placeDetails ->
            when (placeDetails) {
                is Resource.Success -> {
                    placeDetails.let {
                        val museums = it.placesData!!.results
                        for (museum in museums) {
                            museumsList.add(museum)
                        }
                    }
                }

                is Resource.Failure -> {
                    placeDetails.let {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "museums loading", Toast.LENGTH_SHORT).show()

                }
            }

        })

        //getting parks from the api
        parksModel.places.observe(viewLifecycleOwner, Observer { placeDetails ->
            when (placeDetails) {
                is Resource.Success -> {
                    placeDetails.let {
                        val parks = it.placesData!!.results
                        for (park in parks) {
                            parksList.add(park)
                        }
                    }
                }

                is Resource.Failure -> {
                    placeDetails.let {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Parks Loading", Toast.LENGTH_SHORT).show()

                }
            }

        })

        //getting golf clubs from the api
        golfModel.places.observe(viewLifecycleOwner, Observer { golfPlaces ->
            when (golfPlaces) {
                is Resource.Success -> {
                    golfPlaces.let {
                        val golfClubs = it.placesData!!.results
                        for (golfClub in golfClubs) {
                            golfList.add(golfClub)
                        }
                    }
                }

                is Resource.Failure -> {
                    golfPlaces.let {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "golf clubs loading", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })

        //getting swimming pools from the api
        swimmingPoolModel.places.observe(viewLifecycleOwner, Observer { poolData ->
            when (poolData) {
                is Resource.Success -> {
                    poolData.let {
                        val pools = it.placesData!!.results
                        for (pool in pools) {
                            poolsList.add(pool)
                        }
                    }
                }

                is Resource.Failure -> {
                    poolData.let {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "swimming pools loading", Toast.LENGTH_SHORT)
                        .show()

                }

            }

        })

        //getting movie theatres from the api
        movieModel.places.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    it.let {
                        val theatres = it.placesData!!.results

                        for (theatre in theatres) {
                            movieList.add(theatre)
                        }
                    }
                }

                is Resource.Failure -> {
                    it.let {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Movie theatres loading", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })

        //viewing the size of the lists
        bind.bored.text = "restaurants: ${restaurantsList.size}, gym: ${gymList.size}"

        //setting onclick listeners
        //bind.fab.setOnClickListener(this::onClick)

        return bind.root
    }

}