package com.chapo.bookstore.core.data.api.util

import com.chapo.bookstore.core.data.Mapper
import com.chapo.bookstore.core.data.MappingException
import com.chapo.bookstore.core.domain.NetworkException
import com.chapo.bookstore.core.domain.UnknownException
import retrofit2.HttpException
import javax.inject.Inject

class ApiHelper @Inject constructor() {

    suspend fun <Dto : Any, Domain : Any> safeApiCall(
        mapper: Mapper<Dto, Domain>,
        call: suspend () -> Dto
    ): Domain {
        return try {
            mapper.mapToDomain(call())
        } catch (exception: HttpException) {
            throw NetworkException(
                exception.message ?: "Code ${exception.code()}",
                exception.code()
            )
        } catch (e: MappingException) {
            throw UnknownException()
        }
    }

}
