package com.chapo.bookstore.paging

import kotlinx.coroutines.flow.StateFlow

interface Pager<Key, PageModel> {

    val pageListFlow: StateFlow<MutableList<PageModel>>

    var pagingDataSource: PagingDataSource<Key, PageModel>

    val cachedPagesSize: Int

    val startingKey: Key

    var currentKey: Key

    val nextKey: Key

    val isSpace: Boolean
        get() = pageListFlow.value.size < cachedPagesSize

    fun hasNextPage(page: PageModel): Boolean

    suspend fun loadStartingPage() = loadPage(startingKey)

    suspend fun loadPage(page: Key)

    suspend fun loadNextPage() = loadPage(nextKey)

    suspend fun getData(page: Key): PageModel = pagingDataSource.getData(page)
}