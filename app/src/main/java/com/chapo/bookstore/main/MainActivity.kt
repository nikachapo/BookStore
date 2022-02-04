package com.chapo.bookstore.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chapo.bookstore.R
import com.chapo.bookstore.booksearch.BookSearchDestination
import com.chapo.bookstore.core.viewBinding
import com.chapo.bookstore.databinding.ActivityMainBinding
import com.chapo.bookstore.savedbooks.SavedBooksDestination
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            mainNavigator.selectMenuItem(menuItem.itemId)
        }

        mainNavigator.setOnDestinationChangedListener { destination ->
            when (destination) {
                is BookSearchDestination -> {
                    binding.bottomNavigation.selectedItemId = R.id.mnu_search_book
                }
                is SavedBooksDestination -> {
                    binding.bottomNavigation.selectedItemId = R.id.mnu_saved_books
                }
            }
        }

        mainNavigator.navigateToSearchBooks()
    }
}