package com.chapo.bookstore.bookdetails.domain

import com.chapo.bookstore.bookdetails.domain.models.BookDetails
import com.chapo.bookstore.core.domain.Resource

interface IBookDetailsRepository {
    suspend fun getBookDetails(isbn: String): Resource<BookDetails>
}