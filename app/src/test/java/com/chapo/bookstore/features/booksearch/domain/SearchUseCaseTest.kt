package com.chapo.bookstore.features.booksearch.domain

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.bookstore.core.paging.book.BooksPager
import com.chapo.bookstore.features.booksearch.data.SearchBooksPagingDataSource
import com.chapo.paging.Pager
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchUseCaseTest {

    private lateinit var searchUseCase: SearchUseCase
    private lateinit var booksRepository: IBooksRepository
    private lateinit var pager: Pager<Int, BookPage>

    @Before
    fun setUp() {
        booksRepository = mockk()
        pager = spyk(BooksPager(booksRepository))
        coEvery { pager.loadStartingPage() } returns Unit
        searchUseCase = SearchUseCase(booksRepository, pager)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `datasource of pager is set`() = runTest {
        searchUseCase("Android")
        assertTrue(pager.pagingDataSource is SearchBooksPagingDataSource)
    }

    @Test
    fun `same query is not searched`() = runTest {
        searchUseCase("Android")
        coVerify { pager.loadStartingPage() }
        coEvery { pager.loadStartingPage() } returns Unit // reset mock method to catch second call
        searchUseCase("Android")
        coVerify { pager.loadStartingPage() }
    }
}