package com.mz.mozio.pizza_delivery.data

import com.mz.mozio.pizza_delivery.data.api.ApiError
import com.mz.mozio.pizza_delivery.data.api.ApiException
import com.mz.mozio.pizza_delivery.data.api.ApiResult
import com.mz.mozio.pizza_delivery.data.api.Success
import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import com.mz.mozio.pizza_delivery.data.repository.PizzaMenuRepository
import java.io.IOException
import javax.inject.Inject

class FakePizzaMenuRepository @Inject constructor() : PizzaMenuRepository {

    override suspend fun getPizzaMenu(): ApiResult<List<PizzaDTO>> {
        return FakeDataProvider.pizzaMenuList?.let {
            Success(it)
        } ?: run {
            if (FakeDataProvider.code != null && FakeDataProvider.message != null) {
                ApiError(FakeDataProvider.code!!, FakeDataProvider.message!!)
            } else {
                ApiException(IOException())
            }
        }
    }
}
