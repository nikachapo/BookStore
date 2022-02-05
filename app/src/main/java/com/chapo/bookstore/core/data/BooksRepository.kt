package com.chapo.bookstore.core.data

import com.chapo.bookstore.core.data.api.BooksApi
import com.chapo.bookstore.core.data.api.dtos.mappers.BookPageMapper
import com.chapo.bookstore.core.data.api.util.ApiHelper
import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val booksApi: BooksApi,
    private val bookPageMapper: BookPageMapper
) : IBooksRepository {

    override suspend fun getBooksWithPage(page: Int): BookPage {
        return apiHelper.safeApiCall(bookPageMapper) {
            booksApi.getBookWithPage(page.toString())
        }
    }

    override suspend fun searchBook(page: Int, query: String): BookPage {
        return apiHelper.safeApiCall(bookPageMapper) {
            booksApi.getBookWithPage(page = page.toString(), query = query)
        }
    }

}