package com.mz.mozio.pizza_delivery.pizza_menu

import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import org.junit.Assert
import org.junit.Test

class PizzaMenuEventTest {

    @Test
    fun `Test OnLoadMenu event`() {
        val event = OnLoadMenu
        Assert.assertNotNull(event)
    }

    @Test
    fun `Test OnSelectedPizza event`() {
        val model = PizzaModel("Pizza", 0.10)
        val event = OnSelectedPizza(listOf(model))

        Assert.assertNotNull(event)
        Assert.assertEquals(model, event.data.first())
    }
}
