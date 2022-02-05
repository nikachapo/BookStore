package com.chapo.bookstore.features.bookdetails.data.api

import com.chapo.bookstore.features.bookdetails.data.api.dtos.BookDetailsDto
import com.chapo.bookstore.core.data.api.ApiEndPoints
import retrofit2.http.GET
import retrofit2.http.Path

interface BookDetailsApi {

    @GET(ApiEndPoints.DETAILS + "/{isbn}")
    suspend fun getBookDetails(@Path("isbn") isbn: String): BookDetailsDto
}