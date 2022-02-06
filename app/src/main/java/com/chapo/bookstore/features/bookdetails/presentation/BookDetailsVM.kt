package com.chapo.bookstore.features.bookdetails.presentation

import androidx.lifecycle.viewModelScope
import com.chapo.bookstore.core.presentation.BaseViewModel
import com.chapo.bookstore.core.utils.ErrorHandler
import com.chapo.bookstore.features.bookdetails.domain.usecases.GetBookDetailsUseCase
import com.chapo.bookstore.features.bookdetails.domain.usecases.OnFavouriteChangeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsVM @Inject constructor(
    private val getBookDetailsUseCase: GetBookDetailsUseCase,
    private val onFavouriteChangeUseCase: OnFavouriteChangeUseCase,
    errorHandler: ErrorHandler
) : BaseViewModel(errorHandler) {

    val bookDetails = getBookDetailsUseCase.bookDetails

    private var isbn: String? = null

    private var favouriteJob: Job? = null

    fun getBookDetails(isbn: String) {
        launch {
            _loading.emit(true)
            getBookDetailsUseCase(isbn)
            _loading.emit(false)
        }
    }

    fun onFavouriteCheckChanged(checked: Boolean) {
        favouriteJob?.cancel()
        favouriteJob = viewModelScope.launch(errorHandler.globalHandler) {
            bookDetails.value?.let {
                onFavouriteChangeUseCase(checked, it)
            }
        }
    }

    fun retry() {
        isbn?.let { getBookDetails(it) }
    }


}