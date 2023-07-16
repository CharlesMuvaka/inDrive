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
        val driver = Driver("Muvaka Charles", car).apply {
            phone = "+254768761610"
            location = "16th Street Koinange"
            distance = "16 miles"

        }
        drivers.add(driver)

        val car1 = Car("AUDI 300", "KBH4201Z")
        val driver1 = Driver("Leeroy Okumu", car1).apply {
            phone = "+91768761610"
            location = "10th Street Punjabi"
            distance = "11 miles"

        }
        drivers.add(driver1)


        val car2 = Car("Sunny Saloon 320", "KAH4201B")
        val driver2 = Driver("John Doe", car2).apply {
            phone = "+255768871610"
            location = "Kigali, Rwanda"
            distance = "6 miles"

        }
        drivers.add(driver2)


        val car3 = Car("Rosa 624", "KDH4201C")
        val driver3 = Driver("Brad Pitt", car3).apply {
            phone = "+234767461610"
            location = "Along Comoro island"
            distance = "23 miles"

        }
        drivers.add(driver3)


        val car4 = Car("Mark X", "KCD4201D")
        val driver4 = Driver("Elon Tusk", car4).apply {
            phone = "+23878761610"
            location = "12th Street Magongo"
            distance = "34 miles"

        }
        drivers.add(driver4)


    }
}