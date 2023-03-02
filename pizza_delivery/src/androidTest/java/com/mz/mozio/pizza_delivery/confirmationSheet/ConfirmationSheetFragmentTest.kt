package com.mz.mozio.pizza_delivery.confirmationSheet

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.confirmationSheet.view.ConfirmationSheetFragment
import com.mz.mozio.pizza_delivery.confirmationSheet.view.ConfirmationSheetFragmentArgs
import com.mz.mozio.pizza_delivery.core.Utils.decimalFormat
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ConfirmationSheetFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val context: Context = ApplicationProvider.getApplicationContext()
    private val testNavController = TestNavHostController(context).apply {
        UiThreadStatement.runOnUiThread {
            setGraph(R.navigation.navigation_pizza)
            setCurrentDestination(R.id.dialog_pizza_confirmation)
        }
    }

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun launchConfirmationSheet() {
        launch(emptyList())

        assertDisplayed(R.id.close)
        assertDisplayed(R.id.title)
        assertNotDisplayed(R.id.item1)
        assertNotDisplayed(R.id.item2)
        assertDisplayed(R.id.total)
        assertDisplayed(R.id.confirm_button)
    }

    @Test
    fun launchConfirmationSheetOneItem() {
        val pizza = PizzaModel("Mozzarella", 10.0)
        launch(listOf(pizza))

        assertDisplayed(R.id.close)
        assertDisplayed(R.id.title)
        assertDisplayed(R.id.item1)
        assertNotDisplayed(R.id.item2)
        assertDisplayed(R.id.total)
        assertDisplayed(R.id.confirm_button)

        assertDisplayed(R.id.image)
        assertDisplayed(R.id.name, pizza.name)
        assertDisplayed(R.id.price, "$ ${decimalFormat.format(pizza.price)}")
    }

    @Test
    fun launchConfirmationSheetTwoItem() {
        val pizza = PizzaModel("Mozzarella", 10.0)
        launch(listOf(pizza, pizza))

        assertDisplayed(R.id.close)
        assertDisplayed(R.id.title)
        assertDisplayed(R.id.item1)
        assertDisplayed(R.id.item2)
        assertDisplayed(R.id.total)
        assertDisplayed(R.id.confirm_button)
    }

    private fun launch(orders: List<PizzaModel>) {
        val args = ConfirmationSheetFragmentArgs(orders = orders.toTypedArray()).toBundle()
        launchFragmentInHiltContainer<ConfirmationSheetFragment>(
            fragmentArgs = args,
        ) {
            lifecycleScope.launchWhenStarted {
                if (view != null) {
                    Navigation.setViewNavController(view!!, testNavController)
                }
            }
        }
    }
}
