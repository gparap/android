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
package gparap.apps.shopping_list.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import gparap.apps.shopping_list.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var itemDao: ItemDao
    private var categoryDao: CategoryDao
    private lateinit var itemLiveData: LiveData<List<ItemModel>>

    init {
        //initialize the app database and its data access objects
        val database = ShoppingListDatabase.getInstance(application.applicationContext)
        itemDao = database?.getItemDao()!!
        categoryDao = database.getCategoryDao()
    }

    /** Adds a new item to a shopping category */
    fun addShoppingCategoryItem(categoryId: Int, itemName: String) {
        val item = ItemModel(categoryId, itemName)

        //add category item
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.addNewItem(item)

            //update category items count
            val category = categoryDao.getCategory(categoryId)
            category.itemsCount = categoryDao.getCategoryItemsCount(categoryId)
            categoryDao.updateItemsCount(category)

        }.apply {
            //refresh items on the RecyclerView
            itemLiveData = itemDao.getAllCategoryItems(categoryId)
        }
    }

    /** Display all shopping category items */
    fun getShoppingCategoryItems(categoryId: Int): LiveData<List<ItemModel>> {
        itemLiveData = itemDao.getAllCategoryItems(categoryId)
        return itemLiveData
    }

    /** Edits a shopping category item */
    fun editShoppingCategoryItem(item: ItemModel, categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.editItem(item.id, item.name)

        }.apply {
            //refresh items on the RecyclerView
            itemLiveData = itemDao.getAllCategoryItems(categoryId)
        }
    }

    fun deleteShoppingCategoryItem(item: ItemModel, categoryId: Int) {
        //delete category item
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.removeItem(item)

            //update category items count
            val category = categoryDao.getCategory(categoryId)
            category.itemsCount = categoryDao.getCategoryItemsCount(categoryId)
            categoryDao.updateItemsCount(category)

        }.apply {
            //refresh items on the RecyclerView
            itemLiveData = itemDao.getAllCategoryItems(categoryId)
        }
    }
}