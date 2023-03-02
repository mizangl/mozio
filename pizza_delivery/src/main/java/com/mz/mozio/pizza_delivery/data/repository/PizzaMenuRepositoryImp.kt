package com.mz.mozio.pizza_delivery.data.repository

import com.mz.mozio.pizza_delivery.data.api.ApiResult
import com.mz.mozio.pizza_delivery.data.api.PizzaService
import com.mz.mozio.pizza_delivery.data.api.safeCall
import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import javax.inject.Inject

class PizzaMenuRepositoryImp @Inject constructor(
    private val service: PizzaService
) : PizzaMenuRepository {

    override suspend fun getPizzaMenu(): ApiResult<List<PizzaDTO>> {
        return safeCall {
            service.getPizza()
        }
    }
}
