package com.example.incode.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

class Logout: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().finish()
    }
}