package com.example.myapplication.di

import com.example.myapplication.data.remote.api.CurrencyApiService
import com.example.myapplication.data.repository.ConversionRepositoryImpl
import com.example.myapplication.data.repository.CurrencyRepositoryImpl
import com.example.myapplication.domain.repository.ConversionRepository
import com.example.myapplication.domain.repository.CurrencyRepository
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindConversionRepository(
        conversionRepositoryImpl: ConversionRepositoryImpl
    ): ConversionRepository
    
    @Binds
    abstract fun bindCurrencyRepository(
        currencyRepositoryImpl: CurrencyRepositoryImpl
    ): CurrencyRepository
    
    companion object {
        
        @Provides
        @Singleton
        fun provideJson(): Json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()
        }
        
        @Provides
        @Singleton
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            json: Json
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(CurrencyApiService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(
                    json.asConverterFactory("application/json".toMediaType())
                )
                .build()
        }
        
        @Provides
        @Singleton
        fun provideCurrencyApiService(retrofit: Retrofit): CurrencyApiService {
            return retrofit.create(CurrencyApiService::class.java)
        }
    }
}
