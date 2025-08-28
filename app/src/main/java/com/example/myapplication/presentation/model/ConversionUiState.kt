package com.example.myapplication.presentation.model

import com.example.myapplication.domain.model.ConversionType
import com.example.myapplication.domain.model.Currency
import com.example.myapplication.domain.model.LengthUnit
import com.example.myapplication.domain.model.TemperatureUnit

data class ConversionUiState(
    val selectedConversionType: ConversionType = ConversionType.LENGTH,
    val inputValue: String = "",
    val outputValue: String = "",
    val selectedFromLengthUnit: LengthUnit = LengthUnit.INCH,
    val selectedToLengthUnit: LengthUnit = LengthUnit.CENTIMETER,
    val selectedFromTemperatureUnit: TemperatureUnit = TemperatureUnit.CELSIUS,
    val selectedToTemperatureUnit: TemperatureUnit = TemperatureUnit.FAHRENHEIT,
    val selectedFromCurrency: Currency = Currency.USD,
    val selectedToCurrency: Currency = Currency.EUR,
    val isInputValid: Boolean = true,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
