package com.chapo.bookstore.features.bookdetails.presentation

import android.content.Intent
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

        binding.ivFav.setOnClickListener {
            viewModel.onFavouriteCheckChanged(binding.ivFav.isChecked)
        }

        binding.ivShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, viewModel.bookDetails.value?.url)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
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

        lifecycleScope.launchWhenStarted {
            viewModel.loading.collectLatest { isLoading ->
                binding.llError.isVisible = !isLoading
                binding.mainContent.isVisible = !isLoading
                binding.pbProgress.isVisible = isLoading
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
        binding.ivFav.isChecked = details.isFavourite
        binding.ivCover.load(details.image)
        binding.tvTitle.text = details.title
        binding.tvTitleTop.text = details.title
        binding.tvPrice.text = details.price
        binding.tvAuthors.text = details.authors
        binding.ratingBar.rating = details.rating.toFloatOrNull() ?: 0.toFloat()
        binding.tvDescription.text = details.description
    }


}