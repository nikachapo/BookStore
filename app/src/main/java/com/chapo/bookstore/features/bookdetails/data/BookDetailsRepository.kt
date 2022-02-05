package com.chapo.bookstore.features.bookdetails.data

import com.chapo.bookstore.features.bookdetails.data.api.BookDetailsApi
import com.chapo.bookstore.features.bookdetails.data.api.dtos.mappers.BookDetailsMapper
import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import com.chapo.bookstore.core.data.api.util.ApiHelper
import javax.inject.Inject

class BookDetailsRepository @Inject constructor(
    private val mapper: BookDetailsMapper,
    private val bookDetailsApi: BookDetailsApi,
    private val apiHelper: ApiHelper
) : IBookDetailsRepository {

    override suspend fun getBookDetails(isbn: String): BookDetails {
        return apiHelper.safeApiCall(mapper) {
            bookDetailsApi.getBookDetails(isbn)
        }
    }
}