package com.chapo.paging

interface PagingDataSource<Key, PageModel> {

    suspend fun getData(page: Key): PageModel
}