package com.chapo.bookstore.core.di

import com.chapo.bookstore.core.utils.dispatcher.CoroutineDispatchersProvider
import com.chapo.bookstore.core.utils.dispatcher.DispatchersProvider
import com.chapo.bookstore.core.utils.stringreshelper.AndroidStringResHelper
import com.chapo.bookstore.core.utils.stringreshelper.StringResHelper
import com.chapo.navigation.navigator.DefaultNavigator
import com.chapo.navigation.navigator.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindDispatchersProvider(dispatchersProvider: CoroutineDispatchersProvider): DispatchersProvider

    @Binds
    abstract fun bindStringResHelper(stringResHelper: AndroidStringResHelper): StringResHelper
}