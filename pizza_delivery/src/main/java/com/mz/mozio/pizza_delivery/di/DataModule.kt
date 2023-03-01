package com.mz.mozio.pizza_delivery.di

import com.mz.mozio.pizza_delivery.data.api.PizzaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        OkHttpClient.Builder().build()


    private const val API_URL = "https://static.mozio.com/"
}
