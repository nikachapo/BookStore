package com.chapo.bookstore.core.di

import com.chapo.bookstore.core.data.BooksRepository
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindBooksRepository(repository: BooksRepository): IBooksRepository
}