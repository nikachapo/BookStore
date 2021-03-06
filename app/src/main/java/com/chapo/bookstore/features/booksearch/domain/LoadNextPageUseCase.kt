package com.chapo.bookstore.features.booksearch.domain

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.paging.Pager
import javax.inject.Inject

class LoadNextPageUseCase @Inject constructor(
    private val pager: Pager<Int, BookPage>,
) {
    suspend operator fun invoke() {
        pager.loadNextPage()
    }
}