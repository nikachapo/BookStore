package com.chapo.bookstore.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.chapo.bookstore.R
import com.chapo.bookstore.features.booksearch.BookSearchDestination
import com.chapo.bookstore.features.savedbooks.presentation.SavedBooksDestination
import com.chapo.navigation.navigator.Navigator
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class MainNavigator @Inject constructor(
    @ActivityContext context: Context
) : Navigator(context as AppCompatActivity) {

    private val bookSearchDestination =
        BookSearchDestination(activity.supportFragmentManager)

    private val destination by lazy {
        SavedBooksDestination(activity.supportFragmentManager)
    }

    private fun navigateToSearchBooks() {
        navigateTo(bookSearchDestination)
    }

    private fun navigateToSavedBooks() {
        navigateTo(destination)
    }

    override fun selectMenuItem(itemId: Int): Boolean {
        return if (onSelectMenuItem(itemId)) {
            when (itemId) {
                R.id.mnu_search_book -> {
                    navigateToSearchBooks()
                    true
                }
                R.id.mnu_saved_books -> {
                    navigateToSavedBooks()
                    true
                }
                else -> false
            }
        } else false
    }

    override fun navigateBack() {
        if (currentDestination !is BookSearchDestination) {
            navigateToSearchBooks()
        } else {
            activity.finish()
        }
    }

    override fun navigateToStartingDestination() {
        navigateToSearchBooks()
    }

}

