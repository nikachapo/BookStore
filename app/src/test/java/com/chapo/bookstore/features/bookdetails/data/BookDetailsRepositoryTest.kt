package com.chapo.bookstore.features.bookdetails.data

import com.chapo.bookstore.core.data.api.util.ApiHelper
import com.chapo.bookstore.core.data.cache.BookEntity
import com.chapo.bookstore.core.data.cache.IBooksCache
import com.chapo.bookstore.core.data.cache.mappers.BookEntityMapper
import com.chapo.bookstore.features.bookdetails.data.api.BookDetailsApi
import com.chapo.bookstore.features.bookdetails.data.api.dtos.BookDetailsDto
import com.chapo.bookstore.features.bookdetails.data.api.dtos.mappers.BookDetailsMapper
import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import com.chapo.bookstore.util.TestCoroutineDispatchersProvider
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BookDetailsRepositoryTest {

    private val apiHelper = ApiHelper()
    private val testDispatchersProvider = TestCoroutineDispatchersProvider()
    private val bookDetailsMapper = BookDetailsMapper()
    private lateinit var bookEntityMapper: BookEntityMapper
    private lateinit var api: BookDetailsApi
    private lateinit var cache: IBooksCache
    private lateinit var bookDetailsRepository: IBookDetailsRepository


    private var testBookDetails = BookDetails(
        "1", "CachedBook",
        "", "", "", "", "", "", "", "", ""
    )

    private var cachedBook = BookEntity(
        "1", "CachedBook",
        "", "", "", "", "", "", "", "", ""
    )

    private var remoteBook = BookDetailsDto(
        "1", "RemoteBook",
        "", "", "", "", "", "", "", "", ""
    )


    @Before
    fun setUp() {
        api = mockk()
        cache = mockk()
        bookEntityMapper = spyk(BookEntityMapper())
        bookDetailsRepository = BookDetailsRepository(
            bookDetailsMapper,
            api,
            cache,
            bookEntityMapper,
            testDispatchersProvider,
            apiHelper
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `cached book is return if it is saved`() = runTest {
        coEvery { cache.getBookById("1") } returns cachedBook
        coEvery { api.getBookDetails("1") } returns remoteBook
        assertEquals(cachedBook.title, bookDetailsRepository.getBookDetails("1").title)
    }

    @Test
    fun `network call is processed if book is not cached`() = runTest {
        coEvery { cache.getBookById("1") } returns null // book not cached
        coEvery { api.getBookDetails("1") } returns remoteBook
        assertEquals(remoteBook.title, bookDetailsRepository.getBookDetails("1").title)
    }

    @Test
    fun `isFavourite is true if book is saved to cache`() = runTest {
        coEvery { cache.getBookById("1") } returns cachedBook
        coEvery { api.getBookDetails("1") } returns remoteBook
        assertTrue(bookDetailsRepository.getBookDetails("1").isFavourite)
        coEvery { cache.getBookById("1") } returns null
        coEvery { api.getBookDetails("1") } returns remoteBook
        assertFalse(bookDetailsRepository.getBookDetails("1").isFavourite)
    }

    @Test
    fun `addToFavourites calls cache insert`() = runTest {
        every { bookEntityMapper.mapFromDomain(any()) } returns cachedBook
        coEvery { cache.insertBook(cachedBook) } returns Unit
        bookDetailsRepository.addToFavourites(testBookDetails)
        coVerify { cache.insertBook(cachedBook) }
    }

    @Test
    fun `removeFromFavourites calls cache delete`() = runTest {
        coEvery { cache.deleteBookById("1") } returns Unit
        bookDetailsRepository.removeFromFavourites("1")
        coVerify { cache.deleteBookById("1") }
    }
}