package com.chapo.bookstore.booksearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.chapo.bookstore.R
import com.chapo.bookstore.core.data.api.util.Resource
import com.chapo.bookstore.core.domain.repositories.IBooksRepository
import com.chapo.bookstore.core.viewBinding
import com.chapo.bookstore.databinding.FragmentBookSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val viewBinding: FragmentBookSearchBinding by viewBinding(FragmentBookSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}