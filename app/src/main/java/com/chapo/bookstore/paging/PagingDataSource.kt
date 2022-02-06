package com.chapo.bookstore.paging

interface PagingDataSource<Key, PageModel> {

    suspend fun getData(page: Key): PageModel
}