package com.chapo.bookstore.bookdetails.data

import com.chapo.bookstore.bookdetails.data.api.BookDetailsApi
import com.chapo.bookstore.bookdetails.data.api.dtos.mappers.BookDetailsMapper
import com.chapo.bookstore.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.bookdetails.domain.models.BookDetails
import com.chapo.bookstore.core.data.api.util.ApiHelper
import com.chapo.bookstore.core.domain.Resource
import javax.inject.Inject

class BookDetailsRepository @Inject constructor(
    private val mapper: BookDetailsMapper,
    private val bookDetailsApi: BookDetailsApi,
    private val apiHelper: ApiHelper
) : IBookDetailsRepository {
    override suspend fun getBookDetails(isbn: String): Resource<BookDetails> {
        return apiHelper.safeApiCall(mapper) {
            bookDetailsApi.getBookDetails(isbn)
        }
    }
}