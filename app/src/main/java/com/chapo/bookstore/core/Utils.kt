package com.chapo.bookstore.core

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : ViewBinding> Activity.viewBinding(createViewBinding: (LayoutInflater) -> T): ReadOnlyProperty<Any?, T> =
    object : ReadOnlyProperty<Any?, T> {
        var binding: T? = null
        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return if (binding != null) {
                binding!!
            } else {
                binding = createViewBinding(layoutInflater)
                binding!!
            }
        }
    }

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

inline fun <reified T : RecyclerView.Adapter<*>> RecyclerView.getRVAdapter(): T {
    return this.adapter as T
}