package com.chapo.bookstore.core.utils

import androidx.annotation.StringRes
import com.chapo.bookstore.R
import com.chapo.bookstore.core.domain.NetworkException
import com.chapo.bookstore.core.domain.NetworkUnavailableException
import com.chapo.bookstore.core.domain.UnknownException
import com.chapo.bookstore.core.utils.stringreshelper.StringResHelper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.reflect.KClass

@ViewModelScoped
class ErrorHandler @Inject constructor(
    private val stringResHelper: StringResHelper
) {

    private val _showErrorState = MutableStateFlow<String?>(null)
    val showErrorState = _showErrorState.asStateFlow()

    private val commonExceptions = mutableMapOf<KClass<out Throwable>, @StringRes Int>(
        NetworkException::class to R.string.app_name,
        NetworkUnavailableException::class to R.string.app_name,
        UnknownException::class to R.string.app_name
    )

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _showErrorState.value = getErrorMessage(exception::class)
    }

    fun addExceptions(e: KClass<out Throwable>, @StringRes stringRes: Int) {
        commonExceptions[e] = stringRes
    }

    private fun getErrorMessage(e: KClass<out Throwable>): String {
        return commonExceptions[e]?.let { stringResHelper.getString(it) }
            ?: throw IllegalArgumentException("No string found for class \"${e::class.simpleName}\"")
    }
}