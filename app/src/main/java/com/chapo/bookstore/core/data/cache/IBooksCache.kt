package com.chapo.bookstore.core.data.cache

import kotlinx.coroutines.flow.Flow

interface IBooksCache {

    fun getAllBooks(): Flow<List<BookEntity>>

    suspend fun getBookById(id: String): BookEntity?

    suspend fun insertBook(entity: BookEntity)

    suspend fun deleteBookById(id: String)
}