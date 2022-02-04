package com.chapo.bookstore.core.domain

import java.io.IOException

class NetworkUnavailableException(message: String = "") : IOException(message)

class NetworkException(message: String, val code: Int): Exception(message)

class UnknownException: Exception()