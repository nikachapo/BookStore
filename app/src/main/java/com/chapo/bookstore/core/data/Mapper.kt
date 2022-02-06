package com.chapo.bookstore.core.data

interface Mapper<From, To> {

    @Throws(MappingException::class)
    fun mapToDomain(from: From): To

    fun <T> tryMap(map: () -> T): T {
        return try {
            map()
        } catch (e: Exception) {
            throw MappingException()
        }
    }
}

class MappingException : Exception()