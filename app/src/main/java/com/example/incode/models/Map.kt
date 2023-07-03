package com.example.incode.models

class MapData{
    var routes = ArrayList<Routes>()
}

class Routes{
    var legs = ArrayList<Legs>()
}

class Legs{
    var distance = Distance()
    var duration = Duration()
    var endAddress = " "
    var startAddress = " "
    //var endLocation = Location()
    var steps = ArrayList<Steps>()
}

class Steps{
    var distance = Distance()
    var duration = Duration()
    var endAddress = " "
    var startAddress = " "
    //var endLocation = Location()
    //var startLocation = Location()
    var polyLine = PolyLine()
    var travelMode = " "
    var maneuver = ""
}

class Duration{
    var text = ""
    var value = 0
}

class Distance{
    var text = ""
    var value = 0
}

class PolyLine{
    var points = ""
}
