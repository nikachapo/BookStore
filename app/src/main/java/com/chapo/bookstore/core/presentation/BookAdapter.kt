package com.chapo.bookstore.core.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.databinding.ItemBookBinding

typealias onBookClicked = (Book) -> Unit

class BookAdapter(private val onClick: onBookClicked) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book) = oldItem.isbn == newItem.isbn
        override fun areContentsTheSame(oldItem: Book, newItem: Book) = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    fun submitData(books: List<Book>) {
        differ.submitList(books)
    }

    inner class ViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.root.setOnClickListener {
                onClick(book)
            }
            binding.tvTitle.text = book.title
            binding.ivPrice.text = book.price
            binding.ivImage.load(book.imageUrl)
        }

    }

}