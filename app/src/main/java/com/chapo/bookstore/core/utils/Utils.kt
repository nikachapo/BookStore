package com.chapo.bookstore.core.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <T> MutableStateFlow<T>.updateValue(block: T.() -> Unit) {
    this.value ?: return
    this.value = this.value?.apply(block)!!

}

inline fun <reified T : RecyclerView.Adapter<*>> RecyclerView.getRVAdapter(): T {
    return this.adapter as T
}