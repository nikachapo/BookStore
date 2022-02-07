package com.chapo.bookstore.paging.book

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.paging.Pager
import javax.inject.Inject

class BooksPager @Inject constructor(
    booksRepository: IBooksRepository
) : Pager<Int, BookPage>(BooksPagingDataSource(booksRepository)) {

    override val cachedPagesSize: Int = 20

    override val startingKey: Int = 1

    override var currentKey: Int = startingKey

    override val nextKey: Int
        get() = ++currentKey

    override fun hasNextPage(page: BookPage): Boolean {
        return page.books.isNotEmpty()
    }

}