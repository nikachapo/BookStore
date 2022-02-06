package com.chapo.bookstore.features.savedbooks.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.chapo.bookstore.R
import com.chapo.bookstore.core.presentation.BookAdapter
import com.chapo.bookstore.core.utils.getRVAdapter
import com.chapo.bookstore.core.utils.viewbinding.viewBinding
import com.chapo.bookstore.databinding.FragmentSavedBooksBinding
import com.chapo.bookstore.features.bookdetails.BookDetailsDestination
import com.chapo.navigation.di.Default
import com.chapo.navigation.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SavedBooksFragment : Fragment(R.layout.fragment_saved_books) {

    private val binding: FragmentSavedBooksBinding by viewBinding(FragmentSavedBooksBinding::bind)

    private val viewModel: SavedBooksVM by viewModels()

    @Inject
    @Default
    lateinit var navigator: Navigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooks.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvBooks.adapter = BookAdapter {
            navigator.navigateTo(BookDetailsDestination(requireContext(), it.isbn))
        }

        lifecycleScope.launchWhenStarted {
            viewModel.savedBooks.collectLatest {
                binding.pbProgress.isVisible = false
                binding.tvNoSavedBooks.isVisible = it.isEmpty()
                binding.rvBooks.getRVAdapter<BookAdapter>().submitData(it)
            }
        }

    }

}