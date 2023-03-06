package com.mz.mozio.pizza_delivery.pizza_menu.model

import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test

class OrderModelTest {

    @Test
    fun `test constructor`() {
        val model = OrderModel()

        Assert.assertTrue(model.pizza.isEmpty())
        Assert.assertEquals(0.0, model.total, 0.0)
    }

    @Test
    fun `test constructor full`() {
        val model = OrderModel(listOf(mockk(relaxed = true)))

        Assert.assertTrue(model.pizza.isNotEmpty())
        Assert.assertEquals(0.0, model.total, 0.0)
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun down() {
            unmockkAll()
        }
    }
}
