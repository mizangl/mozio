package com.mz.mozio.pizza_delivery.pizza_menu

import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel

sealed class PizzaMenuEvent
object OnLoadMenu : PizzaMenuEvent()
class OnSelectedPizza(val data: PizzaModel) : PizzaMenuEvent()
