package com.example.incode

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.incode.databinding.ActivitySecondBinding
import com.example.incode.fragments.PlaceFragment
import com.example.incode.models.PlaceResult

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