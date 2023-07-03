package com.example.incode.api

import com.example.incode.models.PlaceResult
import com.example.incode.uitils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface GetPlacesApi {
    @GET
    fun getPlace(
        @Query("query")
        searchType: String,
        @Query("query")
        placeFound: String,
        @Query("key")
        apiKey:String = Constants.yes
    ): List<PlaceResult>
}