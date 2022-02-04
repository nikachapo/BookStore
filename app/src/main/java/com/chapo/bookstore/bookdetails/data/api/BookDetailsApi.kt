package com.chapo.bookstore.bookdetails.data.api

import com.chapo.bookstore.bookdetails.data.api.dtos.BookDetailsDto
import com.chapo.bookstore.core.data.api.ApiEndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookDetailsApi {

    @GET(ApiEndPoints.DETAILS + "/{isbn}")
    suspend fun getBookDetails(@Path("isbn") isbn: String): Response<BookDetailsDto>
}