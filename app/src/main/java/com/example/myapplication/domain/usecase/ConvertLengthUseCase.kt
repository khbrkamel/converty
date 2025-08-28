package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.ConversionResult
import com.example.myapplication.domain.model.LengthUnit
import com.example.myapplication.domain.repository.ConversionRepository
import javax.inject.Inject

class ConvertLengthUseCase @Inject constructor(
    private val repository: ConversionRepository
) {
    operator fun invoke(value: Double, fromUnit: LengthUnit, toUnit: LengthUnit): ConversionResult {
        return repository.convertLength(value, fromUnit, toUnit)
    }
}
