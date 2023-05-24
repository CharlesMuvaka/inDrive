package com.example.incode.models

class Driver {
    var name: String? = null
    var phone: String? = null
    var email: String? = null
    var location: String? = null
    var car: Car? = null

    constructor()
    constructor(name: String, car: Car) {
        this.name = name
        this.car = car
    }
}

