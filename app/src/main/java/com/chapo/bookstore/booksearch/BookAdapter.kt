package com.chapo.bookstore.booksearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chapo.bookstore.core.domain.models.Book
import com.chapo.bookstore.databinding.ItemBookBinding

class BookAdapter(private val onBookClicked: (Book) -> Unit) :
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
                onBookClicked(book)
            }
            binding.tvTitle.text = book.title
        }

    }

}