package com.chapo.bookstore.core.paging.book.di

import com.chapo.bookstore.core.domain.models.BookPage
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.bookstore.core.paging.book.BooksPager
import com.chapo.paging.Pager
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