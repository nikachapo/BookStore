package com.chapo.bookstore.core.data.di

import android.app.Application
import com.chapo.bookstore.BookDatabase
import com.chapo.bookstore.core.data.cache.BookEntityQueries
import com.chapo.bookstore.core.data.cache.BooksCache
import com.chapo.bookstore.core.data.cache.IBooksCache
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BooksCacheModule {

    @Binds
    abstract fun bindBooksCache(booksCache: BooksCache): IBooksCache

    companion object {

        @Provides
        @Singleton
        fun provideSqlDriver(app: Application): SqlDriver {
            return AndroidSqliteDriver(
                schema = BookDatabase.Schema,
                context = app,
                name = "books.db"
            )
        }

        @Provides
        @Singleton
        fun provideBookDatabase(driver: SqlDriver): BookDatabase {
            return BookDatabase(driver = driver)
        }

        @Provides
        @Singleton
        fun provideBookQueries(bookDatabase: BookDatabase): BookEntityQueries {
            return bookDatabase.bookEntityQueries
        }
    }
}