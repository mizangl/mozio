package com.mz.mozio.pizza_delivery.sucess.viewmodel

import com.mz.mozio.pizza_delivery.core.navigation.Coordinator
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OnBackClicked
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OrderEvent
import com.mz.mozio.pizza_delivery.success.viewmodel.OrderSuccessViewModel
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.AfterClass
import org.junit.Test

class OrderSuccessViewModelTest {

    private val coordinator: Coordinator<OrderEvent> = mockk(relaxed = true)
    private val viewModel = OrderSuccessViewModel(coordinator)

    @Test
    fun `test navigate back`() {
        viewModel.navigateBack()

        verify { coordinator.onEvent(OnBackClicked) }
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun down() {
            unmockkAll()
        }
    }
}