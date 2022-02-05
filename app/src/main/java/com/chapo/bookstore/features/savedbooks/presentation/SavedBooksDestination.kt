package com.chapo.bookstore.features.savedbooks.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.chapo.bookstore.R
import com.chapo.navigation.FragmentDestination

class SavedBooksDestination(fragmentManager: FragmentManager) :
    FragmentDestination(R.id.fragment_container, fragmentManager) {

    override fun createFragment(): Fragment {
        return SavedBooksFragment()
    }
}