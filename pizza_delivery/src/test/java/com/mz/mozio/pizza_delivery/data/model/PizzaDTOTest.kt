package com.mz.mozio.pizza_delivery.data.model

import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import org.junit.Assert
import org.junit.Test

class PizzaDTOTest {

    @Test
    fun `test constructor empty`() {
        val model = PizzaDTO()

        Assert.assertNull(model.name)
        Assert.assertNull(model.price)
    }

    @Test
    fun `test constructor name`() {
        val model = PizzaDTO("Mozzarella")

        Assert.assertEquals("Mozzarella", model.name)
        Assert.assertNull(model.price)
    }

    @Test
    fun `test constructor name and price`() {
        val model = PizzaDTO("Mozzarella", 12.9)

        Assert.assertEquals("Mozzarella", model.name)
        Assert.assertEquals(12.9, model.price)
    }
}