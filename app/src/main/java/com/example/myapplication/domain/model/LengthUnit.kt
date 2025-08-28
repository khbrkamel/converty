package com.example.myapplication.domain.model

enum class LengthUnit(val displayName: String, val symbol: String, val toMeters: Double) {
    INCH("Inches", "in", 0.0254),
    FEET("Feet", "ft", 0.3048),
    CENTIMETER("Centimeters", "cm", 0.01),
    METER("Meters", "m", 1.0)
}
