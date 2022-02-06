package com.chapo.bookstore.paging.di

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.bookstore.paging.book.BooksPager
import com.chapo.bookstore.paging.Pager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PagingModule {

    @Provides
    @ViewModelScoped
    fun providePager(bookRepository: IBooksRepository): Pager<Int, BookPage> {
        return BooksPager(bookRepository)
    }
}