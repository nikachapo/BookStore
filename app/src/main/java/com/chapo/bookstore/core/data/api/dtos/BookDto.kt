package com.chapo.bookstore.core.data.api.dtos

import com.google.gson.annotations.SerializedName

class BookDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("isbn13")
    val isbn: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("url")
    val bookUrl: String
)