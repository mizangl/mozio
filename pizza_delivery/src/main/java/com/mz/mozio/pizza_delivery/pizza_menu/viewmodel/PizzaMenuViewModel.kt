package com.mz.mozio.pizza_delivery.pizza_menu.viewmodel

import androidx.lifecycle.viewModelScope
import com.mz.mozio.pizza_delivery.core.navigation.Coordinator
import com.mz.mozio.pizza_delivery.core.viewmodel.PizzaViewModel
import com.mz.mozio.pizza_delivery.data.api.Success
import com.mz.mozio.pizza_delivery.data.repository.PizzaMenuRepository
import com.mz.mozio.pizza_delivery.pizza_menu.PizzaMenuEvent
import com.mz.mozio.pizza_delivery.pizza_menu.PizzaMenuState
import com.mz.mozio.pizza_delivery.pizza_menu.model.OrderModel
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.pizza_menu.model.toModel
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OnCheckoutOrder
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OrderEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PizzaMenuViewModel @Inject constructor(
    private val repository: PizzaMenuRepository,
    private val coordinator: Coordinator<OrderEvent>,
) : PizzaViewModel<PizzaMenuEvent, PizzaMenuState>() {

    init {
        setState(PizzaMenuState.Loading)
        loadPizzas()
    }

    val onPizzaSelected: (PizzaModel) -> Unit = {
        viewModelScope.launch {
            if (currentState?.halfPizzaList?.isNotEmpty() == false) {
                currentState?.screenData?.let { data ->
                    setState(PizzaMenuState.Ready(data))
                }
            }
            coordinator.onEvent(OnCheckoutOrder(OrderModel(listOf(it))))
        }
    }

    val onHalfPizzaSelected: (PizzaModel) -> Unit = { pizza ->
        if (currentState?.halfPizzaList?.isNotEmpty() == true) {
            currentState?.let { state ->
                val order = OrderModel(state.halfPizzaList + pizza.copy(price = pizza.halfPrice))
                viewModelScope.launch {
                    coordinator.onEvent(OnCheckoutOrder(order))
                }
            }
        } else {
            currentState?.screenData?.let { data ->
                val order = OrderModel(listOf(pizza.copy(price = pizza.halfPrice)))
                setState(PizzaMenuState.Ready(data, order))
            }
        }
    }

    val forceCheckout: () -> Unit = {
        onReadyState {
            coordinator.onEvent(OnCheckoutOrder(order))
        }
    }

    fun loadPizzas() {
        viewModelScope.launch {
            when (val result = repository.getPizzaMenu()) {
                is Success -> {
                    val pizzaList = result.data.map { dto ->
                        dto.toModel()
                    }
                    setState(PizzaMenuState.Ready(pizzaList))
                }
                else -> setState(PizzaMenuState.Error)
            }
        }
    }

    fun onTryAgain() {
        loadPizzas()
    }

    fun clearSelection() {
        currentState?.screenData?.let { data ->
            setState(PizzaMenuState.Ready(data))
        }
    }

    private fun onReadyState(block: PizzaMenuState.Ready.() -> Unit) {
        (currentState as? PizzaMenuState.Ready)?.run(block)
    }
}
