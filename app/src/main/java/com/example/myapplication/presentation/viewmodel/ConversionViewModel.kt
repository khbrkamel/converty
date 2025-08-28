package com.example.myapplication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.ConversionType
import com.example.myapplication.domain.model.Currency
import com.example.myapplication.domain.model.LengthUnit
import com.example.myapplication.domain.model.TemperatureUnit
import com.example.myapplication.domain.usecase.ConvertCurrencyUseCase
import com.example.myapplication.domain.usecase.ConvertLengthUseCase
import com.example.myapplication.domain.usecase.ConvertTemperatureUseCase
import com.example.myapplication.presentation.model.ConversionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversionViewModel @Inject constructor(
    private val convertLengthUseCase: ConvertLengthUseCase,
    private val convertTemperatureUseCase: ConvertTemperatureUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConversionUiState())
    val uiState: StateFlow<ConversionUiState> = _uiState.asStateFlow()

    fun onConversionTypeChanged(conversionType: ConversionType) {
        _uiState.value = _uiState.value.copy(
            selectedConversionType = conversionType,
            inputValue = "",
            outputValue = "",
            errorMessage = null
        )
    }

    fun onInputValueChanged(value: String) {
        _uiState.value = _uiState.value.copy(
            inputValue = value,
            errorMessage = null
        )
        performConversion(value)
    }

    fun onFromLengthUnitChanged(unit: LengthUnit) {
        _uiState.value = _uiState.value.copy(selectedFromLengthUnit = unit)
        performConversion(_uiState.value.inputValue)
    }

    fun onToLengthUnitChanged(unit: LengthUnit) {
        _uiState.value = _uiState.value.copy(selectedToLengthUnit = unit)
        performConversion(_uiState.value.inputValue)
    }

    fun onFromTemperatureUnitChanged(unit: TemperatureUnit) {
        _uiState.value = _uiState.value.copy(selectedFromTemperatureUnit = unit)
        performConversion(_uiState.value.inputValue)
    }

    fun onToTemperatureUnitChanged(unit: TemperatureUnit) {
        _uiState.value = _uiState.value.copy(selectedToTemperatureUnit = unit)
        performConversion(_uiState.value.inputValue)
    }
    
    fun onFromCurrencyChanged(currency: Currency) {
        _uiState.value = _uiState.value.copy(selectedFromCurrency = currency)
        performConversion(_uiState.value.inputValue)
    }
    
    fun onToCurrencyChanged(currency: Currency) {
        _uiState.value = _uiState.value.copy(selectedToCurrency = currency)
        performConversion(_uiState.value.inputValue)
    }

    fun swapUnits() {
        val currentState = _uiState.value
        when (currentState.selectedConversionType) {
            ConversionType.LENGTH -> {
                _uiState.value = currentState.copy(
                    selectedFromLengthUnit = currentState.selectedToLengthUnit,
                    selectedToLengthUnit = currentState.selectedFromLengthUnit
                )
            }
            ConversionType.TEMPERATURE -> {
                _uiState.value = currentState.copy(
                    selectedFromTemperatureUnit = currentState.selectedToTemperatureUnit,
                    selectedToTemperatureUnit = currentState.selectedFromTemperatureUnit
                )
            }
            ConversionType.CURRENCY -> {
                _uiState.value = currentState.copy(
                    selectedFromCurrency = currentState.selectedToCurrency,
                    selectedToCurrency = currentState.selectedFromCurrency
                )
            }
        }
        performConversion(_uiState.value.inputValue)
    }

    fun clearInput() {
        _uiState.value = _uiState.value.copy(
            inputValue = "",
            outputValue = "",
            errorMessage = null
        )
    }

    private fun performConversion(inputValue: String) {
        if (inputValue.isEmpty()) {
            _uiState.value = _uiState.value.copy(
                outputValue = "",
                isInputValid = true,
                isLoading = false
            )
            return
        }

        viewModelScope.launch {
            try {
                val value = inputValue.toDouble()
                val currentState = _uiState.value

                when (currentState.selectedConversionType) {
                    ConversionType.LENGTH -> {
                        val result = convertLengthUseCase(
                            value,
                            currentState.selectedFromLengthUnit,
                            currentState.selectedToLengthUnit
                        )
                        _uiState.value = currentState.copy(
                            outputValue = result.formattedValue,
                            isInputValid = true,
                            errorMessage = null,
                            isLoading = false
                        )
                    }
                    ConversionType.TEMPERATURE -> {
                        val result = convertTemperatureUseCase(
                            value,
                            currentState.selectedFromTemperatureUnit,
                            currentState.selectedToTemperatureUnit
                        )
                        _uiState.value = currentState.copy(
                            outputValue = result.formattedValue,
                            isInputValid = true,
                            errorMessage = null,
                            isLoading = false
                        )
                    }
                    ConversionType.CURRENCY -> {
                        _uiState.value = currentState.copy(isLoading = true)
                        
                        val result = convertCurrencyUseCase(
                            value,
                            currentState.selectedFromCurrency,
                            currentState.selectedToCurrency
                        )
                        
                        if (result.isSuccess) {
                            val conversionResult = result.getOrThrow()
                            _uiState.value = currentState.copy(
                                outputValue = conversionResult.formattedValue,
                                isInputValid = true,
                                errorMessage = null,
                                isLoading = false
                            )
                        } else {
                            _uiState.value = currentState.copy(
                                outputValue = "",
                                isInputValid = true,
                                errorMessage = "Failed to get exchange rates. Please try again.",
                                isLoading = false
                            )
                        }
                    }
                }
            } catch (e: NumberFormatException) {
                _uiState.value = _uiState.value.copy(
                    outputValue = "",
                    isInputValid = false,
                    errorMessage = "Please enter a valid number",
                    isLoading = false
                )
            }
        }
    }
}
