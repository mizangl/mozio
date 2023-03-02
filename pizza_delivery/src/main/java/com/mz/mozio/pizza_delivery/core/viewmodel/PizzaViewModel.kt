package com.mz.mozio.pizza_delivery.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class PizzaViewModel<Event, State> : ViewModel() {

    private val internalState = MutableLiveData<State>()
    private val internalEvents = MutableSharedFlow<Event>()

    val state: LiveData<State>
        get() = internalState

    val currentState: State?
        get() = internalState.value

    val events: SharedFlow<Event>
        get() = internalEvents.asSharedFlow()

    protected fun setState(newState: State) {
        internalState.value = (newState)
    }

    protected suspend fun postEvent(event: Event) {
        internalEvents.emit(event)
    }
}
