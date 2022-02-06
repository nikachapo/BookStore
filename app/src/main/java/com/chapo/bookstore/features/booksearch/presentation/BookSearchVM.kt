package com.chapo.bookstore.features.booksearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapo.bookstore.R
import com.chapo.bookstore.core.utils.ErrorHandler
import com.chapo.bookstore.features.booksearch.domain.LoadInitialPageUseCase
import com.chapo.bookstore.features.booksearch.domain.LoadNextPageUseCase
import com.chapo.bookstore.features.booksearch.domain.ObservePagesUseCase
import com.chapo.bookstore.features.booksearch.domain.SearchUseCase
import com.chapo.bookstore.paging.NoMorePageException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchVM @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val observePagesUseCase: ObservePagesUseCase,
    private val loadInitialPageUseCase: LoadInitialPageUseCase,
    private val loadNextPageUseCase: LoadNextPageUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    val pageStateFlow = observePagesUseCase.pageStateFlow

    val errorState = errorHandler.showErrorState

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _nextPageLoading = MutableStateFlow(false)
    val nextPageLoading = _nextPageLoading.asStateFlow()

    init {
        errorHandler.addExceptions(NoMorePageException::class, R.string.app_name)
        loadFirstPage()
    }

    private fun loadFirstPage() {
        viewModelScope.launch(errorHandler.globalHandler) {
            _loading.emit(true)
            loadInitialPageUseCase()
            _loading.emit(false)
            observePagesUseCase()
        }
    }

    fun loadNextPage() {
        viewModelScope.launch(errorHandler.globalHandler) {
            _nextPageLoading.emit(true)
            loadNextPageUseCase()
            _nextPageLoading.emit(false)
        }
    }

    fun onQuerySubmitted(query: String?) {
        viewModelScope.launch(errorHandler.globalHandler) {
            _loading.emit(true)
            searchUseCase(query)
            _loading.emit(false)
        }
    }
}