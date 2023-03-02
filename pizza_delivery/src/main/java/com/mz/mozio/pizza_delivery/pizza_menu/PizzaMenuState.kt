package com.mz.mozio.pizza_delivery.pizza_menu

import com.mz.mozio.pizza_delivery.pizza_menu.model.MenuErrorData
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel

sealed class PizzaMenuState {
    data class Ready(
        val data: List<PizzaModel>,
        val pizzas: List<PizzaModel> = emptyList()
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
        get() = (this as? Ready)?.pizzas ?: emptyList()

}
