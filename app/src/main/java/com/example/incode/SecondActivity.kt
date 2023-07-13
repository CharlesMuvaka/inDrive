package com.example.incode

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.incode.adapters.ViewPagerAdapter
import com.example.incode.datab.Places
import com.example.incode.databinding.ActivitySecondBinding
import com.example.incode.fragments.PlaceFragment
import com.example.incode.models.PlaceResult
import com.example.incode.models.Resource
import com.example.incode.repository.PlacesRepo
import com.example.incode.viewmodel.PlacesModelFactory
import com.example.incode.viewmodel.PlacesViewModel

class SecondActivity : AppCompatActivity() {
    private lateinit var bind: ActivitySecondBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val intent = intent
        val place = intent.getSerializableExtra("place") as PlaceResult

        val fragment = PlaceFragment.newInstance(place)
        replaceFragment(fragment)


    }

    private fun replaceFragment(fragment: Fragment){
        val manager = supportFragmentManager.beginTransaction().replace(bind.frame.id, fragment)
        manager.commit()
    }


}