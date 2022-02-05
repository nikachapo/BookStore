package com.chapo.bookstore.core.domain.models

class BookPage(
    val total: Int,
    val page: Int,
    val books: List<Book>
)