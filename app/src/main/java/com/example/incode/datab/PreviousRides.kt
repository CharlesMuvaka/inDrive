package com.example.incode.datab

import com.example.incode.models.Ride

class PreviousRides{
    val previousRides = ArrayList<Ride>()

    init {
        addRides()
    }

    private fun addRides(){
        val ride = Ride("GPO", "Tassia, Embakasi", Drivers().drivers[1]).apply {
            time = "1135"
            date = "Sunday, 9th July"
        }
        previousRides.add(ride)
        val ride1 = Ride("RAilway training institute", "Sana sana, South B", Drivers().drivers[2]).apply {
            time = "1135"
            date = "Sunday, 9th July"
        }
        previousRides.add(ride1)
    }
}