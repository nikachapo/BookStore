package com.chapo.bookstore.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatchersProvider @Inject constructor() : DispatchersProvider {

    override val mainImmediate: CoroutineDispatcher
        get() = Dispatchers.Main.immediate

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}