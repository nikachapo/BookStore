package com.chapo.bookstore.features.booksearch.domain

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.paging.Pager
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class LoadNextPageUseCaseTest {

    @Test
    fun `calls loadNextPage`() = runTest {
        val pager: Pager<Int, BookPage> = mockk()
        coEvery { pager.loadNextPage() } returns Unit
        val loadNextPageUseCase = LoadNextPageUseCase(pager)
        loadNextPageUseCase()
        coVerify { pager.loadNextPage() }
    }
}