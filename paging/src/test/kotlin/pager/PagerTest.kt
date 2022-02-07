package pager

import com.chapo.paging.Pager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PagerTest {

    val pagingDataSource = FakePagingDataSource()

    private lateinit var pager: Pager<Int, TestPageModel>

    @Before
    fun setUp() {
        pager = object : Pager<Int, TestPageModel>(
            pagingDataSource = pagingDataSource
        ) {
            override val cachedPagesSize: Int
                get() = 3
            override val startingKey: Int
                get() = 1
            override var currentKey: Int = startingKey

            override val nextKey: Int
                get() = ++currentKey

            override fun isNotEmptyPage(page: TestPageModel): Boolean {
                return page.data.isNotEmpty()
            }

        }
    }

    @Test
    fun `loadStartingPage returns first page`() = runTest {
        pager.loadStartingPage()
        pager.pageListFlow.value.last().data.forEachIndexed { i, item ->
            assertEquals(item, pagingDataSource.page1[i])
        }
    }

    @Test
    fun `loadPage returns correct data`() = runTest {
        pager.loadPage(2)
        pager.pageListFlow.value.last().data.forEachIndexed { i, item ->
            assertEquals(item, pagingDataSource.page2[i])
        }
    }

    @Test
    fun `pages are cleaned after setting PagingDataSource`() = runTest {
        pager.loadPage(1)
        pager.pagingDataSource = pagingDataSource
        assertTrue(pager.pageListFlow.value.isEmpty())
    }

    @Test
    fun `next page loads page with next key`() = runTest {
        pager.loadStartingPage() // loads page 1
        pager.loadNextPage()
        // next page must return page 2 data
        pager.pageListFlow.value.last().data.forEachIndexed { i, item ->
            assertEquals(pagingDataSource.page2[i], item)
        }
    }

    @Test
    fun `loadPage removes first item if limit of cached items was reached`() = runTest {
        pager.loadPage(1)
        pager.loadPage(2)
        pager.loadPage(3)
        pager.loadPage(4)
        // first item must be removed because cache size is 3
        pager.pageListFlow.value.first().data.forEachIndexed { i, item ->
            assertEquals(item, pagingDataSource.page2[i])
        }
        assertFalse(pager.pageListFlow.value.any { // check if first page was removed
            it.data.any { data ->
                data.startsWith("1")
            }
        })

    }
}