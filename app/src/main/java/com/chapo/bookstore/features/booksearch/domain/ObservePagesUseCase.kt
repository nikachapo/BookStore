package com.chapo.bookstore.features.booksearch.domain

import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.paging.Pager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ObservePagesUseCase @Inject constructor(
    private val pager: Pager<Int, BookPage>
) {

    private val _pageStateFlow = MutableStateFlow<List<Book>>(listOf())
    val pageStateFlow = _pageStateFlow.asStateFlow()

    suspend operator fun invoke() {
        pager.pageListFlow.map { bookPage ->
            val books = mutableListOf<Book>()
            bookPage.forEach { books.addAll(it.books) }
            books.toList()
        }.collectLatest {
            _pageStateFlow.emit(it)
        }
    }
}