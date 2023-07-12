package com.example.incode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.incode.databinding.FragmentPlaceBinding
import com.example.incode.models.Place
import com.example.incode.models.PlaceResult

class PlaceFragment: Fragment() {
    private lateinit var bind: FragmentPlaceBinding
    private final val PLACE = "place"
    private var place: Place? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            place = it.getSerializable(PLACE) as Place
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentPlaceBinding.inflate(inflater)

        return bind.root
    }

    companion object{
        @JvmStatic
        fun newInstance(place: PlaceResult) = PlaceFragment().apply {
            arguments = Bundle().apply {
                putSerializable(PLACE,place )
            }
        }
    }
}