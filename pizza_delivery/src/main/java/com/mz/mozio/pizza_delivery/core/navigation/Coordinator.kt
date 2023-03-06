package com.mz.mozio.pizza_delivery.core.navigation

interface Coordinator<Event> {

    fun start()

    fun onEvent(event: Event)
}