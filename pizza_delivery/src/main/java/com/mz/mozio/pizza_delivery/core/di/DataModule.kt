package com.mz.mozio.pizza_delivery.core.di

import com.mz.mozio.pizza_delivery.data.api.PizzaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providePizzaService(
        okHttpClient: OkHttpClient
    ): PizzaService =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PizzaService::class.java)


    @Provides
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()


    private const val API_URL = "https://static.mozio.com/"
    private const val CONNECTION_TIMEOUT = 30L
    private const val CALL_TIMEOUT = 40L
}
