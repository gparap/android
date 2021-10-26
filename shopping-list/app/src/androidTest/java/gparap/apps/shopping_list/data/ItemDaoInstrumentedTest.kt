/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.shopping_list.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ItemDaoInstrumentedTest {
    private lateinit var db: ShoppingListDatabase
    private lateinit var dao: ItemDao

    //execute tasks synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        //create a fresh new database in memory before every test
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            ShoppingListDatabase::class.java
        ).allowMainThreadQueries().build()

        //get the data access object from created database
        dao = db.getItemDao()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun addNewItem() = runBlockingTest {
        //add a new item
        val item = ItemModel(1, 1, "one")
        dao.addNewItem(item)

        //select all items
        val items = dao.getAllCategoryItems(1).getOrAwaitValue()

        //test if new item was added
        Assert.assertTrue(
            "insertion of item to database failed..",
            items.contains(item)
        )
    }

    //!!! it is hyperbole to test this function as it is the base for all other dao functions
    //!!! but we test for consistency
    @ExperimentalCoroutinesApi
    @Test
    fun getAllCategoryItems() = runBlockingTest {
        //add 2 new items to database
        val itemsCount = 2
        val item1 = ItemModel(1, 1, "one")
        val item2 = ItemModel(2, 1, "two")
        dao.addNewItem(item1)
        dao.addNewItem(item2)

        //select all items
        val items = dao.getAllCategoryItems(1).getOrAwaitValue()

        //test if all items were selected
        Assert.assertTrue(
            "selection of all items from database failed..",
            itemsCount == items.size
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun editItem() = runBlockingTest {
        val expectedItemName = "updated"

        //add a new item to database and update it
        val item = ItemModel(1, 1, "one")
        dao.addNewItem(item)
        item.name = "updated"
        dao.editItem(item)

        //select the updated item
        val items = dao.getAllCategoryItems(1).getOrAwaitValue()
        val actualItemName = items[0].name  //it's ok, we only have one item

        //test if item was updated
        Assert.assertTrue(
            "updating item to database failed..",
            actualItemName == expectedItemName
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun removeItem() = runBlockingTest {
        //add a new item to database and delete it
        val item = ItemModel(1, 1, "one")
        dao.addNewItem(item)
        dao.removeItem(item)

        //select all items
        val items = dao.getAllCategoryItems(1).getOrAwaitValue()

        //test if item was deleted
        Assert.assertFalse(
            "deleting item from database failed..",
            items.contains(item)
        )
    }

    @After
    fun tearDown() {
        db.close()
    }
}