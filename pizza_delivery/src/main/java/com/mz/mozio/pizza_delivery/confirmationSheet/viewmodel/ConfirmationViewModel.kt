package com.mz.mozio.pizza_delivery.confirmationSheet.viewmodel

import androidx.lifecycle.viewModelScope
import com.mz.mozio.pizza_delivery.confirmationSheet.ConfirmationEvent
import com.mz.mozio.pizza_delivery.confirmationSheet.ConfirmationState
import com.mz.mozio.pizza_delivery.core.navigation.Coordinator
import com.mz.mozio.pizza_delivery.core.viewmodel.PizzaViewModel
import com.mz.mozio.pizza_delivery.pizza_menu.model.OrderModel
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OnBackClicked
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OnConfirmClicked
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OrderEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor(
    private val coordinator: Coordinator<OrderEvent>,
) :
    PizzaViewModel<ConfirmationEvent, ConfirmationState>() {

    val confirmAction: () -> Unit = {
        viewModelScope.launch {
            postEvent(ConfirmationEvent.OnConfirmClicked)
            coordinator.onEvent(OnConfirmClicked)
        }
    }

    fun setup(order: OrderModel) {
        setState(ConfirmationState.Ready(order))
    }

    fun navigateBack() {
        coordinator.onEvent(OnBackClicked)
    }
}
