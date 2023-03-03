package com.mz.mozio.pizza_delivery.confirmationSheet

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.confirmationSheet.view.ConfirmationSheetFragment
import com.mz.mozio.pizza_delivery.confirmationSheet.view.ConfirmationSheetFragmentArgs
import com.mz.mozio.pizza_delivery.core.Utils.decimalFormat
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.utils.isDisplayedInParent
import com.mz.mozio.pizza_delivery.utils.launchFragmentInHiltContainer
import com.mz.mozio.pizza_delivery.utils.viewInParentWith
import com.mz.mozio.pizza_delivery.utils.viewIsDisplayed
import com.mz.mozio.pizza_delivery.utils.viewIsNotDisplayed
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

        R.id.close.viewIsDisplayed()
        R.id.title.viewIsDisplayed()
        R.id.item1.viewIsNotDisplayed()
        R.id.item2.viewIsNotDisplayed()
        R.id.total.viewIsDisplayed()
        R.id.confirm_button.viewIsDisplayed()
    }

    @Test
    fun launchConfirmationSheetOneItem() {
        val pizza = PizzaModel("Mozzarella", 10.0)
        launch(listOf(pizza))

        R.id.close.viewIsDisplayed()
        R.id.title.viewIsDisplayed()
        R.id.item1.viewIsDisplayed()
        R.id.item2.viewIsNotDisplayed()
        R.id.total.viewIsDisplayed()
        R.id.confirm_button.viewIsDisplayed()

        R.id.image.isDisplayedInParent(R.id.item1)
        R.id.name.viewInParentWith(R.id.item1, pizza.name)
        R.id.price.viewInParentWith(R.id.item1, "$ ${decimalFormat.format(pizza.price)}")
    }

    @Test
    fun launchConfirmationSheetTwoItem() {
        val pizzaMozzarella = PizzaModel("Mozzarella", 10.0)
        val pizzaPepperoni = PizzaModel("Pepperoni", 12.0)

        launch(listOf(pizzaMozzarella, pizzaPepperoni))

        R.id.close.viewIsDisplayed()
        R.id.title.viewIsDisplayed()
        R.id.item1.viewIsDisplayed()
        R.id.item2.viewIsDisplayed()
        R.id.total.viewIsDisplayed()
        R.id.confirm_button.viewIsDisplayed()

        R.id.image.isDisplayedInParent(R.id.item1)
        R.id.name.viewInParentWith(R.id.item1, pizzaMozzarella.name)
        R.id.price.viewInParentWith(R.id.item1, "$ ${decimalFormat.format(pizzaMozzarella.price)}")

        R.id.image.isDisplayedInParent(R.id.item2)
        R.id.name.viewInParentWith(R.id.item2, pizzaPepperoni.name)
        R.id.price.viewInParentWith(R.id.item2, "$ ${decimalFormat.format(pizzaPepperoni.price)}")
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
