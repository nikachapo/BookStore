package com.chapo.bookstore.features.booksearch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.chapo.bookstore.R
import com.chapo.bookstore.features.booksearch.presentation.BookSearchFragment
import com.chapo.navigation.FragmentDestination

class BookSearchDestination(fragmentManager: FragmentManager) :
    FragmentDestination(R.id.fragment_container, fragmentManager) {

    override fun createFragment(): Fragment {
        return BookSearchFragment()
    }
}