package com.mz.mozio.pizza_delivery.data.repository

import com.mz.mozio.pizza_delivery.data.api.ApiResult
import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO

interface PizzaMenuRepository {

    suspend fun getPizzaMenu(): ApiResult<List<PizzaDTO>>
}
