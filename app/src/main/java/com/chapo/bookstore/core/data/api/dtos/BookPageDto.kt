package com.chapo.bookstore.core.data.api.dtos

import com.google.gson.annotations.SerializedName

class BookPageDto(
    @SerializedName("total")
    val total: String,
    @SerializedName("page")
    val page: String,
    @SerializedName("books")
    val books: List<BookDto>
)