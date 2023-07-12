package com.example.incode.fragments.afternoon

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
import com.example.incode.databinding.FragmentNoonOneBinding
import com.squareup.picasso.Picasso

class NoonFragmentOne : Fragment() {
    private lateinit var bind: FragmentNoonOneBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentNoonOneBinding.inflate(inflater)
        val place = (activity as MainActivity).listGyms[19]

        bind.evening.text = "Hey, its lunch, join us"
        bind.placeName.text = "${place.name.split(" ")[0]} ${place.name.split(" ")[1]} Restaurant"
        //Picasso.get().load(place.photos[0].photo_reference).into(bind.placeImage)

        bind.root.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.enter_slow_anim)
        )

        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(R.id.action_noonFragmentOne_to_noonFragmentThree)
        }, 5000)
        bind.root.setOnClickListener{
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("type", 1)
            startActivity(intent)
        }
        return bind.root
    }

}