package com.example.incode.repository

import com.example.incode.api.RetrofitClient

class DirectionsRepo {
    suspend fun getDirections(origin: String, destination: String) = RetrofitClient.apiInstance.getDirections(origin = origin, destinations = destination)
}