package com.chapo.bookstore.features.savedbooks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chapo.bookstore.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedBooksFragment : Fragment(R.layout.fragment_saved_books) {

//    @Inject
//    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        navigator.navigateTo(BookDetailsDestination(requireContext(), "qwe"))

        return inflater.inflate(R.layout.fragment_saved_books, container, false)
    }

}