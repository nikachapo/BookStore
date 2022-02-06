package com.chapo.bookstore.features.booksearch.domain

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.bookstore.features.booksearch.data.SearchBooksPagingDataSource
import com.chapo.bookstore.paging.Pager
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val booksRepository: IBooksRepository,
    private val pager: Pager<Int, BookPage>,
) {

    private var query: String? = null

    suspend operator fun invoke(query: String?) {
        if (this.query == query) return
        query?.let {
            pager.pagingDataSource = SearchBooksPagingDataSource(booksRepository, query)
            pager.loadStartingPage()
            this.query = query
        }
    }
}