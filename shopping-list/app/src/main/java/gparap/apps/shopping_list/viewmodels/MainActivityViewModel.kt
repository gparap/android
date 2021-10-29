package gparap.apps.shopping_list.viewmodels

import android.app.Application
import androidx.lifecycle.*
import gparap.apps.shopping_list.data.CategoryDao
import gparap.apps.shopping_list.data.CategoryModel
import gparap.apps.shopping_list.data.ShoppingListDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var categoryDao: CategoryDao
    private lateinit var categoryLiveData: LiveData<List<CategoryModel>>

    init {
        //initialize the app database and its data access objects
        val database = ShoppingListDatabase.getInstance(application.applicationContext)
        categoryDao = database?.getCategoryDao()!!
    }

    /** Adds a new shopping category */
    fun addShoppingCategory(categoryName: String) {
        val category = CategoryModel(categoryName)

        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.addNewCategory(category)
        }.apply {
            //refresh categories on the RecyclerView
            categoryLiveData = categoryDao.getAllCategories()
        }
    }

    /** Display all shopping categories */
    fun getShoppingCategories(): LiveData<List<CategoryModel>> {
        categoryLiveData = categoryDao.getAllCategories()
        return categoryLiveData
    }

    /** Edit a shopping category */
    fun editShoppingCategory(category: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.editCategory(category)
        }.apply {
            //refresh categories on the RecyclerView
            categoryLiveData = categoryDao.getAllCategories()
        }
    }

    /** Delete a shopping category */
    fun deleteShoppingCategory(category: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryDao.removeCategory(category)
        }.apply {
            //refresh categories on the RecyclerView
            categoryLiveData = categoryDao.getAllCategories()
        }
    }
}