package com.mz.mozio.pizza_delivery.pizza_menu

import androidx.annotation.StringRes
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.pizza_menu.model.MenuErrorData
import com.mz.mozio.pizza_delivery.pizza_menu.model.OrderModel
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel

sealed class PizzaMenuState {
    data class Ready(
        val data: List<PizzaModel>,
        val order: OrderModel = OrderModel(emptyList())
    ) : PizzaMenuState()

    object Loading : PizzaMenuState()
    object Error : PizzaMenuState()

    val isLoading: Boolean
        get() = this is Loading

    val isReady: Boolean
        get() = this is Ready

    val screenData: List<PizzaModel>
        get() = (this as? Ready)?.data ?: emptyList()

    val isError: Boolean
        get() = this is Error

    val errorMessage: MenuErrorData?
        get() = if (isError) MenuErrorData() else null

    val halfPizzaList: List<PizzaModel>
        get() = (this as? Ready)?.order?.pizza ?: emptyList()

    val hasHalfSelected: Boolean
        get() = (this as? Ready)?.order?.pizza?.isNotEmpty() ?: false

    val selectedHalfTitle: String?
        get() = if (halfPizzaList.isNotEmpty()) halfPizzaList.firstOrNull()?.name else null

    val selectedHalfPrice: Double
        get() = (this as? Ready)?.order?.total ?: 0.0

    @get:StringRes
    val currency: Int
        get() = R.string.default_pizza_currency
}
