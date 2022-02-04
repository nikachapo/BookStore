package com.chapo.bookstore.core.domain.models

import com.chapo.bookstore.core.domain.models.Book

class BookPage(
    val total: Int,
    val page: Int,
    val books: List<Book>
)