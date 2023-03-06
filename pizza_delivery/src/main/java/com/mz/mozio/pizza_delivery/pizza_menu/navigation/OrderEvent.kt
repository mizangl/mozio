package com.mz.mozio.pizza_delivery.pizza_menu.navigation

import com.mz.mozio.pizza_delivery.pizza_menu.model.OrderModel

sealed interface OrderEvent

object OnConfirmClicked : OrderEvent

object OnBackClicked : OrderEvent

class OnCheckoutOrder(val data: OrderModel) : OrderEvent