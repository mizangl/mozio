package com.mz.mozio.pizza_delivery.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PizzaDTO(
    val name: String? = null,
    val price: Double? = null,
) : Parcelable
