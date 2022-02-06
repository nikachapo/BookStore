package com.chapo.bookstore.paging.book

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.bookstore.paging.Pager
import com.chapo.bookstore.paging.PagingDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class BooksPager @Inject constructor(
    booksRepository: IBooksRepository
) : Pager<Int, BookPage> {

    override var pagingDataSource: PagingDataSource<Int, BookPage> =
        BooksPagingDataSource(booksRepository)
        set(value) {
            pageListFlow.value.clear()
            field = value
        }

    override val cachedPagesSize: Int = 20

    override val pageListFlow: MutableStateFlow<MutableList<BookPage>> = MutableStateFlow(
        mutableListOf()
    )

    override val startingKey: Int = 1

    override var currentKey: Int = startingKey

    override val nextKey: Int
        get() = ++currentKey

    override fun hasNextPage(page: BookPage): Boolean {
        return page.books.isNotEmpty()
    }

    override suspend fun loadPage(page: Int) {
        val bookPage = getData(page)
        if (hasNextPage(bookPage)) {
            addToCachedList(bookPage)
        }
    }

    private suspend fun addToCachedList(bookPage: BookPage) {
        val list = mutableListOf<BookPage>()
        list.addAll(pageListFlow.value)
        if (!isSpace) list.removeFirst()
        list.add(bookPage)
        pageListFlow.emit(list)
    }

}