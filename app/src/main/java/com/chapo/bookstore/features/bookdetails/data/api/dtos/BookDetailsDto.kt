package com.chapo.bookstore.features.bookdetails.data.api.dtos

import com.google.gson.annotations.SerializedName

data class BookDetailsDto(
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("authors")
    val authors: String,
    @SerializedName("isbn13")
    val isbn: String,
    @SerializedName("pages")
    val pages: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("desc")
    val description: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("url")
    val url: String
)