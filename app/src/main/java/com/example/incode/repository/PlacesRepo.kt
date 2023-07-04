package com.example.incode.repository

import com.example.incode.api.RetrofitClient

class PlacesRepo {
    suspend fun getPlaces(type: String, place: String) = RetrofitClient.apiInstance.getPlace(type,place)
}