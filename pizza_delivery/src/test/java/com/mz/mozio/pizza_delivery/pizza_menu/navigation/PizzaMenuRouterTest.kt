package com.mz.mozio.pizza_delivery.pizza_menu.navigation

import android.os.Build
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mz.mozio.pizza_delivery.R
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.AfterClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class PizzaMenuRouterTest {

    private val mockNavController: NavController = mockk(relaxed = true)
    private val pizzaMenuRouter = PizzaMenuRouter(mockNavController)

    @Test
    fun `test route to Dialog Checkout`() {
        pizzaMenuRouter.toCheckoutOrder(mockk(relaxed = true))

        verify { mockNavController.navigate(R.id.dialog_pizza_confirmation, any()) }
    }

    @Test
    fun `test route to  Confirmation screen`() {
        pizzaMenuRouter.toConfirmationScreen()

        verify { mockNavController.navigate(R.id.pizza_succeed) }
    }

    @Test
    fun `test route back`() {
        pizzaMenuRouter.back()

        verify { mockNavController.popBackStack() }
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun down() {
            unmockkAll()
        }
    }
}