package com.chapo.bookstore.util
import com.chapo.bookstore.core.utils.stringreshelper.StringResHelper

class TestStringResHelper : StringResHelper {

    var testValue = "test"

    override fun getString(resId: Int) = testValue

    override fun getString(resId: Int, vararg params: Any) = testValue

    override fun getStringArray(resId: Int): Array<out String> = arrayOf(testValue)
}