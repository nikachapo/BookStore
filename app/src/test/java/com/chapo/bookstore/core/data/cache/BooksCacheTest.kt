package com.chapo.bookstore.core.data.cache

import com.chapo.bookstore.BookDatabase
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BooksCacheTest {

    private lateinit var booksCache: IBooksCache

    private lateinit var queries: BookEntityQueries
    private val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    private val database = BookDatabase(driver)

    init {
        BookDatabase.Schema.create(driver)
    }

    @Before
    fun setUp() {
        queries = database.bookEntityQueries
        booksCache = BooksCache(queries)
        addInitialData()
    }

    private fun addInitialData() {
        queries.insertBook(
            "1", "Book1", "", "",
            "", "", "", "", "", "", ""
        )
        queries.insertBook(
            "2", "Book2", "", "",
            "", "", "", "", "", "", ""
        )
    }

    @Test
    fun `getAllBooks returns books from DB`() = runBlocking {
        assertEquals("1", booksCache.getAllBooks().first()[0].id)
        assertEquals("2", booksCache.getAllBooks().first()[1].id)
    }

    @Test
    fun `getBookById returns null if not exists`() = runTest {
        assertNull(booksCache.getBookById("155"))
    }

    @Test
    fun `getBookById returns correct entity`() = runTest {
        val bookEntity = booksCache.getBookById("1")
        assertEquals("1", bookEntity?.id)
        assertEquals("Book1", bookEntity?.title)
    }

    @Test
    fun `insert book writes on DB`() = runTest {
        queries.insertBook(
            "3", "Book3", "", "",
            "", "", "", "", "", "", ""
        )
        val bookEntity = booksCache.getBookById("3")
        assertEquals("3", bookEntity?.id)
    }

    @Test
    fun `delete book deletes on DB`() = runTest {
        booksCache.deleteBookById("1")
        assertNull(booksCache.getBookById("1"))
    }
}