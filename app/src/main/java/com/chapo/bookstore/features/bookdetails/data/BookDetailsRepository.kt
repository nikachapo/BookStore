package com.chapo.bookstore.features.bookdetails.data

import com.chapo.bookstore.core.data.api.util.ApiHelper
import com.chapo.bookstore.core.data.cache.IBooksCache
import com.chapo.bookstore.core.data.cache.mappers.BookEntityMapper
import com.chapo.bookstore.core.utils.dispatcher.DispatchersProvider
import com.chapo.bookstore.features.bookdetails.data.api.BookDetailsApi
import com.chapo.bookstore.features.bookdetails.data.api.dtos.mappers.BookDetailsMapper
import com.chapo.bookstore.features.bookdetails.domain.IBookDetailsRepository
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookDetailsRepository @Inject constructor(
    private val mapper: BookDetailsMapper,
    private val bookDetailsApi: BookDetailsApi,
    private val cache: IBooksCache,
    private val bookEntityMapper: BookEntityMapper,
    private val dispatchersProvider: DispatchersProvider,
    private val apiHelper: ApiHelper
) : IBookDetailsRepository {

    override suspend fun getBookDetails(isbn: String): BookDetails =
        withContext(dispatchersProvider.io) {
            // if cached return it else do network call
            cache.getBookById(isbn)?.let {
                bookEntityMapper.mapToDomain(it).apply {
                    isFavourite = true
                }
            } ?: apiHelper.safeApiCall(mapper) {
                bookDetailsApi.getBookDetails(isbn)
            }
        }

    override suspend fun addToFavourites(bookDetails: BookDetails) {
        cache.insertBook(bookEntityMapper.mapFromDomain(bookDetails))
    }

    override suspend fun removeFromFavourites(isbn: String) {
        cache.deleteBookById(isbn)
    }
}