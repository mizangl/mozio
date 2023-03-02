package com.mz.mozio.pizza_delivery.pizza_menu.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mz.mozio.pizza_delivery.data.api.ApiError
import com.mz.mozio.pizza_delivery.data.api.Success
import com.mz.mozio.pizza_delivery.data.repository.PizzaMenuRepository
import com.mz.mozio.pizza_delivery.pizza_menu.PizzaMenuState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class PizzaMenuViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: PizzaMenuRepository = mockk(relaxed = true)
    private lateinit var viewModel: PizzaMenuViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PizzaMenuViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test Succeed`() {
        coEvery { repository.getPizzaMenu() } returns Success(listOf(mockk(relaxed = true)))

        viewModel.loadPizzas()

        val state = viewModel.currentState
        coVerify { repository.getPizzaMenu() }

        Assert.assertNotNull(state)
        Assert.assertTrue(state is PizzaMenuState.Ready)
    }

    @Test
    fun `test Error`() {
        coEvery { repository.getPizzaMenu() } returns ApiError(404, "Error")

        viewModel.loadPizzas()

        val state = viewModel.currentState
        coVerify { repository.getPizzaMenu() }

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
