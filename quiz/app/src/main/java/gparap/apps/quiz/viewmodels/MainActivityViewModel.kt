/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.quiz.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gparap.apps.quiz.data.QuizDatabase
import gparap.apps.quiz.data.QuizModel
import gparap.apps.quiz.utils.Utils

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var database: QuizDatabase
    private var selectedCategoryLiveData: MutableLiveData<String> = MutableLiveData()

    fun getSelectedCategory() : LiveData<String>{
        return selectedCategoryLiveData
    }

    fun setSelectedCategory(category: String) {
        selectedCategoryLiveData.value = category
    }

    /**
     * Creates the quiz database or open it if already exists
     */
    fun createOrOpenDatabase() {
        database = QuizDatabase(this.getApplication<Application?>().applicationContext, null)
        database.writableDatabase
    }

    /**
     * Closes any open database object
     */
    fun closeDatabase() {
        database.close()
    }

    /**
     * Populate the database table if it is empty with quiz data
     */
    fun populateDatabaseTable(table: String) {
        var quiz: List<QuizModel>? = null

        if (database.isTableEmpty(table)) {
            val jsonData = Utils.getJSONDataByCategory(
                this.getApplication<Application?>().applicationContext,
                Utils.getJsonFileByCategory(table)
            )
            if (jsonData != null) {
                quiz = Utils.getQuizModelFromJSON(jsonData)
            }

            //fill in the database table the first time
            if (quiz != null) {
                for (q in quiz) {
                    database.populateTable(table, q)
                }
            }
        }
    }

    /**
     * Fetches all questions from the database based on a quiz category
     */
    fun getAllQuestions(category: String): List<String> {
        return database.getAllQuestions(category)
    }
}