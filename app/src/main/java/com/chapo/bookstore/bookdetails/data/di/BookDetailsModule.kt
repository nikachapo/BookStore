package com.chapo.bookstore.bookdetails.data.di

import com.chapo.bookstore.bookdetails.data.BookDetailsRepository
import com.chapo.bookstore.bookdetails.data.api.BookDetailsApi
import com.chapo.bookstore.bookdetails.domain.IBookDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class BookDetailsModule {

    @Binds
    @ViewModelScoped
    abstract fun provideBookDetailsRepository(repo: BookDetailsRepository): IBookDetailsRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideApi(builder: Retrofit.Builder): BookDetailsApi {
            return builder
                .build()
                .create(BookDetailsApi::class.java)
        }
    }
}