package com.example.incode.models

import com.example.incode.datab.Drivers

class Ride(val origin: String, val destination: String, val driver: Driver) {
    var date: String? = null
    var time: String? = null
}

