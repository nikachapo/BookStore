package com.chapo.bookstore.core

import android.util.Log
import com.chapo.bookstore.BuildConfig

object Logger {

    private val isDebug = BuildConfig.DEBUG

    fun d(tag: String, message: String, t: Throwable? = null) {
        if (isDebug) {
            Log.d(tag, message, t)
        }
    }

    fun i(tag: String, message: String, t: Throwable? = null) {
        if (isDebug) {
            Log.i(tag, message, t)
        }
    }

    fun e(tag: String, message: String, t: Throwable? = null) {
        if (isDebug) {
            Log.e(tag, message, t)
        }
    }

}