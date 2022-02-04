package com.chapo.bookstore.bookdetails.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chapo.bookstore.bookdetails.BookDetailsDestination
import com.chapo.bookstore.core.viewBinding
import com.chapo.bookstore.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityBookDetailsBinding::inflate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        BookDetailsDestination.getIsbn(intent)

    }


}