package com.chapo.bookstore.features.booksearch.presentation

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chapo.bookstore.R
import com.chapo.bookstore.core.utils.getRVAdapter
import com.chapo.bookstore.core.utils.viewbinding.viewBinding
import com.chapo.bookstore.databinding.FragmentBookSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val binding: FragmentBookSearchBinding by viewBinding(FragmentBookSearchBinding::bind)

    private val viewModel: BookSearchVM by viewModels()

    private var bookAdapter: BookAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.topAppBar)
        initBooksAdapter()
        bindStates()
    }

    private fun bindStates() {
        lifecycleScope.launchWhenStarted {
            viewModel.pageStateFlow.collectLatest {
                binding.rvBooks.getRVAdapter<BookAdapter>().submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.errorState.collectLatest {
                it?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bookAdapter = null
    }

    private fun initBooksAdapter() {
        bookAdapter = BookAdapter {

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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_appbar_nav_menu, menu)
        val searchItem = menu.findItem(R.id.mnu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onQuerySubmitted(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onQueryChanged(newText)
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}