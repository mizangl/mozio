package com.mz.mozio.pizza_delivery.pizza_menu.model

import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import org.junit.Assert
import org.junit.Test

class PizzaModelTest {

    @Test
    fun `test constructor`() {
        val pizza = PizzaModel(
            name = "Name",
            price = 0.10
        )

        Assert.assertEquals("Name", pizza.name)
        Assert.assertEquals(0.10, pizza.price, 0.00)
        Assert.assertNotNull(pizza.imageModel.image)
        Assert.assertNotNull(pizza.imageModel.contentDescription)
        Assert.assertNotNull(pizza.formatCurrency)
    }

    @Test
    fun `test from DTO`() {
        val dto = PizzaDTO(
            name = "Name",
            price = 0.10
        )

        val pizza = dto.toModel()

        Assert.assertEquals("Name", pizza.name)
        Assert.assertEquals(0.10, pizza.price, 0.00)
        Assert.assertNotNull(pizza.imageModel.image)
        Assert.assertNotNull(pizza.imageModel.contentDescription)
        Assert.assertNotNull(pizza.formatCurrency)
    }
}