package com.mz.mozio.pizza_delivery.pizza_menu.viewmodel

import androidx.lifecycle.viewModelScope
import com.mz.mozio.pizza_delivery.core.viewmodel.PizzaViewModel
import com.mz.mozio.pizza_delivery.data.api.Success
import com.mz.mozio.pizza_delivery.data.repository.PizzaMenuRepository
import com.mz.mozio.pizza_delivery.pizza_menu.OnSelectedPizza
import com.mz.mozio.pizza_delivery.pizza_menu.OnSelectedTwoHalf
import com.mz.mozio.pizza_delivery.pizza_menu.PizzaMenuEvent
import com.mz.mozio.pizza_delivery.pizza_menu.PizzaMenuState
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.pizza_menu.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PizzaMenuViewModel @Inject constructor(
    private val repository: PizzaMenuRepository,
) : PizzaViewModel<PizzaMenuEvent, PizzaMenuState>() {

    val onPizzaSelected: (PizzaModel) -> Unit = {
        viewModelScope.launch {
            if (currentState?.halfPizzaList?.isNotEmpty() == false) {
                currentState?.screenData?.let { data ->
                    setState(PizzaMenuState.Ready(data))
                }
            }
            postEvent(OnSelectedPizza(it))
        }
    }

    val onHalfPizzaSelected: (PizzaModel) -> Unit = { pizza ->
        if (currentState?.halfPizzaList?.isNotEmpty() == true) {
            currentState?.halfPizzaList?.let { list ->
                val order = listOf(list.first(), pizza)
                viewModelScope.launch { postEvent(OnSelectedTwoHalf(order)) }
            }
        } else {
            currentState?.screenData?.let { data ->
                setState(PizzaMenuState.Ready(data, listOf(pizza)))
            }
        }
    }

    fun loadPizzas() {
        setState(PizzaMenuState.Loading)
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
}
