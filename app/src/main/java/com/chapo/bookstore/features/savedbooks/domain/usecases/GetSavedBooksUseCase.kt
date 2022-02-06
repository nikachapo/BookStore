package com.chapo.bookstore.features.savedbooks.domain.usecases

import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.features.savedbooks.domain.ISavedBooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedBooksUseCase @Inject constructor(
    private val repository: ISavedBooksRepository
) {

    operator fun invoke(): Flow<List<Book>> = repository.getSavedBooks()
}