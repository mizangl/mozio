package com.mz.mozio.pizza_delivery.pizza_menu.navigation

import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.AfterClass
import org.junit.Test

class OrderCoordinatorTest {

    private val mockPizzaMenuRouter: PizzaMenuRouter = mockk(relaxed = true)

    private val orderCoordinator = OrderCoordinator(mockPizzaMenuRouter)

    @Test
    fun `test onEvent OnCheckoutOrder`() {
        orderCoordinator.onEvent(OnCheckoutOrder(mockk(relaxed = true)))

        verify { mockPizzaMenuRouter.toCheckoutOrder(any()) }
    }

    @Test
    fun `test onEvent OnBackClicked`() {
        orderCoordinator.onEvent(OnBackClicked)

        verify { mockPizzaMenuRouter.back() }
    }

    @Test
    fun `test onEvent OnConfirmClicked`() {
        orderCoordinator.onEvent(OnConfirmClicked)

        verify { mockPizzaMenuRouter.toConfirmationScreen() }
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun down() {
            unmockkAll()
        }
    }
}