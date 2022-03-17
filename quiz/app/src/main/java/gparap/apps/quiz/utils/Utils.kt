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
package gparap.apps.quiz.utils

import android.content.Context
import com.google.gson.Gson
import gparap.apps.quiz.data.JsonResponseModel
import gparap.apps.quiz.data.QuizModel
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object Utils {
    /**
     * Returns the score based on a question's difficulty
     */
    fun getScoreByDifficulty(difficulty: String) : Int{
        return when (difficulty) {
            AppConstants.QUESTION_DIFFICULTY_EASY -> AppConstants.SCORE_DIFFICULTY_EASY
            AppConstants.QUESTION_DIFFICULTY_MEDIUM -> AppConstants.SCORE_DIFFICULTY_MEDIUM
            AppConstants.QUESTION_DIFFICULTY_HARD -> AppConstants.SCORE_DIFFICULTY_HARD
            else -> {
                0
            }
        }
    }

    /**
     * Calculates the difficulty of a completed quiz based on its questions' difficulties.
     *
     * Returns the average difficulty as a string ie. "EASY"
     */
    fun calculateQuizAverageDifficulty(values: List<String>) : String {

        var difficultyPoints = 0

        //get all difficulties and map them to relative name variables
        for(i in 0 until AppConstants.QUIZ_QUESTIONS_COUNT){
            when(values[i]) {
                AppConstants.QUESTION_DIFFICULTY_EASY -> difficultyPoints += 1
                AppConstants.QUESTION_DIFFICULTY_MEDIUM -> difficultyPoints += 2
                AppConstants.QUESTION_DIFFICULTY_HARD ->difficultyPoints += 3
            }
        }

        //get the average of the difficulty
        val average: Float = (difficultyPoints / AppConstants.QUIZ_QUESTIONS_COUNT).toFloat()

        //return the average value as a string describing the difficulty
        return if (average < 1.5F) {
            AppConstants.QUESTION_DIFFICULTY_EASY
        } else if (average >= 1.5F && average < 2.5F) {
            AppConstants.QUESTION_DIFFICULTY_MEDIUM
        } else {
            AppConstants.QUESTION_DIFFICULTY_HARD
        }
    }

    /**
     * Returns a new string by fixing single string quotes (used before querying a database)
     */
    fun fixSingleStringQuotes(string: String): String {
        val singleQuotes = "\'\'"
        return string.replace("\'", singleQuotes)
    }

    /**
     * Gets the specific JSON file based on a category name
     */
    fun getJsonFileByCategory(categoryName: String) : String {
        return categoryName.plus(".json")
    }

    /**
     * Gets the QuizModel collection by deserializing a JSON string
     */
    fun getQuizModelFromJSON(jsonString: String): List<QuizModel> {
        val responseModel = Gson().fromJson(jsonString, JsonResponseModel::class.java)
        return responseModel.quiz
    }

    /**
     * Gets from the assets folder the JSON data string of a quiz category
     */
    fun getJSONDataByCategory(context: Context, categoryJson: String): String? {
        val json: String? = try {
            val inputStream: InputStream = context.assets.open(categoryJson)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName(AppConstants.CHARSET_NAME))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}