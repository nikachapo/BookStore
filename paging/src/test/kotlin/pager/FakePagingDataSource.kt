package pager

import com.chapo.paging.PagingDataSource

class FakePagingDataSource : PagingDataSource<Int, TestPageModel> {

    val page1 = listOf("1_item1", "1_item2", "1_item3")
    val page2 = listOf("2_item1", "2_item2", "2_item3")
    val page3 = listOf("3_item1", "3_item2", "3_item3")
    val page4 = listOf("4_item1", "4_item2", "4_item3")


    var fakeData = mutableMapOf(
        1 to page1,
        2 to page2,
        3 to page3,
        4 to page4
    )

    override suspend fun getData(page: Int): TestPageModel {
        return TestPageModel(page, fakeData[page] ?: emptyList())
    }

}