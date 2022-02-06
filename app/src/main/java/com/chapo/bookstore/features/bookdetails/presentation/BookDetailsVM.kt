package com.chapo.bookstore.features.bookdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.core.utils.ErrorHandler
import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsVM @Inject constructor(
    private val repository: IBookDetailsRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    val errorState = errorHandler.showErrorState

    private val _bookDetails = MutableStateFlow<BookDetails?>(null)
    val bookDetails = _bookDetails.asStateFlow()

    fun getBookDetails(isbn: String) {
        viewModelScope.launch(errorHandler.globalHandler) {
            _bookDetails.emit(repository.getBookDetails(isbn))
        }
    }


}