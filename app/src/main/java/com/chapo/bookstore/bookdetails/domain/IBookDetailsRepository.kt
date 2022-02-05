package com.chapo.bookstore.bookdetails.domain

import com.chapo.bookstore.bookdetails.domain.models.BookDetails

interface IBookDetailsRepository {
    suspend fun getBookDetails(isbn: String): BookDetails
}