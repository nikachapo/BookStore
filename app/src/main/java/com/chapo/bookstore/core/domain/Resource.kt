package com.chapo.bookstore.core.domain

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val e: Exception? = null) : Resource<Nothing>()
}
