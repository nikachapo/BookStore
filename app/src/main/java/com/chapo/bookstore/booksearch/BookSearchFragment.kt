package com.chapo.bookstore.booksearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.chapo.bookstore.R
import com.chapo.bookstore.core.viewBinding
import com.chapo.bookstore.databinding.FragmentBookSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val viewBinding: FragmentBookSearchBinding by viewBinding(FragmentBookSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.root
    }
}