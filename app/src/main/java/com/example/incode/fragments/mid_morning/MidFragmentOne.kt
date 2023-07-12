package com.example.incode.fragments.mid_morning

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
import com.example.incode.databinding.FragmentMidOneBinding
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MidFragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class MidFragmentOne : Fragment() {
    private lateinit var bind: FragmentMidOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentMidOneBinding.inflate(inflater)
        bind.root.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.enter_anim)
        )
        val place = (activity as MainActivity).listGyms[6]
        bind.evening.text = "Gorillas and Chimps"
        bind.placeName.text = "${place.name.split(" ")[0]} ${place.name.split(" ")[1]} city museum"
        //Picasso.get().load(place.photos[0].photo_reference).into(bind.placeImage)

        val action = MidFragmentOneDirections.actionMidFragmentOneToMidFragmentThree()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)

        bind.root.setOnClickListener{
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("type", 5)

            startActivity(intent)
        }
        return bind.root
    }
}