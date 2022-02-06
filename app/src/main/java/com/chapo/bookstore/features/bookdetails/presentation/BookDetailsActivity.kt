package com.chapo.bookstore.features.bookdetails.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.chapo.bookstore.core.utils.viewbinding.viewBinding
import com.chapo.bookstore.databinding.ActivityBookDetailsBinding
import com.chapo.bookstore.features.bookdetails.BookDetailsDestination
import com.chapo.bookstore.features.bookdetails.domain.models.BookDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BookDetailsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityBookDetailsBinding::inflate)

    private val viewModel: BookDetailsVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            onBackPressed()
            finish()
        }
        
        binding.ivShare.setOnClickListener {
            // TODO: 2/6/2022
        }

        lifecycleScope.launchWhenStarted {
            viewModel.errorState.collectLatest {

            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.bookDetails.collectLatest { bookDetails ->
                bookDetails?.let { fillDetails(it) }
            }
        }

        viewModel.getBookDetails(
            BookDetailsDestination.getIsbn(intent)
        )

    }

    private fun fillDetails(details: BookDetails) {
        binding.mainContent.isVisible = true
        binding.pbProgress.isVisible = false
        binding.btRetry.isVisible = false
        binding.llError.isVisible = false
        binding.ivCover.load(details.image)
        binding.tvTitle.text = details.title
        binding.tvTitleTop.text = details.title
        binding.tvPrice.text = details.price
        binding.tvAuthors.text = details.authors
        binding.ratingBar.rating = details.rating.toFloat()
        binding.tvDescription.text = details.description
    }


}