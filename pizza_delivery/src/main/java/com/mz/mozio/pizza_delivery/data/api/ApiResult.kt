package com.mz.mozio.pizza_delivery.data.api

import retrofit2.HttpException
import retrofit2.Response

sealed class ApiResult<out T>
data class Success<T>(val data: T) : ApiResult<T>()
class ApiError(val code: Int, val message: String) : ApiResult<Nothing>()
class ApiException(val throwable: Throwable) : ApiResult<Nothing>()

suspend inline fun <T> safeCall(
    call: () -> Response<T>
): ApiResult<T> = try {
    val response = call()
    val body = response.body()
    if (response.isSuccessful && body != null) {
        Success(body)
    } else {
        ApiError(response.code(), response.message())
    }
} catch (error: HttpException) {
    ApiError(error.code(), error.message())
} catch (throwable: Throwable) {
    ApiException(throwable)
}