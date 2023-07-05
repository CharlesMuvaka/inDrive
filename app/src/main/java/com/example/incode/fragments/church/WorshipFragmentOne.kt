package com.example.incode.fragments.church

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.R
import com.example.incode.databinding.FragmentWorshipOneBinding
import com.example.incode.fragments.MainFragment
import com.example.incode.models.PlaceResult

class WorshipFragmentOne : Fragment() {
    private lateinit var bind: FragmentWorshipOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentWorshipOneBinding.inflate(inflater)
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(R.id.action_worshipFragmentOne_to_worshipFragmentTwo)
        }, 5000)
//
//        val worshipPlace = MainFragment().worshipList!![0]
//        bind.placeName.text = worshipPlace.name

        return bind.root
    }

}