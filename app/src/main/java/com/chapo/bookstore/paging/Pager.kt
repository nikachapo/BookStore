package com.chapo.bookstore.paging

import kotlinx.coroutines.flow.StateFlow

interface Pager<Key : Any, PageModel : Any> {

    val pageListFlow: StateFlow<MutableList<PageModel>>

    val pageSize: Int

    val startingKey: Key

    var currentKey: Key

    val previousKey: Key?

    val nextKey: Key

    val isSpace: Boolean
        get() = pageListFlow.value.size < pageSize

    fun hasNextPage(page: PageModel): Boolean

    suspend fun loadStartingPage() = loadPage(startingKey)

    suspend fun loadPage(page: Key)

    suspend fun loadNextPage() = loadPage(nextKey)

    suspend fun loadPreviousPage() = previousKey?.let { loadPage(it) }
}