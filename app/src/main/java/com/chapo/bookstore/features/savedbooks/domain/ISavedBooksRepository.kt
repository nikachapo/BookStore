package com.chapo.bookstore.features.savedbooks.domain

import com.chapo.bookstore.core.domain.models.Book
import kotlinx.coroutines.flow.Flow

interface ISavedBooksRepository {

    fun getSavedBooks(): Flow<List<Book>>
}