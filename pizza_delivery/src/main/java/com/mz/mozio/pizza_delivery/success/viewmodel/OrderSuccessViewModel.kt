package com.mz.mozio.pizza_delivery.success.viewmodel

import androidx.lifecycle.ViewModel
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OnBackClicked
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OrderCoordinator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderSuccessViewModel @Inject constructor(
    private val coordinator: OrderCoordinator
) : ViewModel() {

    fun navigateBack() {
        coordinator.onEvent(OnBackClicked)
    }
}