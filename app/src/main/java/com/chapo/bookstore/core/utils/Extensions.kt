package com.chapo.bookstore.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView

fun View.hideKeyboard() {
    findFocus()?.let { view ->
        val imm = getSystemService(context, InputMethodManager::class.java)
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

/**
 * For adapters to avoid memory leaks in Fragments
 */
inline fun <reified T : RecyclerView.Adapter<*>> RecyclerView.getRVAdapter(): T {
    return this.adapter as T
}

fun Context.shareUrl(url: String?) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.openBrowser(url: String?) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}
