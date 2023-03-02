package com.mz.mozio.pizza_delivery.confirmationSheet

import androidx.annotation.StringRes
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel

sealed class ConfirmationState {
    class Ready(val order: List<PizzaModel>) : ConfirmationState()

    val firstPizza: PizzaModel?
        get() = (this as? Ready)?.order?.firstOrNull()


    val secondPizza: PizzaModel?
        get() = (this as? Ready)?.let {
            if (it.order.size > 1) it.order.lastOrNull()
            else null
        }

    val total: Double
        get() = (this as? Ready)?.order?.let {
            if (it.size > 1) {
                it.map { pizza -> pizza.price }.sumOf { price -> price / 2 }
            } else {
                it.firstOrNull()?.price
            }
        } ?: 0.0

    @get:StringRes
    val formatCurrency: Int
        get() = R.string.default_pizza_total
}
