package com.example.incode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.incode.repository.PlacesRepo

class PlacesModelFactory(private val searchType: String, private val place: String, private val repo: PlacesRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlacesViewModel(searchType, place, repo) as T
    }
}