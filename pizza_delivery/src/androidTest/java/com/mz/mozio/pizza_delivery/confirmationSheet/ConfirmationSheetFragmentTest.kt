package com.mz.mozio.pizza_delivery.confirmationSheet

import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.confirmationSheet.view.ConfirmationSheetFragment
import com.mz.mozio.pizza_delivery.confirmationSheet.view.ConfirmationSheetFragmentArgs
import com.mz.mozio.pizza_delivery.core.Utils.decimalFormat
import com.mz.mozio.pizza_delivery.core.navigation.Coordinator
import com.mz.mozio.pizza_delivery.pizza_menu.model.OrderModel
import com.mz.mozio.pizza_delivery.pizza_menu.model.PizzaModel
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OnConfirmClicked
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OrderEvent
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.PizzaMenuModule
import com.mz.mozio.pizza_delivery.utils.clickOn
import com.mz.mozio.pizza_delivery.utils.isDisplayedInParent
import com.mz.mozio.pizza_delivery.utils.launchFragmentInHiltContainer
import com.mz.mozio.pizza_delivery.utils.viewInParentWith
import com.mz.mozio.pizza_delivery.utils.viewIsDisplayed
import com.mz.mozio.pizza_delivery.utils.viewIsNotDisplayed
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

@HiltAndroidTest
@UninstallModules(PizzaMenuModule::class)
class ConfirmationSheetFragmentTest {

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
    fun launchConfirmationSheet() {
        launch()

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
        launch(OrderModel(listOf(pizza)))

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

        launch(OrderModel(listOf(pizzaMozzarella, pizzaPepperoni)))

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

    @Test
    fun launchConfirmationSheetNavigateToOrderSuccess() {
        val pizzaMozzarella = PizzaModel("Mozzarella", 10.0)
        val pizzaPepperoni = PizzaModel("Pepperoni", 12.0)

        launch(OrderModel(listOf(pizzaMozzarella, pizzaPepperoni)))

        R.id.confirm_button.clickOn()

        verify(coordinator).onEvent(OnConfirmClicked)
    }

    private fun launch(order: OrderModel = OrderModel()) {
        val args = ConfirmationSheetFragmentArgs(order = order).toBundle()
        launchFragmentInHiltContainer<ConfirmationSheetFragment>(
            fragmentArgs = args,
        )
    }
}
