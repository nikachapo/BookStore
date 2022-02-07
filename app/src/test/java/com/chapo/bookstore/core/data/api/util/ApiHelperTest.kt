package com.chapo.bookstore.core.data.api.util

import com.chapo.bookstore.core.data.Mapper
import com.chapo.bookstore.core.domain.NetworkException
import com.chapo.bookstore.core.domain.UnknownException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class ApiHelperTest {

    private val apiHelper = ApiHelper()

    @Test(expected = UnknownException::class)
    fun `mapper exception throws UnknownException`() = runTest {
        apiHelper.safeApiCall(object : Mapper<String, Int> {
            override fun mapToDomain(from: String): Int {
                tryMap {
                    throw NumberFormatException()
                }
            }
        }, suspend {
            "test"
        })
    }

    @Test(expected = NetworkException::class)
    fun `http exception throws UnknownException`() = runTest {
        apiHelper.safeApiCall(object : Mapper<String, Int> {
            override fun mapToDomain(from: String): Int {
                return from.toInt()
            }
        }, suspend {
            throw HttpException(
                Response.error<ResponseBody>(
                    500,
                    ResponseBody.create("plain/text".toMediaTypeOrNull(), "")
                )
            )
        })
    }

    @Test
    fun `mapper is used`() = runTest {
        val result = apiHelper.safeApiCall(object : Mapper<String, Int> {
            override fun mapToDomain(from: String): Int {
                return from.toInt()
            }
        }, suspend {
            "1"
        })
        assertEquals(1, result)
    }

}