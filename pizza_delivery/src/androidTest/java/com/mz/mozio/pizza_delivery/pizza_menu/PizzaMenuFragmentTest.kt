package com.mz.mozio.pizza_delivery.pizza_menu

import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertContains
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.data.FakeDataProvider
import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import com.mz.mozio.pizza_delivery.pizza_menu.view.PizzaMenuFragment
import com.mz.mozio.pizza_delivery.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PizzaMenuFragmentTest {

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

        assertDisplayed(R.id.error_image)
        assertDisplayed(R.id.error_message)
        assertDisplayed(R.id.error_retry)

        assertContains(R.id.error_message, "Something went wrong!")
        assertContains(R.id.error_retry, "Try again")
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

        assertNotDisplayed(R.id.error_image)
        assertNotDisplayed(R.id.error_message)
        assertNotDisplayed(R.id.error_retry)

        assertRecyclerViewItemCount(
            R.id.pizza_menu,
            2
        )
    }

    private fun launch() {
        launchFragmentInHiltContainer<PizzaMenuFragment>()
    }
}