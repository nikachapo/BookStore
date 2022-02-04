package com.chapo.bookstore.core.di

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
}