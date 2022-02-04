package com.chapo.bookstore.bookdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chapo.bookstore.core.activityViewBinding
import com.chapo.bookstore.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : AppCompatActivity() {

    private val binding by activityViewBinding(ActivityBookDetailsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        BookDetailsDestination.getIsbn(intent)
    }
}