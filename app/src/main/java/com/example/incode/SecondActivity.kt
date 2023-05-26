package com.example.incode

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.incode.databinding.ActivitySecondBinding
import com.example.incode.fragments.HomeFragment
import com.skydoves.transformationlayout.TransformationAppCompatActivity

class SecondActivity: TransformationAppCompatActivity() {
    private lateinit var bind: ActivitySecondBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val intent = intent
        val layout = intent.getIntExtra("layout", 0)
        if (layout == 1){
            val fragment = HomeFragment()
            replaceFragment(fragment)
        }

    }
    private fun replaceFragment(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction().replace(bind.frame.id, fragment).commit()

    }
}