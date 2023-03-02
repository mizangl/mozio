package com.mz.mozio.pizza_delivery.data.repository

import com.mz.mozio.pizza_delivery.data.api.ApiError
import com.mz.mozio.pizza_delivery.data.api.ApiException
import com.mz.mozio.pizza_delivery.data.api.PizzaService
import com.mz.mozio.pizza_delivery.data.api.Success
import com.mz.mozio.pizza_delivery.data.entity.PizzaDTO
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.AfterClass
import org.junit.Assert
import org.junit.Test
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class PizzaMenuRepositoryTest {

    private val service: PizzaService = mockk(relaxed = true)
    private val repository: PizzaMenuRepository = PizzaMenuRepositoryImp(service)
    private val pizzaList = listOf<PizzaDTO>(mockk(relaxed = true))

    @Test
    fun `test when getPizzaMenu then return Success`() = runTest {

        val expected: Success<List<PizzaDTO>> = Success(pizzaList)
        coEvery { service.getPizza() } returns Response.success(pizzaList)

        val result = repository.getPizzaMenu()

        coVerify { service.getPizza() }
        Assert.assertTrue(result is Success)
        Assert.assertEquals(expected.data, (result as Success).data)
    }

    @Test
    fun `test when getPizzaMenu then return Error`() = runTest {
        val errorCode = 404
        val errorMessage = "Not found"
        val response = okhttp3.Response.Builder()
            .code(errorCode)
            .message(errorMessage)
            .protocol(Protocol.HTTP_1_1)
            .request(Request.Builder().url("http://localhost/").build())
            .build()

        coEvery { service.getPizza() } returns Response.error(
            errorMessage.toResponseBody(),
            response
        )

        val result = repository.getPizzaMenu()

        coVerify { service.getPizza() }
        Assert.assertTrue(result is ApiError)
        Assert.assertEquals(errorCode, (result as ApiError).code)
        Assert.assertEquals(errorMessage, result.message)
    }

    @Test
    fun `test when getPizzaMenu then return Exception`() = runTest {
        val errorMessage = "Error"
        val expectedException = IOException(errorMessage)
        coEvery { service.getPizza() } throws expectedException

        val result = repository.getPizzaMenu()

        coVerify { service.getPizza() }
        Assert.assertTrue(result is ApiException)
        Assert.assertEquals(expectedException, (result as ApiException).throwable)
        Assert.assertEquals(errorMessage, result.throwable.message)
    }

    companion object {
        @AfterClass
        @JvmStatic
        fun down() {
            unmockkAll()
        }
    }
}
