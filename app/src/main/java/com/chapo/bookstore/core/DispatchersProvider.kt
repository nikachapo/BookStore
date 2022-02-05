package com.chapo.bookstore.core

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersProvider {
    val mainImmediate: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}