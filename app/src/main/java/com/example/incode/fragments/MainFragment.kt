package com.example.incode.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incode.MainActivity
import com.example.incode.SecondActivity
import com.example.incode.adapters.PlacesAdapter
import com.example.incode.adapters.ViewPagerAdapter
import com.example.incode.databinding.FragmentMainBinding
import com.example.incode.models.Place
import com.skydoves.transformationlayout.TransformationCompat

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainFragment: Fragment(), View.OnClickListener {

    private lateinit var bind: FragmentMainBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentMainBinding.inflate(inflater)

        val places = (activity as MainActivity).places
        val adp = PlacesAdapter()
        adp.asyncList.submitList(places)

        bind.firstRec.apply {
            adapter = adp
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        bind.secRec.apply {
            adapter = adp
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

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