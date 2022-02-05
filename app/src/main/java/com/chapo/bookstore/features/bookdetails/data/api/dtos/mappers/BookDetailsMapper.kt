package com.chapo.bookstore.features.bookdetails.data.api.dtos.mappers

import com.chapo.bookstore.features.bookdetails.data.api.dtos.BookDetailsDto
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import com.chapo.bookstore.core.data.api.dtos.mappers.ApiMapper
import javax.inject.Inject

class BookDetailsMapper @Inject constructor() : ApiMapper<BookDetailsDto, BookDetails> {
    override fun mapToDomain(dto: BookDetailsDto): BookDetails {
        return BookDetails(
            title = dto.title,
            subtitle = dto.subtitle,
            authors = dto.authors,
            isbn = dto.isbn,
            pages = dto.pages,
            year = dto.year,
            rating = dto.rating,
            description = dto.description,
            price = dto.price,
            image = dto.image,
            url = dto.url
        )
    }
}