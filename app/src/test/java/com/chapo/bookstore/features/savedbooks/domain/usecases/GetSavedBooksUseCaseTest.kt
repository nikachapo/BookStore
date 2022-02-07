package com.chapo.bookstore.features.savedbooks.domain.usecases

import com.chapo.bookstore.core.data.cache.BookEntity
import com.chapo.bookstore.core.data.cache.IBooksCache
import com.chapo.bookstore.core.data.cache.mappers.BookEntityMapper
import com.chapo.bookstore.features.savedbooks.data.SavedBooksRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetSavedBooksUseCaseTest {

    private lateinit var booksCache: IBooksCache
    private lateinit var repo: SavedBooksRepository
    private val booksEntityMapper = BookEntityMapper()
    private lateinit var getSavedBooksUseCase: GetSavedBooksUseCase

    @Before
    fun setUp() {
        booksCache = mockk()
        repo = SavedBooksRepository(booksCache, booksEntityMapper)
        coEvery { booksCache.getAllBooks() } returns flow {
            emit(
                listOf(
                    BookEntity(
                        "1", "Book",
                        "", "", "", "",
                        "", "", "", "", ""
                    )
                )
            )
        }
        getSavedBooksUseCase = GetSavedBooksUseCase(repo)
    }

    @Test
    fun `gets books from cache`() = runTest {
        assertEquals("1", getSavedBooksUseCase().first().first().isbn)
    }
}