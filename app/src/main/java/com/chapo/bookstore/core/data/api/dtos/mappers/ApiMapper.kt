package com.chapo.bookstore.core.data.api.dtos.mappers

interface ApiMapper<Dto, Domain> {

    @Throws(IllegalArgumentException::class)
    fun mapToDomain(dto: Dto): Domain
}