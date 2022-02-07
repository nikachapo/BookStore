package com.chapo.bookstore.features.bookdetails.domain.usecases

import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class GetBookDetailsUseCaseTest {

    @Test
    fun `bookDetailsStateFlow is updated`() = runTest {
        val testBookDetails = BookDetails(
            "1", "CachedBook",
            "", "", "", "", "", "", "", "", ""
        )
        val mockRepo: IBookDetailsRepository = mockk()
        coEvery { mockRepo.getBookDetails("1") } returns testBookDetails
        val getBookDetailsUseCase = GetBookDetailsUseCase(mockRepo)
        getBookDetailsUseCase("1")
        assertEquals(testBookDetails, getBookDetailsUseCase.bookDetails.value)
    }
}