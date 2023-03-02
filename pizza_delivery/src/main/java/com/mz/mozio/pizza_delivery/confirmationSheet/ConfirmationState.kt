package com.mz.mozio.pizza_delivery.confirmationSheet

import androidx.annotation.StringRes
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel

sealed class ConfirmationState {
    class Ready(val pizza: PizzaModel) : ConfirmationState()
    class ReadyTwoHalf(
        val pizza1: PizzaModel,
        val pizza2: PizzaModel
    ) : ConfirmationState()

    val firstPizza: PizzaModel
        get() = when (this) {
            is Ready -> this.pizza
            is ReadyTwoHalf -> this.pizza1
        }

    val secondPizza: PizzaModel?
        get() = if (this is ReadyTwoHalf) pizza2 else null

    val total: Double
        get() = when (this) {
            is Ready -> pizza.price
            is ReadyTwoHalf -> pizza1.price / 2 + pizza2.price / 2
        }

    @get:StringRes
    val formatCurrency: Int
        get() = R.string.default_pizza_total
}
