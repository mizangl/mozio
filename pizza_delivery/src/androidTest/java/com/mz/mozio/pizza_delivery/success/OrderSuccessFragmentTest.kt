package com.mz.mozio.pizza_delivery.success

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.success.view.OrderSuccessFragment
import com.mz.mozio.pizza_delivery.utils.launchFragmentInHiltContainer
import com.mz.mozio.pizza_delivery.utils.viewIsDisplayed
import com.mz.mozio.pizza_delivery.utils.viewWith
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class OrderSuccessFragmentTest {

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
        launch()

        R.id.close.viewIsDisplayed()
        R.id.message.viewWith(context.getString(R.string.order_success_description))
    }

    private fun launch() {
        launchFragmentInHiltContainer<OrderSuccessFragment> {
            lifecycleScope.launchWhenStarted {
                if (view != null) {
                    Navigation.setViewNavController(view!!, testNavController)
                }
            }
        }
    }
}