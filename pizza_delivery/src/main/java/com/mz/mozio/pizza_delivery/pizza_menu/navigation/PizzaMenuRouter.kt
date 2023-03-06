package com.mz.mozio.pizza_delivery.pizza_menu.navigation

import androidx.navigation.NavController
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.confirmationSheet.view.ConfirmationSheetFragmentArgs
import com.mz.mozio.pizza_delivery.core.navigation.Router
import com.mz.mozio.pizza_delivery.pizza_menu.model.OrderModel
import javax.inject.Inject

class PizzaMenuRouter @Inject constructor(
    private val navController: NavController
) : Router {

    fun start() = Unit

    fun toCheckoutOrder(order: OrderModel) {
        val args = ConfirmationSheetFragmentArgs(
            order = order
        ).toBundle()
        navController.navigate(R.id.dialog_pizza_confirmation, args)
    }

    fun toConfirmationScreen() {
        navController.navigate(R.id.pizza_succeed)
    }

    fun back() {
        navController.popBackStack()
    }
}