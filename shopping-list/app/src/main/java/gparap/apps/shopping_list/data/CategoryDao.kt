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

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewCategory(category: CategoryModel)

    @Query("SELECT * FROM category")
    fun getAllCategories(): LiveData<List<CategoryModel>>

    @Query("SELECT * FROM category WHERE category_id= :categoryId")
    suspend fun getCategory(categoryId: Int) : CategoryModel

    @Query("SELECT COUNT(*) FROM item WHERE category_id= :categoryId")
    suspend fun getCategoryItemsCount(categoryId: Int) :Int

    @Update
    suspend fun editCategory(category: CategoryModel)

    @Update
    suspend fun updateItemsCount(category: CategoryModel)

    @Delete
    suspend fun removeCategory(category: CategoryModel)
}