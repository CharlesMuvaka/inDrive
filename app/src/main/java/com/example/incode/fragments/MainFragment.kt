package com.example.incode.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
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
    private var placesList = ArrayList<PlaceResult>()

    private lateinit var bind: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentMainBinding.inflate(inflater)

        //initialising the view model
        val viewModel = (activity as MainActivity).placesViewModel

        //getting the response from the view model
        viewModel.places.observe(viewLifecycleOwner, Observer { placesDetails ->
            when (placesDetails) {
                is Resource.Success -> {
                    placesDetails.let {
                        val resultsList = it.placesData!!.results
                        for (result in resultsList) {
                            placesList.add(result)
                        }
                    }
                }
                is Resource.Failure -> {
                    placesDetails.message.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()

                }
            }

        })

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