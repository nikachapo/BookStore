package com.chapo.bookstore.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView

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
