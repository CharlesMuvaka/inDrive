package com.example.incode.datab

import com.example.incode.models.Car
import com.example.incode.models.Driver

class Drivers {
    val drivers = ArrayList<Driver>()

    init {
        getAppDrivers()
    }

    private fun getAppDrivers() {
        val car = Car("Mercedes 320", "KCH4201A")
        val driver = Driver("Muvaka Charles", car)
        drivers.add(driver)

        val car1 = Car("AUDI 300", "KBH4201Z")
        val driver1 = Driver("Leeroy Okumu", car1)
        drivers.add(driver1)


        val car2 = Car("Sunny Saloon 320", "KAH4201B")
        val driver2 = Driver("John Doe", car2)
        drivers.add(driver2)


        val car3 = Car("Rosa 624", "KDH4201C")
        val driver3 = Driver("Brad Pitt", car3)
        drivers.add(driver3)


        val car4 = Car("Mark X", "KCD4201D")
        val driver4 = Driver("Elon Tusk", car4)
        drivers.add(driver4)


    }
}