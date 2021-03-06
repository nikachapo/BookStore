package com.chapo.bookstore.features.bookdetails.domain.models

data class BookDetails(
    val title: String,
    val subtitle: String,
    val authors: String,
    val isbn: String,
    val pages: String,
    val year: String,
    val rating: String,
    val description: String,
    val price: String,
    val image: String,
    val url: String
) {
    var isFavourite: Boolean = false
}
