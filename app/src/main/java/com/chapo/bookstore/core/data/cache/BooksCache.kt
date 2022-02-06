package com.chapo.bookstore.core.data.cache

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksCache @Inject constructor(
    private val queries: BookEntityQueries
) : IBooksCache {

    override fun getAllBooks(): Flow<List<BookEntity>> {
        return queries.getAllBooks()
            .asFlow()
            .mapToList()
    }

    override suspend fun getBookById(id: String): BookEntity? {
        return queries.getBookById(id).executeAsOneOrNull()
    }

    override suspend fun insertBook(entity: BookEntity) {
        queries.insertBook(
            id = entity.id,
            title = entity.title,
            subTitle = entity.subTitle,
            authors = entity.authors,
            pages = entity.pages,
            year = entity.year,
            rating = entity.rating,
            description = entity.description,
            price = entity.price,
            image = entity.image,
            url = entity.url
        )
    }

    override suspend fun deleteBookById(id: String) {
        queries.deleteBookById(id)
    }

}