package com.chapo.bookstore.features.bookdetails.domain.usecases

import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(
    private val repository: IBookDetailsRepository
) {

    private val _bookDetails = MutableStateFlow<BookDetails?>(null)
    val bookDetails = _bookDetails.asStateFlow()

    suspend operator fun invoke(isbn: String) {
        _bookDetails.emit(repository.getBookDetails(isbn))
    }
}