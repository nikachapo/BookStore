package com.chapo.bookstore.features.savedbooks.data

import com.chapo.bookstore.core.data.cache.IBooksCache
import com.chapo.bookstore.core.data.cache.mappers.BookEntityMapper
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.features.savedbooks.domain.ISavedBooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SavedBooksRepository @Inject constructor(
    private val cache: IBooksCache,
    private val mapper: BookEntityMapper
) : ISavedBooksRepository {

    override fun getSavedBooks(): Flow<List<Book>> {
        return cache.getAllBooks().map { bookList ->
            bookList.map { mapper.mapToBook(it) }
        }
    }

}