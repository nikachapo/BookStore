package com.chapo.bookstore.core.data.api.interceptors

import com.chapo.bookstore.core.Logger
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class LoggingInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
  override fun log(message: String) {
    Logger.i("httpLogger", message)
  }
}