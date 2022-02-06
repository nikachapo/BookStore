package com.chapo.bookstore.core.data.cache.mappers

import com.chapo.bookstore.core.data.Mapper
import com.chapo.bookstore.core.data.cache.BookEntity
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import javax.inject.Inject

class BookEntityMapper @Inject constructor() : Mapper<BookEntity, BookDetails> {

    override fun mapToDomain(from: BookEntity): BookDetails {
        return BookDetails(
            title = from.title,
            subtitle = from.subTitle,
            authors = from.authors,
            isbn = from.id,
            pages = from.pages,
            year = from.year,
            rating = from.rating,
            description = from.description,
            price = from.price,
            image = from.image,
            url = from.url
        )
    }

    fun mapFromDomain(from: BookDetails): BookEntity {
        return BookEntity(
            title = from.title,
            subTitle = from.subtitle,
            authors = from.authors,
            id = from.isbn,
            pages = from.pages,
            year = from.year,
            rating = from.rating,
            description = from.description,
            price = from.price,
            image = from.image,
            url = from.url
        )
    }

    fun mapToBook(from: BookEntity): Book {
        return Book(from.title, from.subTitle, from.id, from.price, from.image, from.url)
    }
}