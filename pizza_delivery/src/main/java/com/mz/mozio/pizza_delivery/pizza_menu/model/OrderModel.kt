package com.mz.mozio.pizza_delivery.pizza_menu.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderModel(
    val pizza: List<PizzaModel> = mutableListOf(),
) : Parcelable {

    val total: Double
        get() = pizza.sumOf { it.price }
}
