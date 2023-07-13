package com.example.incode.fragments.church

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.MainActivity
import com.example.incode.R
import com.example.incode.SecondActivity
import com.example.incode.databinding.FragmentWorshipOneBinding
import com.example.incode.fragments.MainFragment
import com.example.incode.models.PlaceResult
import com.example.incode.models.Resource
import com.squareup.picasso.Picasso

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
        //val viewModel = (activity as MainActivity).listworship
        //getting worship responses from the view model
       // bind.placeName.text = viewModel[0].name

        bind.root.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.enter_anim)
        )

        val place = (activity as MainActivity).listGyms[13]
        bind.evening.text = "Cleanse your aura"
        bind.placeName.text = "${place.name.split(" ")[0]} ${place.name.split(" ")[1]} Mosque"
        //Picasso.get().load(place.photos[0].photo_reference).into(bind.placeImage)


        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(R.id.action_worshipFragmentOne_to_worshipFragmentTwo)
        }, 5000)

        bind.root.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("type", 2)

            startActivity(intent)
        }

        return bind.root
    }

}