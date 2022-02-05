package com.chapo.bookstore.features.bookdetails.domain

import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails

interface IBookDetailsRepository {
    suspend fun getBookDetails(isbn: String): BookDetails
}