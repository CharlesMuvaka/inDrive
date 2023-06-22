package com.example.incode.datab

import com.example.incode.R
import com.example.incode.models.Place

class Places {
    val places = ArrayList<Place>()
    init {
        getPlaces()
    }

    private fun getPlaces() {
        val place = Place()
        place.apply {
            title = "Charlies Palace"
            description = "Charlie Palace Hostel is situated in Medellín, 500 m from El Poblado Park. The property is located less than 1 km from Lleras Park. Pueblito Paisa is 2.6 km from the guest house. 70 Avenue is 5 km from the accommodation, while Plaza de Toros La Macarena is 4.1 km away."
            popularActivity = "Gymnesium"
            image = R.drawable.hike
        }

        places.add(place)
        val place1 = Place()
        place1.apply {
            title = "Kenya Cinemax"
            description= "Charlie Palace Hostel is situated in Medellín, 500 m from El Poblado Park. The property is located less than 1 km from Lleras Park. Pueblito Paisa is 2.6 km from the guest house. 70 Avenue is 5 km from the accommodation, while Plaza de Toros La Macarena is 4.1 km away."
            popularActivity = "Moview Theatre"
            image = R.drawable.gym

        }
        places.add(place1)

        val place2 = Place()
        place2.apply {
            title = "Nyayo Stadium"
            description = "Charlie Palace Hostel is situated in Medellín, 500 m from El Poblado Park. The property is located less than 1 km from Lleras Park. Pueblito Paisa is 2.6 km from the guest house. 70 Avenue is 5 km from the accommodation, while Plaza de Toros La Macarena is 4.1 km away."
            popularActivity = "Swimming"
            image = R.drawable.download

        }
        places.add(place2)

        val place3 = Place()
        place3.apply {
            title = "Ngong hills"
            description = "Charlie Palace Hostel is situated in Medellín, 500 m from El Poblado Park. The property is located less than 1 km from Lleras Park. Pueblito Paisa is 2.6 km from the guest house. 70 Avenue is 5 km from the accommodation, while Plaza de Toros La Macarena is 4.1 km away."
            popularActivity = "Hiking"
            image = R.drawable.hike

        }
        places.add(place3)

        val place4 = Place()
        place4.apply {
            title = "Masjid taqwa"
            description = "Charlie Palace Hostel is situated in Medellín, 500 m from El Poblado Park. The property is located less than 1 km from Lleras Park. Pueblito Paisa is 2.6 km from the guest house. 70 Avenue is 5 km from the accommodation, while Plaza de Toros La Macarena is 4.1 km away."
            popularActivity = "Praying"
            image = R.drawable.hike

        }
        places.add(place4)

    }
}