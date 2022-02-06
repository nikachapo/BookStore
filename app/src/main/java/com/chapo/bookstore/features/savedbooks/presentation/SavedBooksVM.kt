package com.chapo.bookstore.features.savedbooks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.features.savedbooks.domain.usecases.GetSavedBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedBooksVM @Inject constructor(
    getSavedBooksUseCase: GetSavedBooksUseCase
) : ViewModel() {

    private val _savedBooks = MutableStateFlow<List<Book>>(listOf())
    val savedBooks = _savedBooks.asStateFlow()

    init {
        viewModelScope.launch {
            getSavedBooksUseCase().collectLatest {
                _savedBooks.emit(it)
            }
        }

    }

}