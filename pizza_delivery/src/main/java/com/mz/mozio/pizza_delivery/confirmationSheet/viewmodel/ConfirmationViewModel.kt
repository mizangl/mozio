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

    fun setup(order: List<PizzaModel>) {
        setState(ConfirmationState.Ready(order))
    }
}