package com.example.incode.api

import com.example.incode.uitils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        private val retrofit by lazy{
           Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }

        val apiInstance by lazy {
            retrofit.create(GetPlacesApi::class.java)
        }
    }
}