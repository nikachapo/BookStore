package com.chapo.bookstore.features.bookdetails

import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails

internal class FakeBookDetailsRepo : IBookDetailsRepository {

    val book = BookDetails(
        "1", "Book1",
        "", "", "", "", "", "", "", "", ""
    )

    val books = mutableSetOf<BookDetails>()

    override suspend fun getBookDetails(isbn: String): BookDetails {
        return books.find { it.isbn == isbn }!!
    }

    override suspend fun addToFavourites(bookDetails: BookDetails) {
        books.add(bookDetails)
    }

    override suspend fun removeFromFavourites(isbn: String) {
        books.removeIf { it.isbn == isbn }
    }

}