package com.mz.mozio.pizza_delivery.pizza_menu.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mz.mozio.pizza_delivery.data.api.ApiError
import com.mz.mozio.pizza_delivery.data.api.Success
import com.mz.mozio.pizza_delivery.data.repository.PizzaMenuRepository
import com.mz.mozio.pizza_delivery.pizza_menu.PizzaMenuState
import com.mz.mozio.pizza_delivery.pizza_menu.navigation.OrderCoordinator
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PizzaMenuViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository: PizzaMenuRepository = mockk(relaxed = true)
    private val mockOrderCoordinator: OrderCoordinator = mockk(relaxed = true)
    private lateinit var viewModel: PizzaMenuViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PizzaMenuViewModel(
            mockRepository,
            mockOrderCoordinator
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test Succeed`() {
        coEvery { mockRepository.getPizzaMenu() } returns Success(listOf(mockk(relaxed = true)))

        viewModel.loadPizzas()

        val state = viewModel.currentState
        coVerify { mockRepository.getPizzaMenu() }

        Assert.assertNotNull(state)
        Assert.assertTrue(state is PizzaMenuState.Ready)
    }

    @Test
    fun `test Error`() {
        coEvery { mockRepository.getPizzaMenu() } returns ApiError(404, "Error")

        viewModel.loadPizzas()

        val state = viewModel.currentState
        coVerify { mockRepository.getPizzaMenu() }

        Assert.assertNotNull(state)
        Assert.assertTrue(state is PizzaMenuState.Error)
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun down() {
            unmockkAll()
        }
    }
}
