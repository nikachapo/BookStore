package com.chapo.bookstore.core.paging.book

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.paging.PagingDataSource

class BooksPagingDataSource(
    private val repository: IBooksRepository
) : PagingDataSource<Int, BookPage> {
    override suspend fun getData(page: Int): BookPage {
        return repository.getBooksWithPage(page)
    }
}