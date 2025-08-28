package com.example.myapplication.presentation.screen

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.component.ConversionCard
import com.example.myapplication.presentation.component.ConversionTypeSelector
import com.example.myapplication.presentation.viewmodel.ConversionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionScreen(
    viewModel: ConversionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        text = "Unit Converter",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Welcome text
                Text(
                    text = "Convert between units, temperatures, and currencies with real-time exchange rates",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                
                // Conversion type selector
                ConversionTypeSelector(
                    selectedType = uiState.selectedConversionType,
                    onTypeSelected = viewModel::onConversionTypeChanged
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Conversion card
                ConversionCard(
                    conversionType = uiState.selectedConversionType,
                    inputValue = uiState.inputValue,
                    outputValue = uiState.outputValue,
                    isInputValid = uiState.isInputValid,
                    errorMessage = uiState.errorMessage,
                    isLoading = uiState.isLoading,
                    fromLengthUnit = uiState.selectedFromLengthUnit,
                    toLengthUnit = uiState.selectedToLengthUnit,
                    fromTemperatureUnit = uiState.selectedFromTemperatureUnit,
                    toTemperatureUnit = uiState.selectedToTemperatureUnit,
                    fromCurrency = uiState.selectedFromCurrency,
                    toCurrency = uiState.selectedToCurrency,
                    onInputValueChange = viewModel::onInputValueChanged,
                    onFromLengthUnitChange = viewModel::onFromLengthUnitChanged,
                    onToLengthUnitChange = viewModel::onToLengthUnitChanged,
                    onFromTemperatureUnitChange = viewModel::onFromTemperatureUnitChanged,
                    onToTemperatureUnitChange = viewModel::onToTemperatureUnitChanged,
                    onFromCurrencyChange = viewModel::onFromCurrencyChanged,
                    onToCurrencyChange = viewModel::onToCurrencyChanged,
                    onSwapUnits = viewModel::swapUnits,
                    onClearInput = viewModel::clearInput
                )
                
                // Footer
                Text(
                    text = "Accurate conversions with live exchange rates for global currencies",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                )
            }
        }
    }
}
