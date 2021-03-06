package com.chapo.bookstore.core.data.api.dtos.mappers

import com.chapo.bookstore.core.data.Mapper
import com.chapo.bookstore.core.data.api.dtos.BookPageDto
import com.chapo.bookstore.core.domain.models.BookPage
import javax.inject.Inject

class BookPageMapper @Inject constructor(
    private val bookMapper: BookMapper
) : Mapper<BookPageDto, BookPage> {

    override fun mapToDomain(from: BookPageDto): BookPage {
        return tryMap {
            BookPage(from.total.toInt(), from.page.toInt(), from.books.map {
                bookMapper.mapToDomain(it)
            })
        }
    }

}