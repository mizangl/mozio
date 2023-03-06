package com.mz.mozio.pizza_delivery.pizza_menu.navigation

import com.mz.mozio.pizza_delivery.core.navigation.Coordinator
import javax.inject.Inject


class OrderCoordinator @Inject constructor(
    private val pizzaMenuRouter: PizzaMenuRouter
) : Coordinator<OrderEvent> {

    override fun start() = Unit

    override fun onEvent(event: OrderEvent) {
        when (event) {
            is OnCheckoutOrder -> pizzaMenuRouter.toCheckoutOrder(event.data)
            is OnBackClicked -> pizzaMenuRouter.back()
            is OnConfirmClicked -> pizzaMenuRouter.toConfirmationScreen()
        }
    }
}
