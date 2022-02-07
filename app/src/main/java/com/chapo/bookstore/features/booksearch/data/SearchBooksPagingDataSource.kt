package com.chapo.bookstore.features.booksearch.data

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.paging.PagingDataSource

class SearchBooksPagingDataSource(
    private val repository: IBooksRepository,
    private val query: String
) : PagingDataSource<Int, BookPage> {

    override suspend fun getData(page: Int): BookPage {
        return repository.searchBook(page, query)
    }
}