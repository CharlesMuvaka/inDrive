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

        return bind.root
    }

}