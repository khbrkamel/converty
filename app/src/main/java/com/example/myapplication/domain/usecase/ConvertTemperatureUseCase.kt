package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.ConversionResult
import com.example.myapplication.domain.model.TemperatureUnit
import com.example.myapplication.domain.repository.ConversionRepository
import javax.inject.Inject

class ConvertTemperatureUseCase @Inject constructor(
    private val repository: ConversionRepository
) {
    operator fun invoke(value: Double, fromUnit: TemperatureUnit, toUnit: TemperatureUnit): ConversionResult {
        return repository.convertTemperature(value, fromUnit, toUnit)
    }
}
