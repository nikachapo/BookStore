package com.chapo.bookstore.core

interface IStringResHelper {
    fun getString(resId: Int): String
    fun getString(resId: Int, vararg params: Any): String
    fun getStringArray(resId: Int): Array<out String>
}