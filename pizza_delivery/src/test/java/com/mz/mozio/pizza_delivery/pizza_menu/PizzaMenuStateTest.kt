package com.mz.mozio.pizza_delivery.pizza_menu

import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import org.junit.Assert
import org.junit.Test

class PizzaMenuStateTest {

    @Test
    fun `test Ready state`() {
        val model = PizzaModel("Pizza", 0.10)
        val state = PizzaMenuState.Ready(listOf(model))

        Assert.assertTrue(state.isReady)
        Assert.assertFalse(state.isLoading)
        Assert.assertFalse(state.isError)
        Assert.assertNull(state.errorMessage)
        Assert.assertTrue(state.data.isNotEmpty())
        Assert.assertEquals(model, state.data.first())
    }

    @Test
    fun `test Error state`() {
        val state = PizzaMenuState.Error

        Assert.assertTrue(state.isError)
        Assert.assertFalse(state.isReady)
        Assert.assertFalse(state.isLoading)
        Assert.assertNotNull(state.errorMessage)
        Assert.assertTrue(state.screenData.isEmpty())
    }

    @Test
    fun `test Loading state`() {
        val state = PizzaMenuState.Loading

        Assert.assertTrue(state.isLoading)
        Assert.assertFalse(state.isReady)
        Assert.assertFalse(state.isError)
        Assert.assertNull(state.errorMessage)
        Assert.assertTrue(state.screenData.isEmpty())

    }
}
