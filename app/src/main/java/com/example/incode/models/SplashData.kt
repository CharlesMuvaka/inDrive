package com.example.incode.models

import java.io.Serializable

class SplashData(val title: String): Serializable {
    var description: String? = null
    var image: Int? = null
}