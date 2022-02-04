package com.chapo.bookstore.core.data.api.dtos.mappers

import com.chapo.bookstore.core.data.api.dtos.BookDto
import com.chapo.bookstore.core.domain.models.Book
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class BookMapper @Inject constructor() : ApiMapper<BookDto, Book> {

    override fun mapToDomain(dto: BookDto): Book {
        return try {
            Book(
                title = dto.title,
                subtitle = dto.subtitle,
                isbn = dto.isbn,
                price = dto.price,
                imageUrl = dto.imageUrl,
                bookUrl = dto.bookUrl
            )
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }

}