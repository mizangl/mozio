package com.mz.mozio.pizza_delivery.pizza_menu

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.core.navigation.Coordinator
import com.mz.mozio.pizza_delivery.data.FakeDataProvider
import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OrderEvent
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.PizzaMenuModule
import com.mz.mozio.pizza_delivery.pizza_menu.view.PizzaMenuFragment
import com.mz.mozio.pizza_delivery.utils.click
import com.mz.mozio.pizza_delivery.utils.clickOn
import com.mz.mozio.pizza_delivery.utils.hasTextOn
import com.mz.mozio.pizza_delivery.utils.isDisplayedOn
import com.mz.mozio.pizza_delivery.utils.launchFragmentInHiltContainer
import com.mz.mozio.pizza_delivery.utils.viewIsDisplayed
import com.mz.mozio.pizza_delivery.utils.viewIsNotDisplayed
import com.mz.mozio.pizza_delivery.utils.viewWith
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

@HiltAndroidTest
@UninstallModules(PizzaMenuModule::class)
@RunWith(AndroidJUnit4::class)
class PizzaMenuFragmentTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    val coordinator: Coordinator<OrderEvent> = mock()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragment_Error() {
        FakeDataProvider.pizzaMenuList = null
        FakeDataProvider.code = 404
        FakeDataProvider.message = "Error"

        launch()

        R.id.error_image.viewIsDisplayed()
        R.id.error_message.viewIsDisplayed()
        R.id.error_retry.viewIsDisplayed()

        R.id.error_message.viewWith("Something went wrong!")
        R.id.error_retry.viewWith("Try again")
    }

    @Test
    fun testLaunchFragment_Success() {
        FakeDataProvider.pizzaMenuList = listOf(
            PizzaDTO(
                "Mozzarella", 10.0
            ), PizzaDTO(
                "Pepperoni", 12.0
            )
        )
        FakeDataProvider.code = null
        FakeDataProvider.message = null

        launch()

        R.id.error_image.viewIsNotDisplayed()
        R.id.error_message.viewIsNotDisplayed()
        R.id.error_retry.viewIsNotDisplayed()

        checkPizza("Mozzarella", 0)
        checkPizza("Pepperoni", 1)
    }

    @Test
    fun testLaunchFragment_Select_Half() {
        FakeDataProvider.pizzaMenuList = listOf(
            PizzaDTO(
                "Mozzarella", 10.0
            ), PizzaDTO(
                "Pepperoni", 12.0
            )
        )
        FakeDataProvider.code = null
        FakeDataProvider.message = null

        launch()

        R.id.checkout_option.viewIsNotDisplayed()
        R.id.half_pizza_title.viewIsNotDisplayed()
        R.id.pizza_title.viewIsNotDisplayed()
        R.id.pizza_price.viewIsNotDisplayed()
        R.id.show_checkout.viewIsNotDisplayed()

        R.id.action_half.click(R.id.pizza_menu, 0)

        R.id.checkout_option.viewIsDisplayed()
        R.id.half_pizza_title.viewIsDisplayed()
        R.id.pizza_title.viewIsDisplayed()
        R.id.pizza_price.viewIsDisplayed()
        R.id.show_checkout.viewIsDisplayed()
    }

    @Test
    fun testLaunchFragment_Navigate_Checkout() {
        FakeDataProvider.pizzaMenuList = listOf(
            PizzaDTO(
                "Mozzarella", 10.0
            ), PizzaDTO(
                "Pepperoni", 12.0
            )
        )
        FakeDataProvider.code = null
        FakeDataProvider.message = null

        launch()

        R.id.action_complete.click(R.id.pizza_menu, 0)

        verify(coordinator).onEvent(any())

    }

    @Test
    fun testLaunchFragment_HalfPizza_Navigate_Checkout() {
        FakeDataProvider.pizzaMenuList = listOf(
            PizzaDTO(
                "Mozzarella", 10.0
            ), PizzaDTO(
                "Pepperoni", 12.0
            )
        )
        FakeDataProvider.code = null
        FakeDataProvider.message = null

        launch()

        R.id.action_half.click(R.id.pizza_menu, 0)
        R.id.show_checkout.clickOn()

        verify(coordinator).onEvent(any())

    }

    private fun checkPizza(
        name: String,
        index: Int
    ) {

        R.id.image.isDisplayedOn(R.id.pizza_menu, index)
        R.id.name.hasTextOn(R.id.pizza_menu, index, name)
        R.id.action_complete.isDisplayedOn(R.id.pizza_menu, index)
        R.id.action_half.isDisplayedOn(R.id.pizza_menu, index)
    }

    private fun launch() {
        launchFragmentInHiltContainer<PizzaMenuFragment>()
    }

    private fun loadPizzasFromJson(): List<PizzaDTO> {
        val raw =
            context.resources.openRawResource(R.raw.pizza_list)
                .bufferedReader().use { it.readText() }
        return Gson().fromJson(raw, Array<PizzaDTO>::class.java).toList()
    }
}