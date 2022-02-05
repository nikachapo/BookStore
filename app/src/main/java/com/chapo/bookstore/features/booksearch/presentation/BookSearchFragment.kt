package com.chapo.bookstore.features.booksearch.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chapo.bookstore.R
import com.chapo.bookstore.core.utils.viewbinding.viewBinding
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
        binding.rvBooks.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvBooks.adapter = bookAdapter

        binding.rvBooks.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // reached end
                    viewModel.loadNextPage()
                }
            }
        })

        lifecycleScope.launchWhenStarted {
            viewModel.pageStateFlow.collectLatest {
                bookAdapter.submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.errorState.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}