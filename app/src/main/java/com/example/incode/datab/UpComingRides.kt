package com.example.incode.datab

import com.example.incode.models.Ride

class UpComingRides{
    val upComingRides = ArrayList<Ride>()

    init {
        addRides()
    }

    private fun addRides(){
        val ride = Ride("GSU Training school", "Garyl Restaurant", Drivers().drivers[0]).apply {
            time = "0835"
            date = "Sunday, 16th July"
        }
        upComingRides.add(ride)
        val ride1 = Ride("Utawala shooters", "Smart Gyms", Drivers().drivers[Drivers().drivers.size - 1]).apply {
            time = "1135"
            date = "Sunday, 16th July"
        }
        upComingRides.add(ride1)
    }
}
