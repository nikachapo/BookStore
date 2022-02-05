package com.chapo.bookstore.features.bookdetails

import android.content.Context
import android.content.Intent
import com.chapo.bookstore.features.bookdetails.presentation.BookDetailsActivity
import com.chapo.navigation.ActivityDestination

class BookDetailsDestination(
    private val context: Context,
    private val isbn: String
) : ActivityDestination(context) {

    override fun generateIntent(): Intent {
        return Intent(context, BookDetailsActivity::class.java).apply {
            putExtra(ARG_ISBN, isbn)
        }
    }

    companion object {
        private const val ARG_ISBN = "isbn"
        fun getIsbn(intent: Intent): String? {
            return intent.getStringExtra(ARG_ISBN)
        }
    }
}