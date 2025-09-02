package com.kamel.projects.domain.repository

import com.kamel.projects.domain.model.ConversionResult
import com.kamel.projects.domain.model.Currency
import com.kamel.projects.domain.model.LengthUnit
import com.kamel.projects.domain.model.TemperatureUnit

interface ConversionRepository {
    fun convertLength(value: Double, fromUnit: LengthUnit, toUnit: LengthUnit): ConversionResult
    fun convertTemperature(value: Double, fromUnit: TemperatureUnit, toUnit: TemperatureUnit): ConversionResult
    suspend fun convertCurrency(value: Double, fromCurrency: Currency, toCurrency: Currency): Result<ConversionResult>
}
