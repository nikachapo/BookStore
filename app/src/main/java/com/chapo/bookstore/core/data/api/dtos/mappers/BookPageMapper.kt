package com.chapo.bookstore.core.data.api.dtos.mappers

import com.chapo.bookstore.core.data.api.dtos.BookPageDto
import com.chapo.bookstore.core.domain.models.BookPage
import java.lang.Exception
import java.lang.IllegalArgumentException
import javax.inject.Inject

class BookPageMapper @Inject constructor(
    private val bookMapper: BookMapper
) : ApiMapper<BookPageDto, BookPage> {

    override fun mapToDomain(dto: BookPageDto): BookPage {
        return try {
            BookPage(dto.total.toInt(), dto.page.toInt(), dto.books.map {
                bookMapper.mapToDomain(it)
            })
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }
    }

}