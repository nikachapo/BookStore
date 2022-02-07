package com.chapo.bookstore.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.createTestCoroutineScope
import org.junit.rules.TestWatcher

//@ExperimentalCoroutinesApi
//class MainCoroutineRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()):
//    TestWatcher(),
//    TestScope by TestScope(dispatcher) {
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(dispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        cleanupTestCoroutines()
//        Dispatchers.resetMain()
//    }
//}