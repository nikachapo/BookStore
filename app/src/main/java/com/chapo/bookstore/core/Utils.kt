package com.chapo.bookstore.core

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : ViewBinding> Activity.activityViewBinding(createViewBinding: (LayoutInflater) -> T): ReadOnlyProperty<Any?, T> =
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