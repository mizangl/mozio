package com.mz.mozio.pizza_delivery.confirmationSheet.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mz.mozio.pizza_delivery.confirmationSheet.ConfirmationEvent
import com.mz.mozio.pizza_delivery.confirmationSheet.ConfirmationState
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ConfirmationViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val confirmationViewModel = ConfirmationViewModel()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test Ready state`() {
        confirmationViewModel.setup(listOf(mockk(relaxed = true)))
        val state = confirmationViewModel.currentState

        Assert.assertNotNull(state)
        Assert.assertTrue(state is ConfirmationState.Ready)
    }

    @Test
    fun `test dispatch event`() = runTest {
        confirmationViewModel.confirmAction.invoke()
        val event = confirmationViewModel.events.first()

        Assert.assertNotNull(event)
        Assert.assertTrue(event is ConfirmationEvent.OnConfirmClicked)
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun down() {
            unmockkAll()
        }
    }
}
