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
import android.content.Context.MODE_PRIVATE
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
    private var selectedCategoryMultipleChoices: MutableLiveData<ArrayList<ArrayList<String>>> =
        MutableLiveData()
    private var selectedCategoryRightAnswers: MutableLiveData<ArrayList<String>> =
        MutableLiveData()
    private var userQuizAnswers: MutableLiveData<ArrayList<String>> = MutableLiveData()
    private var questionsDifficulty: MutableLiveData<ArrayList<String>> = MutableLiveData()
    private var spinnerVisibilityLiveData: MutableLiveData<Int> = MutableLiveData()
    private var buttonStartVisibilityLiveData: MutableLiveData<Int> = MutableLiveData()
    private var layoutIntroVisibilityLiveData: MutableLiveData<Int> = MutableLiveData()
    private var layoutQuizVisibilityLiveData: MutableLiveData<Int> = MutableLiveData()

    fun getSelectedCategory(): LiveData<String> {
        return selectedCategoryLiveData
    }

    fun setSelectedCategory(category: String) {
        selectedCategoryLiveData.value = category
    }

    fun getSelectedCategoryQuestions(): List<String>? {
        return selectedCategoryQuestions.value
    }

    fun getQuestionsCounter(): Int {
        return questionsCounter
    }

    fun getUserQuizAnswers(): LiveData<ArrayList<String>?> {
        return userQuizAnswers
    }

    fun getSpinnerVisibility(): LiveData<Int> {
        return spinnerVisibilityLiveData
    }

    fun setSpinnerVisibility(visibility: Int) {
        spinnerVisibilityLiveData.value = visibility
    }

    fun getButtonStartVisibility(): LiveData<Int> {
        return buttonStartVisibilityLiveData
    }

    fun setButtonStartVisibility(visibility: Int) {
        buttonStartVisibilityLiveData.value = visibility
    }

    fun getLayoutIntroVisibility(): LiveData<Int> {
        return layoutIntroVisibilityLiveData
    }

    fun setLayoutIntroVisibility(visibility: Int) {
        layoutIntroVisibilityLiveData.value = visibility
    }

    fun getLayoutQuizVisibility(): LiveData<Int> {
        return layoutQuizVisibilityLiveData
    }

    fun setLayoutQuizVisibility(visibility: Int) {
        layoutQuizVisibilityLiveData.value = visibility
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
                populateAnimalsCategoryQuestions()
            }

            //Geography
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_geography) -> {
                populateGeographyCategoryQuestions()
            }

            //History
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_history) -> {
                populateHistoryCategoryQuestions()
            }

            //Literature
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_literature) -> {
                populateLiteratureCategoryQuestions()
            }

            //Mathematics
            this.getApplication<Application?>().applicationContext.resources.getString(R.string.category_mathematics) -> {
                populateMathematicsCategoryQuestions()
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

    /**
     * Returns 4 multiple choices for the current quiz question with one of them being the right one
     */
    fun getMultipleChoices(): List<String> {
        //initialize the list that holds the lists that hold all multiple choices
        if (selectedCategoryMultipleChoices.value == null) {
            selectedCategoryMultipleChoices.value = ArrayList()
            for (i in 1..AppConstants.QUIZ_QUESTIONS_COUNT) {
                selectedCategoryMultipleChoices.value!!.add(ArrayList())
            }
        }

        //return the multiple choices and do not query the database if we already have them
        if (selectedCategoryMultipleChoices.value!![questionsCounter - 1].isNotEmpty()) {
            return selectedCategoryMultipleChoices.value?.get(questionsCounter - 1) as List<String>
        }

        //fetch the right answer from the database based on the current category question
        val answer = database.getRightAnswer(
            selectedCategoryLiveData.value!!,
            selectedCategoryQuestions.value!![questionsCounter]
        ).also {
            //initialize the list that holds the right answers
            if (selectedCategoryRightAnswers.value == null) {
                selectedCategoryRightAnswers.value = ArrayList()
            }
            //add right answer to list
            selectedCategoryRightAnswers.value!!.add(it)
        }

        //add right answer to the list that holds the lists that hold all multiple choices
        selectedCategoryMultipleChoices.value?.get(questionsCounter - 1)?.add(answer)

        //fetch the wrongs answers from the database based on the current category question
        var choices = database.getWrongAnswers(
            selectedCategoryLiveData.value!!,
            selectedCategoryQuestions.value!![questionsCounter]
        )

        //split and cleanup the wrong answers
        choices = choices.replace("[", "")
        choices = choices.replace("]", "")
        choices = choices.replace("\"", "")
        val choicesSplit = choices.split(",")

        //add wrongs answers to the list that holds the lists that hold all multiple choices
        for (s in choicesSplit) {
            selectedCategoryMultipleChoices.value!![questionsCounter - 1].add(s)
        }

        //shuffle the multiple choices
        selectedCategoryMultipleChoices.value?.get(questionsCounter - 1)?.shuffle()

        //return the multiple choices
        return selectedCategoryMultipleChoices.value?.get(questionsCounter - 1) as List<String>
    }

    fun addUserAnswer(answer: String) {
        if (userQuizAnswers.value == null) {
            userQuizAnswers.value = ArrayList()
        }
        userQuizAnswers.value?.add(questionsCounter - 1, answer)
    }

    /**
     * Returns the difficulty for the current quiz question.
     *
     * Difficulty can be: EASY or MEDIUM or HARD
     */
    fun getQuestionDifficulty(): String? {
        var difficulty: String?

        //initialize the list that holds the difficulties
        if (questionsDifficulty.value == null) {
            questionsDifficulty.value = ArrayList()

            //get the difficulty from the list (if exists)
        } else {
            try {
                difficulty = questionsDifficulty.value?.get(questionsCounter - 1)
                return difficulty
            } catch (e: Exception) {
            }
        }

        //get the difficulty from the database and add it to the list
        difficulty = database.getQuestionDifficulty(
            selectedCategoryLiveData.value!!,
            selectedCategoryQuestions.value!![questionsCounter - 1]
        )
        questionsDifficulty.value?.add(questionsCounter - 1, difficulty)

        return difficulty
    }

    /**
     * Returns the average difficulty for the current quiz.
     *
     * Difficulty can be: easy OR medium OR hard
     */
    fun getQuizDifficulty(): String {
        return Utils.calculateQuizAverageDifficulty(questionsDifficulty.value!!)
    }

    /**
     * Returns how many times the user answered right in the quiz
     */
    fun getUserRightAnswersToQuiz(): Int {
        var rightAnswers = 0
        for (i in 0 until AppConstants.QUIZ_QUESTIONS_COUNT) {
            if (userQuizAnswers.value!![i] == selectedCategoryRightAnswers.value!![i]) {
                rightAnswers += 1
            }
        }
        return rightAnswers
    }

    /**
     * Returns the user score based on how many questions did they answer right and
     * what was the difficulty for these questions
     */
    fun getUserScore(): Int {
        var score = 0
        for (i in 0 until AppConstants.QUIZ_QUESTIONS_COUNT) {
            if (userQuizAnswers.value!![i] == selectedCategoryRightAnswers.value!![i]) {
                score += Utils.getScoreByDifficulty(questionsDifficulty.value!![i])
            }
        }
        return score
    }

    /**
     * Returns the user high score for the selected category from shared preferences
     */
    fun getUserHighScore(): Int {
        getApplication<Application?>().applicationContext.getSharedPreferences(
            selectedCategoryLiveData.value.toString(), MODE_PRIVATE
        ).apply {
            return this.getInt(selectedCategoryLiveData.value.toString(), 0)
        }
    }

    /**
     * Updates the shared preference for the selected category that holds the user high score
     */
    fun setUserHighScore(score: Int) {
        val highScore = getApplication<Application?>().applicationContext.getSharedPreferences(
            selectedCategoryLiveData.value.toString(), MODE_PRIVATE
        )
        highScore.edit().putInt(selectedCategoryLiveData.value.toString(), score).apply()
    }

    /**
     * Makes all appropriate changes so as the user can start a new quiz
     */
    fun resetQuiz() {
        questionsCounter = 0
        selectedCategoryMultipleChoices.value = null
        questionsDifficulty.value = null
        userQuizAnswers.value = null
        selectedCategoryRightAnswers.value = null
        selectedCategoryLiveData.value = ""
    }

    /**
     * Makes the question counter zero
     */
    fun resetQuestionCounter() {
        questionsCounter = 0
    }

    /**
     * Returns the index among multiple choices of the current quiz question's right answer
     */
    fun getQuizQuestionRightAnswerIndex(): Int {
        var rightAnswerIndex = 0

        val rightAnswer = selectedCategoryRightAnswers.value!![questionsCounter - 1]
        val choices = selectedCategoryMultipleChoices.value?.get(questionsCounter - 1)

        var index = 0
        for (choice in choices!!) {
            if (choice == rightAnswer) {
                rightAnswerIndex = index
                break
            }
            index++
        }

        return rightAnswerIndex
    }

    /**
     * Returns the index among multiple choices of the current quiz question's user answer
     */
    fun getQuizQuestionUserAnswerIndex(): Int {
        var userAnswerIndex = 0

        val userAnswer = userQuizAnswers.value!![questionsCounter - 1]
        val choices = selectedCategoryMultipleChoices.value?.get(questionsCounter - 1)

        var index = 0
        for (choice in choices!!) {
            if (choice == userAnswer) {
                userAnswerIndex = index
                break
            }
            index++
        }

        return userAnswerIndex
    }

    private fun populateAnimalsCategoryQuestions() {
        if (questionsAnimals.value == null) {
            questionsAnimals.value =
                database.getAllQuestions(selectedCategoryLiveData.value!!)
        }
        selectedCategoryQuestions.value = questionsAnimals.value
    }

    private fun populateGeographyCategoryQuestions() {
        if (questionsGeography.value == null) {
            questionsGeography.value =
                database.getAllQuestions(selectedCategoryLiveData.value!!)
        }
        selectedCategoryQuestions.value = questionsGeography.value
    }

    private fun populateHistoryCategoryQuestions() {
        if (questionsHistory.value == null) {
            questionsHistory.value =
                database.getAllQuestions(selectedCategoryLiveData.value!!)
        }
        selectedCategoryQuestions.value = questionsHistory.value
    }

    private fun populateLiteratureCategoryQuestions() {
        if (questionsLiterature.value == null) {
            questionsLiterature.value =
                database.getAllQuestions(selectedCategoryLiveData.value!!)
        }
        selectedCategoryQuestions.value = questionsLiterature.value
    }

    private fun populateMathematicsCategoryQuestions() {
        if (questionsMathematics.value == null) {
            questionsMathematics.value =
                database.getAllQuestions(selectedCategoryLiveData.value!!)
        }
        selectedCategoryQuestions.value = questionsMathematics.value
    }
}