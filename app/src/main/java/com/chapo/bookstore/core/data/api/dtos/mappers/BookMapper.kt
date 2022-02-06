package com.chapo.bookstore.core.data.api.dtos.mappers

import com.chapo.bookstore.core.data.Mapper
import com.chapo.bookstore.core.data.api.dtos.BookDto
import com.chapo.bookstore.core.domain.models.Book
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class BookMapper @Inject constructor() : Mapper<BookDto, Book> {

    override fun mapToDomain(from: BookDto): Book {
        return try {
            Book(
                title = from.title,
                subtitle = from.subtitle,
                isbn = from.isbn,
                price = from.price,
                imageUrl = from.imageUrl,
                bookUrl = from.bookUrl
            )
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }

}