package com.chapo.bookstore.paging

import com.chapo.bookstore.core.domain.Resource
import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.bookstore.core.updateValue
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class BooksPager @Inject constructor(
    private val booksRepository: IBooksRepository
) : Pager<Int, BookPage> {

    override val pageSize: Int = 5

    override val pageListFlow: MutableStateFlow<MutableList<BookPage>> = MutableStateFlow(
        mutableListOf()
    )

    override val startingKey: Int = 1

    override var currentKey: Int = startingKey

    override val nextKey: Int
        get() {
            return ++currentKey
        }

    override val previousKey: Int? = if (currentKey == 0) null else currentKey - 1

    override suspend fun loadPage(page: Int) {
        booksRepository.getBooksWithPage(page).also {
            if (it is Resource.Success) {
                if (it.data.total == 0) {
                    throw NoMorePageException()
                }
                addToCachedList(it.data)
            } else {
                throw LoadingException()
            }
        }
    }

    private suspend fun addToCachedList(bookPage: BookPage) {
        val list = mutableListOf<BookPage>()
        list.addAll(pageListFlow.value)
        if (!isSpace) list.removeFirst()
        list.add(bookPage)
        pageListFlow.emit(list)
    }

//    private fun cachedOrNull(page: Int): BookPage? {
//        if (pageListFlow.value.size == 0) return null
//
//        pageListFlow.value.forEach {
//            if (it.page == page) {
//                return it
//            }
//        }
//        return null
//    }
}