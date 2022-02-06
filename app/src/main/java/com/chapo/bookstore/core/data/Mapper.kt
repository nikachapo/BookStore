package com.chapo.bookstore.core.data

interface Mapper<From, To> {

    @Throws(IllegalArgumentException::class)
    fun mapToDomain(from: From): To
}