package com.chapo.bookstore.core.data.api.util

import com.chapo.bookstore.core.data.api.dtos.mappers.ApiMapper
import com.chapo.bookstore.core.domain.NetworkException
import com.chapo.bookstore.core.domain.Resource
import com.chapo.bookstore.core.domain.UnknownException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class ApiHelper @Inject constructor() {
    suspend fun <Dto : Any, Domain : Any> safeApiCall(
        mapper: ApiMapper<Dto, Domain>,
        call: suspend () -> Response<Dto>
    ): Resource<Domain> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Resource.Success(data = mapper.mapToDomain(response.body()!!))
            } else {
                Resource.Error(UnknownException())
            }
        } catch (exception: HttpException) {
            throw NetworkException(
                exception.message ?: "Code ${exception.code()}",
                exception.code()
            )
        }
    }

}
