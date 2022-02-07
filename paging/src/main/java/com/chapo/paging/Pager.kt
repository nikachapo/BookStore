package com.chapo.paging

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class Pager<Key, PageModel>(pagingDataSource: PagingDataSource<Key, PageModel>) {

    private val _pageListFlow: MutableStateFlow<MutableList<PageModel>> =
        MutableStateFlow(mutableListOf())
    val pageListFlow = _pageListFlow.asStateFlow()

    var pagingDataSource: PagingDataSource<Key, PageModel> = pagingDataSource
        set(value) {
            _pageListFlow.value.clear()
            field = value
        }

    abstract val cachedPagesSize: Int

    abstract val startingKey: Key

    abstract var currentKey: Key

    abstract val nextKey: Key

    private val isSpace: Boolean
        get() = _pageListFlow.value.size < cachedPagesSize

    abstract fun isNotEmptyPage(page: PageModel): Boolean

    suspend fun loadStartingPage() = loadPage(startingKey)

    open suspend fun loadPage(page: Key) {
        val pageData = getData(page)
        if (isNotEmptyPage(pageData)) {
            addToCachedList(pageData)
        }
    }

    suspend fun loadNextPage() = loadPage(nextKey)

    open suspend fun getData(page: Key): PageModel = pagingDataSource.getData(page)

    private suspend fun addToCachedList(page: PageModel) {
        val list = mutableListOf<PageModel>()
        list.addAll(_pageListFlow.value)
        if (!isSpace) list.removeFirst()
        list.add(page)
        _pageListFlow.emit(list)
    }
}