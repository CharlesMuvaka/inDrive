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
import com.example.incode.databinding.FragmentNoonThreeBinding
import com.squareup.picasso.Picasso


class NoonFragmentThree : Fragment() {
    private lateinit var bind: FragmentNoonThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentNoonThreeBinding.inflate(inflater)

        val place = (activity as MainActivity).listGyms[18]
        bind.evening.text = "Need a marmosa, we got you"
        bind.placeName.text = "${place.name.split(" ")[0]} ${place.name.split(" ")[1]} Restaurant"
            //Picasso.get().load(place.photos[0].photo_reference).into(bind.placeImage)

        bind.root.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.enter_slow_anim)
        )
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(R.id.action_noonFragmentThree_to_noonFragmentTwo)
        }, 5000)

        bind.root.setOnClickListener{
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("type", 1)

            startActivity(intent)
        }
        return bind.root

    }

}