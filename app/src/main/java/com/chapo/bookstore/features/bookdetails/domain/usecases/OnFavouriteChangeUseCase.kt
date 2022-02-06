package com.chapo.bookstore.features.bookdetails.domain.usecases

import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import javax.inject.Inject

class OnFavouriteChangeUseCase @Inject constructor(
    private val repository: IBookDetailsRepository
) {
    suspend operator fun invoke(checked: Boolean, bookDetails: BookDetails) {
        if (checked) {
            repository.addToFavourites(bookDetails)
        } else {
            repository.removeFromFavourites(bookDetails.isbn)
        }
    }
}