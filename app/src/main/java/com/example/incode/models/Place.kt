package com.example.incode.models

import java.io.Serializable

class Place: Serializable {
    var title: String? = null
    var image: Int? = null
    var popularActivity: String? = null
    var description :String? = null
    var distance: Int = 0
}