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
class CategoryDaoInstrumentedTest {
    private lateinit var db: ShoppingListDatabase
    private lateinit var dao: CategoryDao

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
        dao = db.getCategoryDao()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun addNewCategory() = runBlockingTest {
        //add a new category
        val category = CategoryModel("one")
        dao.addNewCategory(category)

        //select all categories
        val categories = dao.getAllCategories().getOrAwaitValue()

        //test if new category was added
        Assert.assertTrue(
            "insertion of category to database failed..",
            categories.contains(category)
        )
    }

    //!!! it is hyperbole to test this function as it is the base for all other dao functions
    //!!! but we test for consistency
    @ExperimentalCoroutinesApi
    @Test
    fun getAllCategories() = runBlockingTest {
        //add 2 new categories to database
        val categoriesCount = 2
        val category1 = CategoryModel("one")
        val category2 = CategoryModel("two")
        dao.addNewCategory(category1)
        dao.addNewCategory(category2)

        //select all categories
        val categories = dao.getAllCategories().getOrAwaitValue()

        //test if all categories were selected
        Assert.assertTrue(
            "selection of all categories from database failed..",
            categoriesCount == categories.size
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun editCategory() = runBlockingTest {
        val expectedCategoryName = "updated"

        //add a new category to database and update it
        val category = CategoryModel("one")
        category.setId(1)
        dao.addNewCategory(category)
        category.name = "updated"
        dao.editCategory(category)

        //select the updated category
        val categories = dao.getAllCategories().getOrAwaitValue()
        val actualCategoryName = categories[0].name //it's ok, we only have one category

        //test if category was updated
        Assert.assertTrue(
            "updating category to database failed..",
            actualCategoryName == expectedCategoryName
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun removeCategoryById() = runBlockingTest {
        //add a new category to database and delete it
        val category = CategoryModel("one")
        category.setId(1)
        dao.addNewCategory(category)
        dao.removeCategory(category)

        //select all categories
        val categories = dao.getAllCategories().getOrAwaitValue()

        //test if category was deleted
        Assert.assertFalse(
            "deleting category from database failed..",
            categories.contains(category)
        )
    }

    @After
    fun tearDown() {
        db.close()
    }
}

//!!! This is very important because the "id" field is auto-increment
//!!!   and we must not be able to set it from the app
private fun CategoryModel.setId(id: Int) {
    this.id = id
}
