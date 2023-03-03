package com.mz.mozio.pizza_delivery.pizza_menu

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.google.gson.Gson
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.data.FakeDataProvider
import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import com.mz.mozio.pizza_delivery.pizza_menu.view.PizzaMenuFragment
import com.mz.mozio.pizza_delivery.utils.hasTextOn
import com.mz.mozio.pizza_delivery.utils.isDisplayedOn
import com.mz.mozio.pizza_delivery.utils.launchFragmentInHiltContainer
import com.mz.mozio.pizza_delivery.utils.viewIsDisplayed
import com.mz.mozio.pizza_delivery.utils.viewIsNotDisplayed
import com.mz.mozio.pizza_delivery.utils.viewWith
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PizzaMenuFragmentTest {

    private val context: Context = ApplicationProvider.getApplicationContext()
    private val testNavController = TestNavHostController(context).apply {
        UiThreadStatement.runOnUiThread {
            setGraph(R.navigation.navigation_pizza)
            setCurrentDestination(R.id.dialog_pizza_confirmation)
        }
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

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
        launchFragmentInHiltContainer<PizzaMenuFragment>() {
            lifecycleScope.launchWhenStarted {
                if (view != null) {
                    Navigation.setViewNavController(view!!, testNavController)
                }
            }
        }
    }

    private fun loadPizzasFromJson(): List<PizzaDTO> {
        val raw =
            context.resources.openRawResource(R.raw.pizza_list)
                .bufferedReader().use { it.readText() }
        return Gson().fromJson(raw, Array<PizzaDTO>::class.java).toList()
    }
}