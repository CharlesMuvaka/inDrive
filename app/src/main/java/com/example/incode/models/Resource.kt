package com.example.incode.models

sealed class Resource<T>(val placesData: T? = null, val message: String? = null) {
    class Success<T>(private val places: T): Resource<T>(places)
    class Failure<T>(private val data: T? = null, private val error:String): Resource<T>(data, error)
    class Loading<T>(): Resource<T>()
}