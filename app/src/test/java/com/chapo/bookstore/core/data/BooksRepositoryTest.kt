package com.chapo.bookstore.core.data

import com.chapo.bookstore.core.data.api.BooksApi
import com.chapo.bookstore.core.data.api.dtos.BookDto
import com.chapo.bookstore.core.data.api.dtos.BookPageDto
import com.chapo.bookstore.core.data.api.dtos.mappers.BookMapper
import com.chapo.bookstore.core.data.api.dtos.mappers.BookPageMapper
import com.chapo.bookstore.core.data.api.util.ApiHelper
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.bookstore.util.TestCoroutineDispatchersProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BooksRepositoryTest {

    private val apiHelper = ApiHelper()
    private val testDispatchersProvider = TestCoroutineDispatchersProvider()
    private val booksPageMapper = BookPageMapper(BookMapper())
    private lateinit var api: BooksApi
    private lateinit var booksRepo: IBooksRepository

    @Before
    fun setUp() {
        api = mockk()
        booksRepo = BooksRepository(apiHelper, api, testDispatchersProvider, booksPageMapper)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getBookPage calls api`() = runTest {
        coEvery { api.getBookWithPage("1") } returns BookPageDto(
            "5", "1", listOf(
                BookDto("Book1", "", "", "", "", ""),
            )
        )
        booksRepo.getBooksWithPage(1)
        coVerify { api.getBookWithPage("1") }
    }

    @Test
    fun `searchBook calls api`() = runTest {
        coEvery { api.getBookWithPage("1", "Android") } returns BookPageDto(
            "5", "1", listOf(
                BookDto("Book1", "", "", "", "", ""),
            )
        )
        booksRepo.searchBook(1, "Android")
        coVerify { api.getBookWithPage("1", "Android") }
    }
}