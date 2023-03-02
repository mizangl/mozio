package com.mz.mozio.pizza_delivery.confirmationSheet

import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test

class ConfirmationStateTest {

    @Test
    fun `test Ready State`() {
        val state = ConfirmationState.Ready(emptyList())

        Assert.assertTrue(state.order.isEmpty())
        Assert.assertNull(state.firstPizza)
        Assert.assertNull(state.secondPizza)
        Assert.assertEquals(0.0, state.total, 0.0)
    }

    @Test
    fun `test Ready State with order size 1`() {
        val state = ConfirmationState.Ready(listOf(mockk(relaxed = true)))

        Assert.assertTrue(state.order.isNotEmpty())
        Assert.assertNotNull(state.firstPizza)
        Assert.assertNull(state.secondPizza)
    }

    @Test
    fun `test Ready State with order size 2`() {
        val state = ConfirmationState.Ready(listOf(mockk(relaxed = true), mockk(relaxed = true)))

        Assert.assertTrue(state.order.isNotEmpty())
        Assert.assertNotNull(state.firstPizza)
        Assert.assertNotNull(state.secondPizza)
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun down() {
            unmockkAll()
        }
    }
}
