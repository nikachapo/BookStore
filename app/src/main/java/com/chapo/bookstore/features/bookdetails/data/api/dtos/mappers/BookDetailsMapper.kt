package com.chapo.bookstore.features.bookdetails.data.api.dtos.mappers

import com.chapo.bookstore.core.data.Mapper
import com.chapo.bookstore.features.bookdetails.data.api.dtos.BookDetailsDto
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import javax.inject.Inject

class BookDetailsMapper @Inject constructor() : Mapper<BookDetailsDto, BookDetails> {
    override fun mapToDomain(from: BookDetailsDto): BookDetails {
        return tryMap {
            BookDetails(
                title = from.title,
                subtitle = from.subtitle,
                authors = from.authors,
                isbn = from.isbn,
                pages = from.pages,
                year = from.year,
                rating = from.rating,
                description = from.description,
                price = from.price,
                image = from.image,
                url = from.url
            )
        }
    }
}