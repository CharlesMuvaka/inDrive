package com.example.incode.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incode.models.Resource
import com.example.incode.models.TestResultsOne
import com.example.incode.repository.PlacesRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class PlacesViewModel(private val searchType: String, private val place: String, private val repo: PlacesRepo): ViewModel() {
    val places: MutableLiveData<Resource<TestResultsOne>> = MutableLiveData()

    init {
        getPlacesData(searchType, place)
    }

    private fun getPlacesData(searchType: String, place: String) {
        viewModelScope.launch {
            places.postValue(Resource.Loading())
            val response = repo.getPlaces(searchType, place)
            places.postValue(getPlacesState(response))
        }
    }

    private fun getPlacesState(response: Response<TestResultsOne>): Resource<TestResultsOne>? {
        if (response.isSuccessful){
            response.body()?.let {placesResult ->
            return Resource.Success(placesResult)
            }
        }
        return Resource.Failure(error = response.message())
    }
}