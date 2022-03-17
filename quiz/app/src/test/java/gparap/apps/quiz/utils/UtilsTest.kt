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

import org.junit.Assert.*

import org.junit.Test

class UtilsTest {

    @Test
    fun getQuizModelFromJSON() {
        val category = "geography"
        val jsonString =
            "{\"results\":[{\"category\":$category,\"type\":\"multiple\",\"difficulty\":\"medium\",\"question\":\"What European country is not a part of the EU?\",\"correct_answer\":\"Norway\",\"incorrect_answers\":[\"Lithuania\",\"Ireland\",\"Czechia\"]}]}"
        val quizModel = Utils.getQuizModelFromJSON(jsonString)

        assertEquals(quizModel[0].category, category)
    }

    @Test
    fun getJsonFileByCategory() {
        val category = "history"
        val expectedFile = "history.json"
        val actualFile = Utils.getJsonFileByCategory(category)

        assertEquals(actualFile, expectedFile)
    }

    @Test
    fun fixSingleStringQuotes_replaceOneSingleQuoteWithTwoSingleQuotes() {
        val initialString = "Fix this ' single quote"
        val expectedString = "Fix this '' single quote"
        val actualString = Utils.fixSingleStringQuotes(initialString)

        assertEquals(expectedString, actualString)
    }

    @Test
    fun calculateQuizAverageDifficulty_easyDifficultyValueIsCorrect() {
        val difficulties = ArrayList<String>()
        val expectedDifficulty = AppConstants.QUESTION_DIFFICULTY_EASY

        //add difficulties for an easy quiz
        for (i in 0 until AppConstants.QUIZ_QUESTIONS_COUNT) {
            //add some non-easy difficulties
            if (i == 0 || i == 1 || i == 2) {
                difficulties.add(AppConstants.QUESTION_DIFFICULTY_HARD)
            } else {
                difficulties.add(AppConstants.QUESTION_DIFFICULTY_EASY)
            }
        }

        //get the average difficulty
        val actualDifficulty = Utils.calculateQuizAverageDifficulty(difficulties)

        assertEquals(expectedDifficulty, actualDifficulty)
    }

    @Test
    fun calculateQuizAverageDifficulty_mediumDifficultyValueIsCorrect() {
        val difficulties = ArrayList<String>()
        val expectedDifficulty = AppConstants.QUESTION_DIFFICULTY_MEDIUM

        //add difficulties for an medium quiz
        for (i in 0 until AppConstants.QUIZ_QUESTIONS_COUNT) {
            //add some non-medium difficulties
            if (i == 0 || i == 1 || i == 2) {
                difficulties.add(AppConstants.QUESTION_DIFFICULTY_HARD)
            } else {
                difficulties.add(AppConstants.QUESTION_DIFFICULTY_MEDIUM)
            }
        }

        //get the average difficulty
        val actualDifficulty = Utils.calculateQuizAverageDifficulty(difficulties)

        assertEquals(expectedDifficulty, actualDifficulty)
    }

    @Test
    fun calculateQuizAverageDifficulty_hardDifficultyValueIsCorrect() {
        val difficulties = ArrayList<String>()
        val expectedDifficulty = AppConstants.QUESTION_DIFFICULTY_HARD

        //add difficulties for an hard quiz
        for (i in 0 until AppConstants.QUIZ_QUESTIONS_COUNT) {
            difficulties.add(AppConstants.QUESTION_DIFFICULTY_HARD)
        }

        //get the average difficulty
        val actualDifficulty = Utils.calculateQuizAverageDifficulty(difficulties)

        assertEquals(expectedDifficulty, actualDifficulty)
    }

    @Test
    fun getScoreByDifficulty() {
        val difficulty = AppConstants.QUESTION_DIFFICULTY_MEDIUM
        val expectedScore = AppConstants.SCORE_DIFFICULTY_MEDIUM
        val actualScore = Utils.getScoreByDifficulty(difficulty)

        assertEquals(expectedScore, actualScore)
    }
}