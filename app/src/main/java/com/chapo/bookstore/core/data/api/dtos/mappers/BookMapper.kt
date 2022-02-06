package com.chapo.bookstore.core.data.api.dtos.mappers

import com.chapo.bookstore.core.data.Mapper
import com.chapo.bookstore.core.data.api.dtos.BookDto
import com.chapo.bookstore.core.domain.models.Book
import javax.inject.Inject

class BookMapper @Inject constructor() : Mapper<BookDto, Book> {

    override fun mapToDomain(from: BookDto): Book {
        return tryMap {
            Book(
                title = from.title,
                subtitle = from.subtitle,
                isbn = from.isbn,
                price = from.price,
                imageUrl = from.imageUrl,
                bookUrl = from.bookUrl
            )
        }
    }

}