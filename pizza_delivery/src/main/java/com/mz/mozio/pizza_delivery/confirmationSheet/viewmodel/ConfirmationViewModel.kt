package com.mz.mozio.pizza_delivery.confirmationSheet.viewmodel

import androidx.lifecycle.viewModelScope
import com.mz.mozio.pizza_delivery.confirmationSheet.ConfirmationEvent
import com.mz.mozio.pizza_delivery.confirmationSheet.ConfirmationState
import com.mz.mozio.pizza_delivery.core.viewmodel.PizzaViewModel
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor() :
    PizzaViewModel<ConfirmationEvent, ConfirmationState>() {

    val confirmAction: () -> Unit = {
        viewModelScope.launch {
            postEvent(ConfirmationEvent.OnConfirmClicked)
        }
    }

    fun setup(pizza1: PizzaModel?, pizza2: PizzaModel?) {
        if (pizza1 != null && pizza2 != null) {
            setState(ConfirmationState.ReadyTwoHalf(pizza1, pizza2))
        } else if (pizza1 != null) {
            setState(ConfirmationState.Ready(pizza1))
        }
    }
}