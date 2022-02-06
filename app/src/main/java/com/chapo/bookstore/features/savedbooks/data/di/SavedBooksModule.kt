package com.chapo.bookstore.features.savedbooks.data.di

import com.chapo.bookstore.features.savedbooks.data.SavedBooksRepository
import com.chapo.bookstore.features.savedbooks.domain.ISavedBooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SavedBooksModule {

    @Binds
    @ViewModelScoped
    abstract fun provideSavedBooksRepository(repo: SavedBooksRepository): ISavedBooksRepository

}