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
class MainFragment : Fragment(), View.OnClickListener {
    private var worshipList = ArrayList<PlaceResult>()
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
        val viewModel = (activity as MainActivity).placesViewModel
        val gymModel = (activity as MainActivity).placesGymModel
        val restaurantModel = (activity as MainActivity).placesBreakModel
        val golfModel = (activity as MainActivity).golfViewModel
        val movieModel = (activity as MainActivity).movieViewModel
        val museumModel = (activity as MainActivity).placesEveningModel
        val parksModel = (activity as MainActivity).placesAfternoonModel
        val swimmingPoolModel = (activity as MainActivity).placesMidMorningModel

        //getting worship responses from the view model
        viewModel.places.observe(viewLifecycleOwner, Observer { placesDetails ->
            when (placesDetails) {
                is Resource.Success -> {
                    placesDetails.let {
                        val resultsList = it.placesData!!.results
                        bind.early.text = resultsList.size.toString()
                        //Toast.makeText(requireContext(), resultsList[0].name, Toast.LENGTH_SHORT).show()

                        for (result in resultsList) {
                            worshipList.add(result)
//                            bind.early.text = result.name

                        }
                    }
                }
                is Resource.Failure -> {
                    placesDetails.message.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        Log.d("Message Error", "onCreateView:$it ")
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()

                }
            }

        })

        //getting gym responses from the view model
        gymModel.places.observe(viewLifecycleOwner, Observer { placeDetails ->
        when(placeDetails){
            is Resource.Success -> {
                placeDetails.let {
                    val placeList = it.placesData!!.results

                    for (gym in placeList){
                        gymList.add(gym)
                    }

                }
            }

            is Resource.Failure -> {
                placeDetails.let {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }

            is Resource.Loading -> {
                Toast.makeText(requireContext(), "Loading please wait", Toast.LENGTH_SHORT).show()

            }

        }


        })

        //getting restaurants from the api
        restaurantModel.places.observe(viewLifecycleOwner, Observer{placeDeatils ->
            when(placeDeatils){
                is Resource.Success -> {
                    placeDeatils.let {
                        val restaurants = it.placesData!!.results
                        for (restaurant in restaurants){
                            restaurantsList.add(restaurant)
                        }

                    }
                }
                is Resource.Failure -> {
                    placeDeatils.let {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Restaurants loading", Toast.LENGTH_SHORT).show()
                }
            }

        })

        //getting museums from the api
        museumModel.places.observe(viewLifecycleOwner, Observer {placeDetails->
        when(placeDetails){
            is Resource.Success -> {
                placeDetails.let {
                    val museums = it.placesData!!.results
                    for (museum in museums){
                        museumsList.add(museum)
                    }
                }
            }
            is Resource.Failure -> {
                placeDetails.let{
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }

            is Resource.Loading -> {
                Toast.makeText(requireContext(), "museums loading", Toast.LENGTH_SHORT).show()

            }
        }

        })

        //getting parks from the api
        parksModel.places.observe(viewLifecycleOwner, Observer{placeDetails ->
            when(placeDetails){
                is Resource.Success -> {
                    placeDetails.let {
                        val parks = it.placesData!!.results
                        for (park in parks){
                            parksList.add(park)
                        }
                    }
                }
                is Resource.Failure -> {
                    placeDetails.let{
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Parks Loading", Toast.LENGTH_SHORT).show()

                }
            }

        })

        //getting golf clubs from the api
        golfModel.places.observe(viewLifecycleOwner, Observer{golfPlaces ->
            when (golfPlaces){
                is Resource.Success -> {
                    golfPlaces.let{
                        val golfClubs = it.placesData!!.results
                        for (golfClub in golfClubs){
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
                    Toast.makeText(requireContext(), "golf clubs loading", Toast.LENGTH_SHORT).show()
                }
            }

        })

        //getting swimming pools from the api
        swimmingPoolModel.places.observe(viewLifecycleOwner, Observer {poolData ->
        when(poolData){
            is Resource.Success -> {
                poolData.let {
                    val pools = it.placesData!!.results
                    for (pool in pools){
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
                Toast.makeText(requireContext(), "swimming pools loading", Toast.LENGTH_SHORT).show()

            }

        }

        })

        //getting movie theatres from the api
        movieModel.places.observe(viewLifecycleOwner, Observer{
            when(it){
                is Resource.Success -> {
                    it.let {
                        val theatres = it.placesData!!.results
                        bind.early.text = theatres.size.toString()

                        for (theatre in theatres){
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
                    Toast.makeText(requireContext(), "Movie theatres loading", Toast.LENGTH_SHORT).show()
                }
            }

        })

        //viewing the size of the lists
        bind.early.text = "worship: ${worshipList.size} movies: ${movieList.size}, pools: ${poolsList.size}, gyms: ${gymList.size} "


        //setting onclick listeners
        //bind.fab.setOnClickListener(this::onClick)

        return bind.root
    }


    override fun onClick(p0: View?) {
//        if (p0 == bind.fab){
//            val transformLayout = bind.transformationOverview
//            val intent = Intent(requireContext(), SecondActivity::class.java)
//            intent.putExtra("layout", 1)
//            TransformationCompat.startActivity(transformLayout, intent)
//        }
    }
}