package com.chapo.bookstore.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StringResHelper @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : IStringResHelper {

    override fun getString(resId: Int): String = applicationContext.getString(resId)

    override fun getString(resId: Int, vararg params: Any): String =
        applicationContext.getString(resId, *params)

    override fun getStringArray(resId: Int): Array<out String> =
        applicationContext.resources.getStringArray(resId)
}