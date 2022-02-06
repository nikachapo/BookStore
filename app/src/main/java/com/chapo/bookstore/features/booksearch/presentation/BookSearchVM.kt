package com.chapo.bookstore.features.booksearch.presentation

import com.chapo.bookstore.core.utils.BaseViewModel
import com.chapo.bookstore.core.utils.ErrorHandler
import com.chapo.bookstore.features.booksearch.domain.LoadInitialPageUseCase
import com.chapo.bookstore.features.booksearch.domain.LoadNextPageUseCase
import com.chapo.bookstore.features.booksearch.domain.ObservePagesUseCase
import com.chapo.bookstore.features.booksearch.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookSearchVM @Inject constructor(
    errorHandler: ErrorHandler,
    private val observePagesUseCase: ObservePagesUseCase,
    private val loadInitialPageUseCase: LoadInitialPageUseCase,
    private val loadNextPageUseCase: LoadNextPageUseCase,
    private val searchUseCase: SearchUseCase
) : BaseViewModel(errorHandler) {

    val pageStateFlow = observePagesUseCase.pageStateFlow

    private val _nextPageLoading = MutableStateFlow(false)
    val nextPageLoading = _nextPageLoading.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadFirstPage()
    }

    fun loadFirstPage() {
        launch {
            _loading.emit(true)
            loadInitialPageUseCase()
            _loading.emit(false)
            observePagesUseCase()
        }
    }

    fun loadNextPage() {
        launch {
            _nextPageLoading.emit(true)
            loadNextPageUseCase()
            _nextPageLoading.emit(false)
        }
    }

    fun onQuerySubmitted(query: String?) {
        searchJob?.cancel()
        searchJob = launch {
            _loading.emit(true)
            searchUseCase(query)
            _loading.emit(false)
        }
    }
}