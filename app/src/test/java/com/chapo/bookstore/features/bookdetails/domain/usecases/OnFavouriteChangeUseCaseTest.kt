package com.chapo.bookstore.features.bookdetails.domain.usecases

import com.chapo.bookstore.features.bookdetails.FakeBookDetailsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class OnFavouriteChangeUseCaseTest {

    private lateinit var fakeRepo: FakeBookDetailsRepo
    private lateinit var onFavouriteChangeUseCase: OnFavouriteChangeUseCase

    @Before
    fun setUp() {
        fakeRepo = FakeBookDetailsRepo()
        onFavouriteChangeUseCase = OnFavouriteChangeUseCase(fakeRepo)
    }

    @Test
    fun `book is added in favourites`() = runTest {
        onFavouriteChangeUseCase(true, fakeRepo.book)
        assertEquals(fakeRepo.book, fakeRepo.books.first())
    }

    @Test
    fun `book is removed from favourites`() = runTest {
        onFavouriteChangeUseCase(true, fakeRepo.book)
        onFavouriteChangeUseCase(false, fakeRepo.book)
        assertEquals(0, fakeRepo.books.size) // book was deleted
    }
}
