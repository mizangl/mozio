package com.mz.mozio.pizza_delivery.confirmationSheet

import androidx.annotation.StringRes
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.pizza_menu.model.OrderModel
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel

sealed class ConfirmationState {
    class Ready(val order: OrderModel) : ConfirmationState()

    val firstPizza: PizzaModel?
        get() = (this as? Ready)?.order?.pizza?.firstOrNull()


    val secondPizza: PizzaModel?
        get() = (this as? Ready)?.let {
            if (it.order.pizza.size > 1) it.order.pizza.lastOrNull()
            else null
        }

    val total: Double
        get() = (this as? Ready)?.order?.total ?: 0.0

    @get:StringRes
    val formatCurrency: Int
        get() = R.string.default_pizza_total
}
