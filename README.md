# Converty - Universal Unit Converter

A modern Android application built with Jetpack Compose that provides comprehensive unit conversion capabilities including length, temperature, and currency conversions with real-time exchange rates.

## 🚀 Features

### Core Conversion Types
- **Length Conversion**: Convert between inches, feet, centimeters, and meters
- **Temperature Conversion**: Convert between Celsius and Fahrenheit
- **Currency Conversion**: Convert between 80+ global currencies with live exchange rates

### Key Capabilities
- ✨ **Real-time Currency Exchange Rates**: Fetches live exchange rates from CurrencyFreaks API
- 🔄 **Unit Swapping**: Easily swap from/to units with a single tap
- 🎯 **Input Validation**: Smart input validation with error messaging
- 🌙 **Modern UI**: Beautiful Material Design 3 interface with Jetpack Compose
- ⚡ **Instant Conversion**: Real-time conversion as you type
- 🧹 **Clear Input**: Quick clear functionality for easy reset

## 📱 Screenshots

The app features a clean, intuitive interface with:
- **Conversion Type Selector**: Choose between Length, Temperature, and Currency
- **Input Field**: Enter the value you want to convert
- **Unit Dropdowns**: Select source and target units
- **Swap Button**: Quickly interchange units
- **Results Display**: Formatted conversion results
- **Error Handling**: Clear error messages for invalid inputs

## 🏗️ Architecture

This app follows **Clean Architecture** principles with **MVVM** pattern:

### Architecture Layers
```
├── Presentation Layer (UI)
│   ├── Screens (Jetpack Compose)
│   ├── ViewModels
│   ├── UI Components
│   └── Theme
├── Domain Layer (Business Logic)
│   ├── Models
│   ├── Use Cases
│   └── Repository Interfaces
└── Data Layer
    ├── Repository Implementations
    ├── Remote Data Sources (API)
    └── DTOs
```

### Key Components
- **Dependency Injection**: Dagger Hilt for clean dependency management
- **Reactive UI**: StateFlow and Compose for reactive programming
- **Network Layer**: Retrofit with Kotlin Serialization for API calls
- **Error Handling**: Comprehensive error handling with user-friendly messages

## 🛠️ Tech Stack

### Core Technologies
- **Kotlin**: Modern Android development language
- **Jetpack Compose**: Modern declarative UI toolkit
- **Material Design 3**: Latest Material Design components

### Architecture & DI
- **Dagger Hilt**: Dependency injection framework
- **MVVM Pattern**: Model-View-ViewModel architecture
- **Clean Architecture**: Separation of concerns

### Networking & Data
- **Retrofit**: HTTP client for API calls
- **Kotlin Serialization**: JSON serialization/deserialization
- **OkHttp**: HTTP client with logging interceptor

### Reactive Programming
- **Coroutines**: Asynchronous programming
- **StateFlow**: State management
- **Compose State**: UI state management

## 📋 Supported Units

### Length Units
- **Inches (in)**: Imperial measurement
- **Feet (ft)**: Imperial measurement  
- **Centimeters (cm)**: Metric measurement
- **Meters (m)**: Base metric unit

### Temperature Units
- **Celsius (°C)**: Metric temperature scale
- **Fahrenheit (°F)**: Imperial temperature scale

### Currency Support
Over 80 international currencies including:
- **Major Currencies**: USD, EUR, GBP, JPY, AUD, CAD, CHF
- **Asian Currencies**: CNY, INR, KRW, SGD, HKD, THB, MYR, PHP, IDR, VND
- **European Currencies**: SEK, NOK, DKK, PLN, CZK, HUF, RUB
- **Middle Eastern**: SAR, AED, QAR, KWD, BHD, OMR, JOD
- **African Currencies**: ZAR, EGP, MAD, NGN, KES, GHS, TND
- **Latin American**: BRL, MXN, ARS, CLP, COP, PEN
- **And many more...**

## 🔧 Setup & Installation

### Prerequisites
- Android Studio Arctic Fox or later
- Kotlin 1.9.0 or later
- Android SDK 24+ (Android 7.0)
- Target SDK 34 (Android 14)

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd converty
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **API Key Configuration**
   - Sign up at [CurrencyFreaks](https://currencyfreaks.com/) for a free API key
   - Replace `YOUR_API_KEY_HERE` in `CurrencyApiService.kt` with your actual API key

4. **Build and Run**
   ```bash
   ./gradlew assembleDebug
   ```

### Build Configuration
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Java Version**: 11
- **Kotlin Version**: 1.9.0

## 📁 Project Structure

```
app/src/main/java/com/example/myapplication/
├── data/
│   ├── remote/
│   │   ├── api/           # API service interfaces
│   │   └── dto/           # Data transfer objects
│   └── repository/        # Repository implementations
├── di/                    # Dependency injection modules
├── domain/
│   ├── model/            # Domain models
│   ├── repository/       # Repository interfaces
│   └── usecase/          # Business logic use cases
├── presentation/
│   ├── component/        # Reusable UI components
│   ├── model/           # UI state models
│   ├── screen/          # Compose screens
│   ├── theme/           # App theming
│   └── viewmodel/       # ViewModels
├── MainActivity.kt       # Main activity
└── UnitConverterApplication.kt  # Application class
```

## 🔄 Conversion Logic

### Length Conversion
All length conversions use meters as the base unit:
- Input value → Convert to meters → Convert to target unit
- Conversion factors stored in enum classes

### Temperature Conversion
Direct conversion formulas:
- **Celsius to Fahrenheit**: `(C × 9/5) + 32`
- **Fahrenheit to Celsius**: `(F - 32) × 5/9`

### Currency Conversion
- Fetches real-time exchange rates from CurrencyFreaks API
- Base currency: USD
- Handles API errors gracefully with user feedback
- Caches results for performance

## 🧪 Testing

The project includes:
- **Unit Tests**: Business logic validation
- **Instrumented Tests**: Android-specific functionality
- **UI Tests**: Compose UI testing

Run tests:
```bash
./gradlew test           # Unit tests
./gradlew connectedAndroidTest  # Instrumented tests
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 🙏 Acknowledgments

- **CurrencyFreaks API** for providing free currency exchange rates
- **Material Design** for the beautiful design system
- **Jetpack Compose** team for the amazing UI toolkit
- **Android Architecture Components** for robust architecture patterns

