package com.mz.mozio.pizza_delivery.data.api

import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import retrofit2.Response
import retrofit2.http.GET

interface PizzaService {

    @GET(MOBILE_GET_MENU_PIZZA)
    suspend fun getPizza(): Response<List<PizzaDTO>>

    private companion object {
        const val MOBILE_GET_MENU_PIZZA = "mobile/tests/pizzas.json"
    }
}
