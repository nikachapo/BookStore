package com.chapo.bookstore.core.utils.stringreshelper

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidStringResHelper @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : StringResHelper {

    override fun getString(resId: Int): String = applicationContext.getString(resId)

    override fun getString(resId: Int, vararg params: Any): String =
        applicationContext.getString(resId, *params)

    override fun getStringArray(resId: Int): Array<out String> =
        applicationContext.resources.getStringArray(resId)
}