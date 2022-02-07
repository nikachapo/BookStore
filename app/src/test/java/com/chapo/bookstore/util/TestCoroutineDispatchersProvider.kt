package com.chapo.bookstore.util

import com.chapo.bookstore.core.utils.dispatcher.DispatchersProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TestCoroutineDispatchersProvider : DispatchersProvider {

    override val mainImmediate: CoroutineDispatcher = Dispatchers.Unconfined
    override val main: CoroutineDispatcher = Dispatchers.Unconfined
    override val io: CoroutineDispatcher = Dispatchers.Unconfined
    override val default: CoroutineDispatcher = Dispatchers.Unconfined
}