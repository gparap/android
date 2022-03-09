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
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gparap.apps.quiz.R
import gparap.apps.quiz.data.QuizDatabase
import gparap.apps.quiz.data.QuizModel
import gparap.apps.quiz.utils.AppConstants
import gparap.apps.quiz.utils.Utils

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var database: QuizDatabase
    private var selectedCategoryLiveData: MutableLiveData<String> = MutableLiveData()
    private var selectedCategoryQuestions: MutableLiveData<List<String>> = MutableLiveData()
    private var questionsAnimals: MutableLiveData<List<String>> = MutableLiveData()
    private var questionsGeography: MutableLiveData<List<String>> = MutableLiveData()
    private var questionsHistory: MutableLiveData<List<String>> = MutableLiveData()
    private var questionsLiterature: MutableLiveData<List<String>> = MutableLiveData()
    private var questionsMathematics: MutableLiveData<List<String>> = MutableLiveData()
    private var questionsCounter: Int = 0

    fun getSelectedCategory(): LiveData<String> {
        return selectedCategoryLiveData
    }

    fun setSelectedCategory(category: String) {
        selectedCategoryLiveData.value = category
    }

    fun getSelectedCategoryQuestions(): List<String>? {
        return selectedCategoryQuestions.value
    }

    fun getQuestionsCounter() : Int {
        return questionsCounter
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
     * Populates the selected quiz category with all its questions fetched from the database
     */
    fun populateSelectedCategoryQuestions() {
        when (selectedCategoryLiveData.value) {

            //Animals
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_animals) -> {
                if (questionsAnimals.value == null) {
                    questionsAnimals.value =
                        database.getAllQuestions(selectedCategoryLiveData.value!!)
                }
                selectedCategoryQuestions.value = questionsAnimals.value
            }

            //Geography
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_geography) -> {
                if (questionsGeography.value == null) {
                    questionsGeography.value =
                        database.getAllQuestions(selectedCategoryLiveData.value!!)
                }
                selectedCategoryQuestions.value = questionsGeography.value
            }

            //History
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_history) -> {
                if (questionsHistory.value == null) {
                    questionsHistory.value =
                        database.getAllQuestions(selectedCategoryLiveData.value!!)
                }
                selectedCategoryQuestions.value = questionsHistory.value
            }

            //Literature
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_literature) -> {
                if (questionsLiterature.value == null) {
                    questionsLiterature.value =
                        database.getAllQuestions(selectedCategoryLiveData.value!!)
                }
                selectedCategoryQuestions.value = questionsLiterature.value
            }

            //Mathematics
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_mathematics) -> {
                if (questionsMathematics.value == null) {
                    questionsMathematics.value =
                        database.getAllQuestions(selectedCategoryLiveData.value!!)
                }
                selectedCategoryQuestions.value = questionsMathematics.value
            }
        }
    }

    /**
     * Shuffles the questions of the selected quiz category
     */
    fun shuffleSelectedCategoryQuestions() {
        (selectedCategoryQuestions.value as MutableList<String>?)?.shuffle()
    }

    /**
     * Returns the next question of the quiz
     */
    fun getSelectedCategoryNextQuestion(): String {
        var nextQuestion = selectedCategoryQuestions.value!![questionsCounter]
        if (questionsCounter < AppConstants.QUIZ_QUESTIONS_COUNT) {
            questionsCounter += 1
            nextQuestion = selectedCategoryQuestions.value!![questionsCounter]
        } else {
            Toast.makeText(
                getApplication<Application?>().applicationContext,
                getApplication<Application?>().applicationContext.resources.getString(R.string.toast_next_question_error),
                Toast.LENGTH_SHORT
            ).show()
        }
        return nextQuestion
    }

    /**
     * Returns the previous question of the quiz or a toast if we have reached the start of the quiz
     */
    fun getSelectedCategoryPreviousQuestion(): String {
        var prevQuestion = selectedCategoryQuestions.value!![questionsCounter]
        if (questionsCounter > 1) {
            questionsCounter -= 1
            prevQuestion = selectedCategoryQuestions.value!![questionsCounter]
        } else {
            Toast.makeText(
                getApplication<Application?>().applicationContext,
                getApplication<Application?>().applicationContext.resources.getString(R.string.toast_prev_question_error),
                Toast.LENGTH_SHORT
            ).show()
        }
        return prevQuestion
    }
}