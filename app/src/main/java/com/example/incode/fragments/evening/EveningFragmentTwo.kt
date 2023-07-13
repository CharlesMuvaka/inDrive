package com.example.incode.fragments.evening

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.MainActivity
import com.example.incode.R
import com.example.incode.SecondActivity
import com.example.incode.databinding.FragmentEveningTwoBinding
import com.squareup.picasso.Picasso


class EveningFragmentTwo : Fragment() {
   private lateinit var bind: FragmentEveningTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentEveningTwoBinding.inflate(inflater)
        bind.root.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.enter_anim)
        )

        val place = (activity as MainActivity).listGyms[9]
        bind.evening.text = "No, its movie time"
        bind.placeName.text = "${place.name.split(" ")[0]} ${place.name.split(" ")[1]} cinemax"
        //Picasso.get().load(place.photos[0].photo_reference).into(bind.placeImage)

        val action = EveningFragmentTwoDirections.actionEveningFragmentTwoToEveningFragmentOne()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)

        bind.root.setOnClickListener{
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("type", 3)

            startActivity(intent)
        }
        return bind.root
    }

}