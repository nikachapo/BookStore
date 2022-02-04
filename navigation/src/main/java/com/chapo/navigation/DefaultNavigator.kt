package com.chapo.navigation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class DefaultNavigator @Inject constructor(@ActivityContext context: Context) :
    Navigator(context as AppCompatActivity) {

    override fun navigateBack() = Unit
}