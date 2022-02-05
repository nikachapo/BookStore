package com.chapo.bookstore.features.booksearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapo.bookstore.R
import com.chapo.bookstore.core.utils.ErrorHandler
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.paging.NoMorePageException
import com.chapo.bookstore.paging.Pager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchVM @Inject constructor(
    private val pager: Pager<Int, BookPage>,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _pageStateFlow = MutableStateFlow<List<Book>>(listOf())
    val pageStateFlow = _pageStateFlow.asStateFlow()

    val errorState = errorHandler.showErrorState

    init {
        errorHandler.addExceptions(NoMorePageException::class, R.string.app_name)
        loadFirstPage()
    }

    private fun loadFirstPage() {
        viewModelScope.launch(errorHandler.coroutineExceptionHandler) {
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