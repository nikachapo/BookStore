package com.chapo.bookstore.core.di

import com.chapo.bookstore.main.MainNavigator
import com.chapo.navigation.navigator.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @Binds
    @ActivityScoped
    @MainNav
    abstract fun bindDefNavigator(mainNavigator: MainNavigator): Navigator
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainNav
