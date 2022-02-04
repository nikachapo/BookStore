package com.chapo.bookstore.core.domain.models

data class Book(
    val title: String,
    val subtitle: String,
    val isbn: String,
    val price: String,
    val imageUrl: String,
    val bookUrl: String
)