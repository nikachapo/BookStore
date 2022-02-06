package com.chapo.bookstore.features.savedbooks.presentation

import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.core.presentation.BaseViewModel
import com.chapo.bookstore.core.utils.ErrorHandler
import com.chapo.bookstore.features.savedbooks.domain.usecases.GetSavedBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SavedBooksVM @Inject constructor(
    getSavedBooksUseCase: GetSavedBooksUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    private val _savedBooks = MutableStateFlow<List<Book>>(listOf())
    val savedBooks = _savedBooks.asStateFlow()

    init {
        launch {
            getSavedBooksUseCase().collectLatest {
                _savedBooks.emit(it)
            }
        }

    }

}