package com.example.incode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.incode.repository.DirectionsRepo

class DirectionsViewModelFactory(private val origin: String, private val dest: String, private val repo: DirectionsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DirectionsViewModel(origin = origin, dest = dest, repo = repo ) as T
    }
}