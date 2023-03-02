package com.mz.mozio.pizza_delivery.pizza_menu.model

import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO

fun PizzaDTO.toModel(): PizzaModel =
    PizzaModel(name ?: "", price ?: 0.0)
