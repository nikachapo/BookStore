package com.chapo.bookstore.booksearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapo.bookstore.core.domain.NetworkException
import com.chapo.bookstore.core.domain.NetworkUnavailableException
import com.chapo.bookstore.core.domain.Resource
import com.chapo.bookstore.core.domain.UnknownException
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.paging.LoadingException
import com.chapo.bookstore.paging.NoMorePageException
import com.chapo.bookstore.paging.Pager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchVM @Inject constructor(
    private val pager: Pager<Int, BookPage>
) : ViewModel() {

    private val _pageStateFlow = MutableStateFlow<List<Book>?>(null)

    val pageStateFlow = _pageStateFlow.asStateFlow()

    init {

        val handler = CoroutineExceptionHandler { _, exception ->
            when (exception) {
                is NoMorePageException -> {

                }
                is LoadingException -> {

                }
                is NetworkException -> {

                }
                is NetworkUnavailableException -> {

                }
                is UnknownException -> {

                }
            }
        }

        viewModelScope.launch(handler) {
            pager.loadStartingPage()

            pager.pageListFlow.map { bookPage ->
                val books = mutableListOf<Book>()
                bookPage.forEach { books.addAll(it.books) }
                books.toList()
            }.collectLatest {
                _pageStateFlow.emit(it)
            }
        }


    }

    fun loadNextPage() {
        viewModelScope.launch {
            pager.loadNextPage()
        }
    }
}