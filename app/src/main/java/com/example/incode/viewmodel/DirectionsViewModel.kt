package com.example.incode.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incode.models.Resource
import com.example.incode.models.RouteDirections
import com.example.incode.repository.DirectionsRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class DirectionsViewModel(private val origin: String, private val dest: String, private val repo: DirectionsRepo): ViewModel() {
    val directions: MutableLiveData<Resource<RouteDirections>> = MutableLiveData()

    init {
        getRouteDirections(origin, dest)
    }

    private fun getRouteDirections(origin: String, dest: String) {
        viewModelScope.launch{
            directions.postValue(Resource.Loading())
            val response = repo.getDirections(origin, dest)
            directions.postValue(getRouteDirectionsState(response))
        }
    }

    private fun getRouteDirectionsState(response: Response<RouteDirections>): Resource<RouteDirections>? {
        if (response.isSuccessful){
            response.body()?.let {routeDirection ->
                return Resource.Success(routeDirection)
            }
        }
        return Resource.Failure(error = response.message())
    }
}