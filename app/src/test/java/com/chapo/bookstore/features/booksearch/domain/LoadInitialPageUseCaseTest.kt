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
class LoadInitialPageUseCaseTest {

    @Test
    fun `calls loadStartingPage`() = runTest {
        val pager: Pager<Int, BookPage> = mockk()
        coEvery { pager.loadStartingPage() } returns Unit
        val loadInitialPageUseCase = LoadInitialPageUseCase(pager)
        loadInitialPageUseCase()
        coVerify { pager.loadStartingPage() }
    }
}