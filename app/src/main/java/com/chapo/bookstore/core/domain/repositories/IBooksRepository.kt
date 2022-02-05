package com.chapo.bookstore.core.domain.repositories

import com.chapo.bookstore.core.domain.Resource
import com.chapo.bookstore.core.domain.models.BookPage

interface IBooksRepository {

    suspend fun getBooksWithPage(page: Int): Resource<BookPage>

    suspend fun searchBook(page: Int, query: String): Resource<BookPage>

}