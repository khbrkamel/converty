package com.example.myapplication.data.repository

import com.example.myapplication.domain.model.ConversionResult
import com.example.myapplication.domain.model.Currency
import com.example.myapplication.domain.model.LengthUnit
import com.example.myapplication.domain.model.TemperatureUnit
import com.example.myapplication.domain.repository.ConversionRepository
import com.example.myapplication.domain.repository.CurrencyRepository
import java.text.DecimalFormat
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class ConversionRepositoryImpl @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ConversionRepository {

    private val decimalFormat = DecimalFormat("#.####")

    override fun convertLength(value: Double, fromUnit: LengthUnit, toUnit: LengthUnit): ConversionResult {
        if (fromUnit == toUnit) {
            return ConversionResult(value, formatValue(value))
        }

        // Convert to meters first, then to target unit
        val valueInMeters = value * fromUnit.toMeters
        val convertedValue = valueInMeters / toUnit.toMeters

        return ConversionResult(convertedValue, formatValue(convertedValue))
    }

    override fun convertTemperature(value: Double, fromUnit: TemperatureUnit, toUnit: TemperatureUnit): ConversionResult {
        if (fromUnit == toUnit) {
            return ConversionResult(value, formatValue(value))
        }

        val convertedValue = when (fromUnit to toUnit) {
            TemperatureUnit.CELSIUS to TemperatureUnit.FAHRENHEIT -> (value * 9.0 / 5.0) + 32.0
            TemperatureUnit.FAHRENHEIT to TemperatureUnit.CELSIUS -> (value - 32.0) * 5.0 / 9.0
            else -> value // This shouldn't happen with our current units
        }

        return ConversionResult(convertedValue, formatValue(convertedValue))
    }
    
    override suspend fun convertCurrency(value: Double, fromCurrency: Currency, toCurrency: Currency): Result<ConversionResult> {
        return currencyRepository.convertCurrency(value, fromCurrency, toCurrency)
    }

    private fun formatValue(value: Double): String {
        return when {
            value == 0.0 -> "0"
            abs(value) >= 1000000 -> decimalFormat.format(value)
            abs(value) >= 1 -> {
                val formatted = decimalFormat.format(value)
                // Remove trailing zeros after decimal point
                if (formatted.contains('.')) {
                    formatted.trimEnd('0').trimEnd('.')
                } else {
                    formatted
                }
            }
            else -> decimalFormat.format(value)
        }
    }
}
