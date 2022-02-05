package com.chapo.bookstore.booksearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.chapo.bookstore.R
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.core.viewBinding
import com.chapo.bookstore.databinding.FragmentBookSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val binding: FragmentBookSearchBinding by viewBinding(FragmentBookSearchBinding::bind)

    private val viewModel: BookSearchVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookAdapter = BookAdapter {

        }

        binding.rvBooks.adapter = bookAdapter


        binding.rvBooks.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvBooks.canScrollVertically(1)) {
                    viewModel.loadNextPage()
                }
            }
        })

        lifecycleScope.launchWhenStarted {
            viewModel.pageStateFlow.collectLatest {
                bookAdapter.submitData(it)
            }
        }
    }
}