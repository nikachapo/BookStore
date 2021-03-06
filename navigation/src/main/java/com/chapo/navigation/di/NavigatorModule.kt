package com.chapo.navigation.di

import com.chapo.navigation.navigator.DefaultNavigator
import com.chapo.navigation.navigator.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Qualifier

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigatorModule {

    @Binds
    @ActivityScoped
    @Default
    abstract fun bindDefNavigator(defaultNavigator: DefaultNavigator): Navigator

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Default
