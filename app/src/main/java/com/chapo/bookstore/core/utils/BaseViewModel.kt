package com.chapo.bookstore.core.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Suppress("PropertyName")
abstract class BaseViewModel(
    protected var errorHandler: ErrorHandler
) : ViewModel() {

    val errorState = errorHandler.showErrorState

    protected val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(errorHandler.globalHandler + context, start, block)
    }
}