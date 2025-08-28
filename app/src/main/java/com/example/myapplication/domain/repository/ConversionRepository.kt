package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.ConversionResult
import com.example.myapplication.domain.model.Currency
import com.example.myapplication.domain.model.LengthUnit
import com.example.myapplication.domain.model.TemperatureUnit

interface ConversionRepository {
    fun convertLength(value: Double, fromUnit: LengthUnit, toUnit: LengthUnit): ConversionResult
    fun convertTemperature(value: Double, fromUnit: TemperatureUnit, toUnit: TemperatureUnit): ConversionResult
    suspend fun convertCurrency(value: Double, fromCurrency: Currency, toCurrency: Currency): Result<ConversionResult>
}
