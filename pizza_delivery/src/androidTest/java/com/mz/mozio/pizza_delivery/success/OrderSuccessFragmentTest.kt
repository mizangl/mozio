package com.mz.mozio.pizza_delivery.success

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.core.navigation.Coordinator
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OrderEvent
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.PizzaMenuModule
import com.mz.mozio.pizza_delivery.success.view.OrderSuccessFragment
import com.mz.mozio.pizza_delivery.utils.launchFragmentInHiltContainer
import com.mz.mozio.pizza_delivery.utils.viewIsDisplayed
import com.mz.mozio.pizza_delivery.utils.viewWith
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@HiltAndroidTest
@UninstallModules(PizzaMenuModule::class)
class OrderSuccessFragmentTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    @BindValue
    @JvmField
    val coordinator: Coordinator<OrderEvent> = mock()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragment_Error() {
        launch()

        R.id.close.viewIsDisplayed()
        R.id.message.viewWith(context.getString(R.string.order_success_description))
    }

    private fun launch() {
        launchFragmentInHiltContainer<OrderSuccessFragment>()
    }
}