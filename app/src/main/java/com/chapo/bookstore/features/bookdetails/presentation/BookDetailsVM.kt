package com.chapo.bookstore.features.bookdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chapo.bookstore.core.utils.ErrorHandler
import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
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
    private val errorHandler: ErrorHandler
) : ViewModel() {

    val errorState = errorHandler.showErrorState

    val bookDetails = getBookDetailsUseCase.bookDetails

    private var favouriteJob: Job? = null

    fun getBookDetails(isbn: String) {
        viewModelScope.launch(errorHandler.globalHandler) {
            getBookDetailsUseCase(isbn)
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


}