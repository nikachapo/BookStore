package com.chapo.bookstore.core.data.api

import com.chapo.bookstore.core.data.api.dtos.BookPageDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApi {

    @GET(ApiEndPoints.SEARCH_ENDPOINT + "/{query}/{page}")
    suspend fun getBookWithPage(
        @Path("page") page: String,
        @Path("query") query: String = "Algo"
    ): BookPageDto

    @GET(ApiEndPoints.SEARCH_ENDPOINT + "/{query}/{page}")
    suspend fun searchBook(
        @Path("page") page: String,
        @Path("query") query: String
    ): BookPageDto
}