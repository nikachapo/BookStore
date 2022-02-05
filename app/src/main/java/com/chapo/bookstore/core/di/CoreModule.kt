package com.chapo.bookstore.core.di

import com.chapo.bookstore.core.CoroutineDispatchersProvider
import com.chapo.bookstore.core.DispatchersProvider
import com.chapo.bookstore.core.IStringResHelper
import com.chapo.bookstore.core.StringResHelper
import com.chapo.navigation.DefaultNavigator
import com.chapo.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindNavigator(navigator: DefaultNavigator): Navigator

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider): DispatchersProvider

    @Binds
    abstract fun bindStringResHelper(stringResHelper: StringResHelper): IStringResHelper
}