package com.chapo.bookstore.paging

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class BooksPager @Inject constructor(
    private val booksRepository: IBooksRepository
) : Pager<Int, BookPage> {

    override val cachedPagesSize: Int = 20

    override val pageListFlow: MutableStateFlow<MutableList<BookPage>> = MutableStateFlow(
        mutableListOf()
    )

    override val startingKey: Int = 1

    override var currentKey: Int = startingKey

    override val nextKey: Int
        get() = ++currentKey

    override fun hasNextPage(page: BookPage): Boolean {
        return page.books.isEmpty()
    }

    override suspend fun loadPage(page: Int) {
        val bookPage = booksRepository.getBooksWithPage(page)
        if (hasNextPage(bookPage)) throw NoMorePageException()
        addToCachedList(bookPage)
    }

    private suspend fun addToCachedList(bookPage: BookPage) {
        val list = mutableListOf<BookPage>()
        list.addAll(pageListFlow.value)
        if (!isSpace) list.removeFirst()
        list.add(bookPage)
        pageListFlow.emit(list)
    }

}