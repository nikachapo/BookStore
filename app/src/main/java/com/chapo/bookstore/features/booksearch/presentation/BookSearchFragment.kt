package com.chapo.bookstore.features.booksearch.presentation

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chapo.bookstore.R
import com.chapo.bookstore.core.di.MainNav
import com.chapo.bookstore.core.presentation.BookAdapter
import com.chapo.bookstore.core.utils.getRVAdapter
import com.chapo.bookstore.core.utils.hideKeyboard
import com.chapo.bookstore.core.utils.viewbinding.viewBinding
import com.chapo.bookstore.databinding.FragmentBookSearchBinding
import com.chapo.bookstore.features.bookdetails.BookDetailsDestination
import com.chapo.navigation.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject


@AndroidEntryPoint
class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val binding: FragmentBookSearchBinding by viewBinding(FragmentBookSearchBinding::bind)

    private val viewModel: BookSearchVM by viewModels()

    @Inject
    @MainNav
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.topAppBar)
        binding.btRetry.setOnClickListener {
            viewModel.loadFirstPage()
        }
        initBooksAdapter()
        bindStates()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_appbar_nav_menu, menu)
        setUpSearchMenu(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpSearchMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.mnu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onQuerySubmitted(query)
                searchView.hideKeyboard()
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
    }

    private fun bindStates() {
        lifecycleScope.launchWhenStarted {
            viewModel.pageStateFlow.collectLatest {
                binding.rvBooks.getRVAdapter<BookAdapter>().submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.errorState.collectLatest { errorText ->
                errorText?.let {
                    binding.pbMainProgress.isVisible = false
                    binding.pbFooterProgress.isVisible = false
                    binding.llError.isVisible = true
                    binding.tvError.text = it
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.loading.collectLatest { isLoading ->
                binding.llError.isVisible = false
                binding.rvBooks.isVisible = !isLoading
                binding.pbMainProgress.isVisible = isLoading
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.nextPageLoading.collectLatest {
                binding.pbFooterProgress.isVisible = it
            }
        }
    }

    private fun initBooksAdapter() = with(binding.rvBooks) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = BookAdapter {
            navigator.navigateTo(BookDetailsDestination(requireContext(), it.isbn))
        }
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) &&
                    newState == RecyclerView.SCROLL_STATE_IDLE
                ) { // reached end
                    viewModel.loadNextPage()
                }
            }
        })
    }
}