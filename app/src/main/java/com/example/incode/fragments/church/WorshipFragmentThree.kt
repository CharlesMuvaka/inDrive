package com.example.incode.fragments.church

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.MainActivity
import com.example.incode.R
import com.example.incode.SecondActivity
import com.example.incode.databinding.FragmentWorshipThreeBinding
import com.example.incode.models.Resource
import com.squareup.picasso.Picasso

class WorshipFragmentThree : Fragment() {
    private lateinit var bind: FragmentWorshipThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentWorshipThreeBinding.inflate(inflater)
        val intent = Intent(requireContext(), SecondActivity::class.java)

        //val gyms = (activity as MainActivity).listGyms
        //bind.placeName.text = gyms[gyms.size - 1].name

        bind.root.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.enter_anim)
        )
        val place = (activity as MainActivity).listGyms[13]
        bind.evening.text = "Break your muscles"
        bind.placeName.text = "${place.name.split(" ")[0]} ${place.name.split(" ")[1]} Shrine"
        //Picasso.get().load(place.photos[0].photo_reference).into(bind.placeImage)


        val action = WorshipFragmentThreeDirections.actionWorshipFragmentThreeToWorshipFragmentOne()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)

        bind.root.setOnClickListener{
            intent.putExtra("type", 2)

            startActivity(intent)
        }
        return bind.root
    }

}